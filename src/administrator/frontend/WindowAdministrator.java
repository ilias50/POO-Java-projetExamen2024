package administrator.frontend;

import administrator.backend.AdministratorListener;
import administrator.backend.IAdministrator;
import customer.backend.ICustomer;
import customer.factory.EnumCustomerFactory;
import garner.IGarner;
import ticketDispenser.backend.ITicketDispenser;
import ticketOffice.backend.ITicketOffice;

import javax.swing.*;
import java.util.List;
import java.util.Set;

public class WindowAdministrator extends JFrame implements IAdministrator {

    private final PanAdministrator panAdministrator = new PanAdministrator();

    private final JPanel panCont = (JPanel) getContentPane();

    public WindowAdministrator(){
        initComponent();
        setVisible(true);
    }

    public PanAdministrator getPanAdministrator() {
        return panAdministrator;
    }



    private void initComponent(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 500, 300);
        setTitle("Administrateur");
        getPanCont().add(getPanAdministrator());
        getPanAdministrator().newTicketDispenser();
        getPanAdministrator().newTicketOffice();


    }


    public JPanel getPanCont() {
        return panCont;
    }

    @Override
    public List<ICustomer> getListCustomer() {
        return getPanAdministrator().getListCustomer();
    }

    @Override
    public Set<ITicketOffice> getTicketOffices() {
        return getPanAdministrator().getTicketOffices();
    }

    @Override
    public Set<ITicketDispenser> getTicketDispensers() {
        return getPanAdministrator().getTicketDispensers();
    }

    @Override
    public void addITicketOffice(ITicketOffice iTicketOffice) {
        getPanAdministrator().addITicketOffice(iTicketOffice);
    }

    @Override
    public void removeTicketOffice(ITicketOffice iTicketOffice) {
        getPanAdministrator().removeTicketOffice(iTicketOffice);
    }

    @Override
    public void addITicketDispenser(ITicketDispenser iTicketDispenser) {
        getPanAdministrator().addITicketDispenser(iTicketDispenser);
    }

    @Override
    public void removeTicketDispenser(ITicketDispenser iTicketDispenser) {
        getPanAdministrator().removeTicketDispenser(iTicketDispenser);
    }

    @Override
    public void addAdministratorListener(AdministratorListener administratorListener) {
        getPanAdministrator().addAdministratorListener(administratorListener);
    }

    @Override
    public void removeAdministratorListener(AdministratorListener administratorListener) {
        getPanAdministrator().removeAdministratorListener(administratorListener);
    }

    @Override
    public void fireAdministratorListenerTicketOfficeChanged(ITicketOffice ticketOffice) {
        getPanAdministrator().fireAdministratorListenerTicketOfficeChanged(ticketOffice);
    }

    @Override
    public void fireAdministratorListenerTicketOfficeAdded(ITicketOffice ticketOffice) {
        getPanAdministrator().fireAdministratorListenerTicketOfficeAdded(ticketOffice);
    }

    @Override
    public void fireAdministratorListenerTicketOfficeRemoved(ITicketOffice ticketOffice) {
        getPanAdministrator().fireAdministratorListenerTicketOfficeRemoved(ticketOffice);
    }

    @Override
    public void fireAdministratorListenerCustomerChanged(ICustomer customer) {
        getPanAdministrator().fireAdministratorListenerCustomerChanged(customer);
    }

    @Override
    public void fireAdministratorListenerCustomerAdded(ICustomer customer) {
        getPanAdministrator().fireAdministratorListenerCustomerAdded(customer);
    }

    @Override
    public void fireAdministratorListenerCustomerRemoved(ICustomer customer) {
        getPanAdministrator().fireAdministratorListenerCustomerRemoved(customer);
    }

    @Override
    public void fireAdministratorListenerTicketDispenserChanged(ITicketDispenser ticketDispenser) {
        getPanAdministrator().fireAdministratorListenerTicketDispenserChanged(ticketDispenser);
    }

    @Override
    public void fireAdministratorListenerTicketDispenserAdded(ITicketDispenser ticketDispenser) {
        getPanAdministrator().fireAdministratorListenerTicketDispenserAdded(ticketDispenser);
    }

    @Override
    public void fireAdministratorListenerTicketDispenserRemoved(ITicketDispenser ticketDispenser) {
        getPanAdministrator().fireAdministratorListenerTicketDispenserRemoved(ticketDispenser);
    }

    @Override
    public void addCustomer(int idCustomer, EnumCustomerFactory customerType) {
        getPanAdministrator().addCustomer(idCustomer, customerType);
    }

    @Override
    public void addCustomerIntoList(ICustomer customer) {
        getPanAdministrator().addCustomerIntoList(customer);
    }

    @Override
    public IGarner getGarner() {
        return getPanAdministrator().getGarner();
    }
}
