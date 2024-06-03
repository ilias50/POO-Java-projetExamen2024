package administrator.frontend;

import administrator.backend.Administrator;
import administrator.backend.AdministratorListener;
import administrator.backend.IAdministrator;
import administrator.frontend.componenet.*;
import administrator.frontend.componenet.listener.PanBtnNewTicketDispenserListener;
import administrator.frontend.componenet.listener.PanBtnNewTicketOfficeListener;
import customer.backend.ICustomer;
import customer.factory.EnumCustomerFactory;
import garner.IGarner;
import ticketDispenser.backend.ITicketDispenser;
import ticketDispenser.exception.TicketDispenserException;
import ticketDispenser.factory.EnumTicketDispenserType;
import ticketDispenser.factory.TicketDispenserFactory;
import ticketDispenser.factory.exception.TicketDispenserFactoryException;
import ticketDispenser.numberManager.NumberManager;
import ticketOffice.backend.ITicketOffice;
import ticketOffice.backend.ITicketOfficeAdministrator;
import ticketOffice.factory.EnumTypeOfTicketOffice;
import ticketOffice.factory.TicketOfficeFactory;
import ticketOffice.factory.exception.TicketOfficeFactoryException;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

public class PanAdministrator extends JPanel implements PanBtnNewTicketDispenserListener, PanBtnNewTicketOfficeListener, AdministratorListener, IAdministrator {

    private final IAdministrator administrator = new Administrator();

    private final PanBtnNewTicketOffice panBtnNewTicketOffice = new PanBtnNewTicketOffice();
    private final PanBtnNewTicketDispenser panBtnNewTicketDispenser = new PanBtnNewTicketDispenser();
    private final PanDisplayTicketOfficeInformation panDisplayTicketOfficeInformation = new PanDisplayTicketOfficeInformation();
    private final PanDisplayCustomerInformation panDisplayCustomerInformation = new PanDisplayCustomerInformation();

    private final PanDisplayTicketDispenserInformation panDisplayTicketDispenserInformation = new PanDisplayTicketDispenserInformation();
    private final PanDisplayCurrentTime panDisplayCurrentTime = new PanDisplayCurrentTime();

    private final JPanel PanAllInfo = new JPanel(new GridLayout(0, 3));

    private JScrollPane scrollPane;

    public PanAdministrator() {
        getAdministrator().addAdministratorListener(this);
        initComponent();
    }

    private void initComponent() {
        setLayout(new BorderLayout());
        add(getPanDisplayCurrentTime(), BorderLayout.NORTH);
        getPanAllInfo().add(getPanDisplayTicketDispenserInformation());
        getPanAllInfo().add(getPanDisplayTicketOfficeInformation());
        getPanAllInfo().add(getPanDisplayCustomerInformation());
        setScrollPane(new JScrollPane(getPanAllInfo()));
        getScrollPane().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getScrollPane().setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(getScrollPane(), BorderLayout.CENTER);
        JPanel panSouth = new JPanel(new GridLayout(0, 2));
        getPanBtnNewTicketDispenser().addPanBtnNewTicketDispenserListener(this);
        panSouth.add(getPanBtnNewTicketDispenser());
        getPanBtnNewTicketOffice().addPanBtnNewTicketDispenserListener(this);
        panSouth.add(getPanBtnNewTicketOffice());
        add(panSouth, BorderLayout.SOUTH);
    }


    public JPanel getPanAllInfo() {
        return PanAllInfo;
    }

    public PanDisplayTicketOfficeInformation getPanDisplayTicketOfficeInformation() {
        return panDisplayTicketOfficeInformation;
    }

    public PanDisplayCustomerInformation getPanDisplayCustomerInformation() {
        return panDisplayCustomerInformation;
    }

    public PanDisplayTicketDispenserInformation getPanDisplayTicketDispenserInformation() {
        return panDisplayTicketDispenserInformation;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public IAdministrator getAdministrator() {
        return administrator;
    }

    public PanBtnNewTicketOffice getPanBtnNewTicketOffice() {
        return panBtnNewTicketOffice;
    }

    public PanBtnNewTicketDispenser getPanBtnNewTicketDispenser() {
        return panBtnNewTicketDispenser;
    }


    public PanDisplayCurrentTime getPanDisplayCurrentTime() {
        return panDisplayCurrentTime;
    }

    @Override
    public void newTicketDispenser() {
        try {
            ITicketDispenser ticketDispenser = TicketDispenserFactory.createTicketDispenser(EnumTicketDispenserType.WINDOW_TICKET_DISPENSER,new NumberManager(), getGarner());
            addITicketDispenser(ticketDispenser);
        } catch (TicketDispenserException | TicketDispenserFactoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void newTicketOffice() {

        try {
            ITicketOffice ticketOffice = TicketOfficeFactory.createTicketOffice(EnumTypeOfTicketOffice.WINDOW_TICKET_OFFICE, (ITicketOfficeAdministrator) getAdministrator(), getGarner());
            getAdministrator().addITicketOffice(ticketOffice);
        } catch (TicketOfficeFactoryException e) {
            throw new RuntimeException(e);
        }


    }

    /*
    ------------------------------------------------------------------------------------------------------
    -- méthode AdministratorListener
    -------------------------------------------------------------------------------------------------------
     */


    @Override
    public void ticketOfficeChanged(ITicketOffice ticketOffice) {
        getPanDisplayTicketOfficeInformation().updateInformation(ticketOffice);
        repaint();
    }

    @Override
    public void ticketOfficeAdded(ITicketOffice ticketOffice) {
        getPanDisplayTicketOfficeInformation().addITicketOffice(ticketOffice);
        repaint();
    }

    @Override
    public void ticketOfficeRemoved(ITicketOffice ticketOffice) {
        getPanDisplayTicketOfficeInformation().removeITicketOffice(ticketOffice);
        repaint();
    }

    @Override
    public void customerChanged(ICustomer customer) {
        getPanDisplayCustomerInformation().updateInformation(customer);
    }

    @Override
    public void customerAdded(ICustomer customer) {
        getPanDisplayCustomerInformation().addICustomer(customer);
    }

    @Override
    public void customerRemoved(ICustomer customer) {
        getPanDisplayCustomerInformation().removeICustomer(customer);
    }

    @Override
    public void ticketDispenserChanged(ITicketDispenser ticketDispenser) {

    }

    @Override
    public void ticketDispenserAdded(ITicketDispenser ticketDispenser) {
        getPanDisplayTicketDispenserInformation().addITicketDispenser(ticketDispenser);
    }

    @Override
    public void ticketDispenserRemoved(ITicketDispenser ticketDispenser) {
        getPanDisplayTicketDispenserInformation().removeITicketDispenser(ticketDispenser);
    }

    /*
    ------------------------------------------------------------------------------------------------------
    -- méthode IAdministrator
    -------------------------------------------------------------------------------------------------------
     */

    @Override
    public List<ICustomer> getListCustomer() {
        return getAdministrator().getListCustomer();
    }

    @Override
    public Set<ITicketOffice> getTicketOffices() {
        return getAdministrator().getTicketOffices();
    }

    @Override
    public Set<ITicketDispenser> getTicketDispensers() {
        return getAdministrator().getTicketDispensers();
    }

    @Override
    public void addITicketOffice(ITicketOffice iTicketOffice) {
        getAdministrator().addITicketOffice(iTicketOffice);
    }

    @Override
    public void removeTicketOffice(ITicketOffice iTicketOffice) {
        getAdministrator().removeTicketOffice(iTicketOffice);
    }

    @Override
    public void addITicketDispenser(ITicketDispenser iTicketDispenser) {
        getAdministrator().addITicketDispenser(iTicketDispenser);
    }

    @Override
    public void removeTicketDispenser(ITicketDispenser iTicketDispenser) {
        getAdministrator().removeTicketDispenser(iTicketDispenser);
    }

    @Override
    public void addAdministratorListener(AdministratorListener administratorListener) {
        getAdministrator().addAdministratorListener(administratorListener);
    }

    @Override
    public void removeAdministratorListener(AdministratorListener administratorListener) {
        getAdministrator().removeAdministratorListener(administratorListener);
    }

    @Override
    public void fireAdministratorListenerTicketOfficeChanged(ITicketOffice ticketOffice) {
        getAdministrator().fireAdministratorListenerTicketOfficeChanged(ticketOffice);
    }

    @Override
    public void fireAdministratorListenerTicketOfficeAdded(ITicketOffice ticketOffice) {
        getAdministrator().fireAdministratorListenerTicketOfficeAdded(ticketOffice);
    }

    @Override
    public void fireAdministratorListenerTicketOfficeRemoved(ITicketOffice ticketOffice) {
        getAdministrator().fireAdministratorListenerTicketOfficeRemoved(ticketOffice);
    }

    @Override
    public void fireAdministratorListenerCustomerChanged(ICustomer customer) {
        getAdministrator().fireAdministratorListenerCustomerChanged(customer);
    }

    @Override
    public void fireAdministratorListenerCustomerAdded(ICustomer customer) {
        getAdministrator().fireAdministratorListenerCustomerAdded(customer);
    }

    @Override
    public void fireAdministratorListenerCustomerRemoved(ICustomer customer) {
        getAdministrator().fireAdministratorListenerCustomerRemoved(customer);
    }

    @Override
    public void fireAdministratorListenerTicketDispenserChanged(ITicketDispenser ticketDispenser) {
        getAdministrator().fireAdministratorListenerTicketDispenserChanged(ticketDispenser);
    }

    @Override
    public void fireAdministratorListenerTicketDispenserAdded(ITicketDispenser ticketDispenser) {
        getAdministrator().fireAdministratorListenerTicketDispenserAdded(ticketDispenser);
    }

    @Override
    public void fireAdministratorListenerTicketDispenserRemoved(ITicketDispenser ticketDispenser) {
        getAdministrator().fireAdministratorListenerTicketDispenserRemoved(ticketDispenser);
    }

    @Override
    public void addCustomer(int idCustomer, EnumCustomerFactory customerType) {
        getAdministrator().addCustomer(idCustomer, customerType);
    }

    @Override
    public void addCustomerIntoList(ICustomer customer) {
        getAdministrator().addCustomerIntoList(customer);
    }

    @Override
    public IGarner getGarner() {
        return getAdministrator().getGarner();
    }
}
