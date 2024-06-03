package ticketDispenser.backend;


public interface TicketDispenserListener {
    void ticketWasGiven(int idCustomer, ITicketDispenser ticketDispenser);

    void StateTicketDispenserChanged(ITicketDispenser ticketDispenser, EnumStateTicketDispenser oldState, EnumStateTicketDispenser newState);

}
