package administrator.backend;

import customer.backend.ICustomer;
import ticketDispenser.backend.ITicketDispenser;
import ticketOffice.backend.ITicketOffice;

public interface AdministratorListener {

    void ticketOfficeChanged(ITicketOffice ticketOffice);

    void ticketOfficeAdded(ITicketOffice ticketOffice);
    void ticketOfficeRemoved(ITicketOffice ticketOffice);


    void customerChanged(ICustomer customer);

    void customerAdded(ICustomer customer);

    void customerRemoved(ICustomer customer);

    void ticketDispenserChanged(ITicketDispenser ticketDispenser);

    void ticketDispenserAdded(ITicketDispenser ticketDispenser);

    void ticketDispenserRemoved(ITicketDispenser ticketDispenser);
}
