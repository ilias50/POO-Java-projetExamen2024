package ticketDispenser.backend;

public interface ITicketDispenser {

    void setState(EnumStateTicketDispenser state);

    EnumStateTicketDispenser getStateTicketDispenser();

    void takeTicket();

    void addTicketDispenserListener(TicketDispenserListener ticketDispenserListener);

    void removeTicketDispenserListener(TicketDispenserListener ticketDispenserListener);

    void fireTicketDispenserListenerTicketWasGiven(int idCustomer);

    void fireTicketDispenserListenerStateChanged(EnumStateTicketDispenser oldState, EnumStateTicketDispenser newState);

    int getId();

    void close();

    void open();
}
