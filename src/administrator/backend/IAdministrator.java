package administrator.backend;

import customer.backend.ICustomer;
import customer.factory.EnumCustomerFactory;
import garner.IGarner;
import ticketDispenser.backend.ITicketDispenser;
import ticketOffice.backend.ITicketOffice;

import java.util.List;
import java.util.Set;

public interface IAdministrator {

    List<ICustomer> getListCustomer();
    Set<ITicketOffice> getTicketOffices();

    Set<ITicketDispenser> getTicketDispensers();

    void addITicketOffice(ITicketOffice iTicketOffice);

    void removeTicketOffice(ITicketOffice iTicketOffice);

    void addITicketDispenser(ITicketDispenser iTicketDispenser);

    void removeTicketDispenser(ITicketDispenser iTicketDispenser);

    void addAdministratorListener(AdministratorListener administratorListener);

    void removeAdministratorListener(AdministratorListener administratorListener);

    void fireAdministratorListenerTicketOfficeChanged(ITicketOffice ticketOffice);

    void fireAdministratorListenerTicketOfficeAdded(ITicketOffice ticketOffice);

    void fireAdministratorListenerTicketOfficeRemoved(ITicketOffice ticketOffice);

    void fireAdministratorListenerCustomerChanged(ICustomer customer);
    void fireAdministratorListenerCustomerAdded(ICustomer customer);

    void fireAdministratorListenerCustomerRemoved(ICustomer customer);
    void fireAdministratorListenerTicketDispenserChanged(ITicketDispenser ticketDispenser);
    void fireAdministratorListenerTicketDispenserAdded(ITicketDispenser ticketDispenser);

    void fireAdministratorListenerTicketDispenserRemoved(ITicketDispenser ticketDispenser);

    void addCustomer(int idCustomer, EnumCustomerFactory customerType);

    void addCustomerIntoList(ICustomer customer);

    IGarner getGarner();


}
