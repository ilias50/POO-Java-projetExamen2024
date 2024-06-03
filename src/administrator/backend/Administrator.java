package administrator.backend;

import customer.backend.CustomerListener;
import customer.backend.EnumStateCustomer;
import customer.backend.ICustomer;
import customer.factory.CustomerFactory;
import customer.factory.EnumCustomerFactory;
import customer.factory.Exception.CustomerTypeNotFoundException;
import garner.Garner;
import ticketDispenser.backend.EnumStateTicketDispenser;
import ticketDispenser.backend.ITicketDispenser;
import ticketDispenser.backend.TicketDispenserListener;
import ticketOffice.backend.EnumStateTicketOffice;
import ticketOffice.backend.ITicketOffice;
import ticketOffice.backend.ITicketOfficeAdministrator;
import ticketOffice.backend.TicketOfficeListener;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Administrator implements ITicketOfficeAdministrator, TicketDispenserListener, TicketOfficeListener, CustomerListener, IAdministrator {


    private final Set<ITicketOffice> ticketOffices = new HashSet<>();

    private final Set<ITicketDispenser> ticketDispensers = new HashSet<>();



    private final List<Integer> listTicketNumber = new LinkedList<>();

    private final Set<AdministratorListener> administratorListeners = new HashSet<>();


    private final Garner garner = new Garner();
    private final List<ICustomer> listCustomer = new LinkedList<>();




    public List<Integer> getListTicketNumber() {
        return listTicketNumber;
    }

    public Set<AdministratorListener> getAdministratorListeners() {
        return administratorListeners;
    }






    private ICustomer getCustomer(int idCustomer){
        if (listCustomerIsEmpty()) return null;
        for (ICustomer customer: getListCustomer()) {
            if (customer.getId() == idCustomer){
                return customer;
            }
        }
        return null;
    }

    private ICustomer createCustomer(int idCustomer, EnumCustomerFactory customerType){
        try {
            return CustomerFactory.createCustomer(customerType, idCustomer);
        } catch (CustomerTypeNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private boolean listCustomerIsEmpty() {
        return getListCustomer().isEmpty();
    }




    /*
    ------------------------------------------------------------------------------------------------------
    -- méthode de IAdministrator
    --------------------------------------------------------------------------------------------------
     */

    @Override
    public List<ICustomer> getListCustomer() {
        return listCustomer;
    }
    @Override
    public Set<ITicketOffice> getTicketOffices() {
        return ticketOffices;
    }
    @Override
    public Set<ITicketDispenser> getTicketDispensers() {
        return ticketDispensers;
    }
    @Override
    public Garner getGarner() {
        return garner;
    }
    @Override
    public void addCustomer(int idCustomer, EnumCustomerFactory customerType) {
        ICustomer customer = createCustomer(idCustomer, customerType);
        addCustomerIntoList(customer);
    }
    @Override
    public void addCustomerIntoList(ICustomer customer){
        customer.addCustomerListener(this);
        getListCustomer().add(customer);
        fireAdministratorListenerCustomerAdded(customer);
    }


    @Override
    public void addITicketOffice(ITicketOffice iTicketOffice){
        iTicketOffice.addTicketOfficeListener(this);
        ticketOffices.add(iTicketOffice);
        fireAdministratorListenerTicketOfficeAdded(iTicketOffice);
    }
    @Override
    public void removeTicketOffice(ITicketOffice iTicketOffice){
        ticketOffices.remove(iTicketOffice);
        fireAdministratorListenerTicketOfficeRemoved(iTicketOffice);
    }
    @Override
    public void addITicketDispenser(ITicketDispenser iTicketDispenser){
        iTicketDispenser.addTicketDispenserListener(this);
        ticketDispensers.add(iTicketDispenser);
        fireAdministratorListenerTicketDispenserAdded(iTicketDispenser);

    }

    @Override
    public void removeTicketDispenser(ITicketDispenser iTicketDispenser){
        ticketDispensers.remove(iTicketDispenser);
        fireAdministratorListenerTicketDispenserRemoved(iTicketDispenser);
    }

    @Override
    public void addAdministratorListener(AdministratorListener administratorListener) {
        getAdministratorListeners().add(administratorListener);
    }

    @Override
    public void removeAdministratorListener(AdministratorListener administratorListener) {
        getAdministratorListeners().remove(administratorListener);
    }

    @Override
    public void fireAdministratorListenerTicketOfficeChanged(ITicketOffice ticketOffice) {
        for (AdministratorListener listener: getAdministratorListeners()) {
            listener.ticketOfficeChanged(ticketOffice);
        }
    }
    @Override
    public void fireAdministratorListenerTicketOfficeAdded(ITicketOffice ticketOffice) {
        for (AdministratorListener listener: getAdministratorListeners()) {
            listener.ticketOfficeAdded(ticketOffice);
        }
    }

    @Override
    public void fireAdministratorListenerTicketOfficeRemoved(ITicketOffice ticketOffice) {
        for (AdministratorListener listener: getAdministratorListeners()) {
            listener.ticketOfficeRemoved(ticketOffice);
        }
    }

    @Override
    public void fireAdministratorListenerCustomerChanged(ICustomer customer) {
        for (AdministratorListener listener: getAdministratorListeners()) {
            listener.customerChanged(customer);
        }
    }
    @Override
    public void fireAdministratorListenerTicketDispenserChanged(ITicketDispenser ticketDispenser) {
        for (AdministratorListener listener: getAdministratorListeners()) {
            listener.ticketDispenserChanged(ticketDispenser);
        }
    }
    @Override
    public void fireAdministratorListenerTicketDispenserAdded(ITicketDispenser ticketDispenser) {
        for (AdministratorListener listener: getAdministratorListeners()) {
            listener.ticketDispenserAdded(ticketDispenser);
        }
    }

    @Override
    public void fireAdministratorListenerTicketDispenserRemoved(ITicketDispenser ticketDispenser) {
        for (AdministratorListener listener: getAdministratorListeners()) {
            listener.ticketDispenserRemoved(ticketDispenser);
        }
    }

    @Override
    public void fireAdministratorListenerCustomerAdded(ICustomer customer) {
        for (AdministratorListener listener: getAdministratorListeners()) {
            listener.customerAdded(customer);
        }
    }

    @Override
    public void fireAdministratorListenerCustomerRemoved(ICustomer customer) {
        for (AdministratorListener listener: getAdministratorListeners()) {
            listener.customerRemoved(customer);
        }
    }


    /*
    ------------------------------------------------------------------------------------------------------
    -- méthode de ITicketOfficeAdministrator
    --------------------------------------------------------------------------------------------------
     */

    @Override
    public void removeCustomer(int idCustomer){
        ICustomer customer = getCustomer(idCustomer);
        if (customer == null) return;

        if (customer.getCustomerState() == EnumStateCustomer.IS_EXPECTED){
            customer.setState(EnumStateCustomer.MISSED_APPOINTMENT);
        }

        if (customer.getCustomerState() == EnumStateCustomer.SUPPORTED){
            for (ITicketOffice ticketOffice: getTicketOffices()) {
                if (ticketOffice.getTheExpectedCustomer() == customer.getId()){
                    ticketOffice.doNothing();
                }
            }
            customer.leave();
        }
        fireAdministratorListenerCustomerRemoved(customer);
        getListCustomer().remove(customer);


    }

    @Override
    public void callCustomer(int idCustomer, int idTicketOffice) {
        if (getCustomer(idCustomer) == null) return;
        getCustomer(idCustomer).call(idTicketOffice);

    }

    @Override
    public int getFirstIdCustomerOnHold() {
        if (getListTicketNumber().isEmpty()) return -1;
        int id =  getListTicketNumber().get(0);
        getListTicketNumber().remove(0);
        return id;
    }


    /*
    ------------------------------------------------------------------------------------------------------
    -- méthode de TicketOfficeListener
    --------------------------------------------------------------------------------------------------
     */
    @Override
    public void StateTicketOfficeChanged(EnumStateTicketOffice oldState, EnumStateTicketOffice newState, ITicketOffice ticketOffice) {
        if (newState == EnumStateTicketOffice.CLOSE){
            if (oldState == EnumStateTicketOffice.ON_HOLD){
                for (ICustomer customer: getListCustomer()) {
                    if (customer.getId() == ticketOffice.getTheExpectedCustomer()){
                        customer.setState(EnumStateCustomer.MISSED_APPOINTMENT);
                    }
                }
            }

            removeTicketOffice(ticketOffice);
        }
        fireAdministratorListenerTicketOfficeChanged(ticketOffice);
    }


    /*
    ------------------------------------------------------------------------------------------------------
    -- méthode de CustomerListener
    --------------------------------------------------------------------------------------------------
     */
    @Override
    public void stateCustomerChanged(EnumStateCustomer oldSate, EnumStateCustomer newState, ICustomer customer) {
        switch (newState) {
            case SUPPORTED-> {
                for (ITicketOffice iTicketOffice: getTicketOffices()) {
                    if (customer.getIdTicketOffice() == iTicketOffice.getId()){
                        iTicketOffice.supportsTheCustomer();
                    }
                }
            }
            case LEFT -> {
                if (oldSate == EnumStateCustomer.SUPPORTED){
                    for (ITicketOffice ticketOffice: getTicketOffices()) {
                        if (ticketOffice.getId() == customer.getId()){
                            ticketOffice.doNothing();
                        }
                    }
                }
            }
        }

        fireAdministratorListenerCustomerChanged(customer);

    }

    /*
    ------------------------------------------------------------------------------------------------------
    -- méthode de TicketDispenserListener
    --------------------------------------------------------------------------------------------------
     */


    @Override
    public void ticketWasGiven(int idCustomer, ITicketDispenser ticketDispenser) {
        addCustomer(idCustomer, EnumCustomerFactory.WINDOW_CUSTOMER);
        getListTicketNumber().add(idCustomer);


    }

    @Override
    public void StateTicketDispenserChanged(ITicketDispenser ticketDispenser, EnumStateTicketDispenser oldState, EnumStateTicketDispenser newState) {
        if (newState == EnumStateTicketDispenser.CLOSE){
            removeTicketDispenser(ticketDispenser);
        }

        fireAdministratorListenerTicketDispenserChanged(ticketDispenser);

    }




    /*
    ------------------------------------------------------------------------------------------------------
    -- autres méthode
    --------------------------------------------------------------------------------------------------
     */

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (ICustomer customer: getListCustomer()){
            s.append(customer.toString());
        }


        return "liste de client = {" + s + " }";
    }

}
