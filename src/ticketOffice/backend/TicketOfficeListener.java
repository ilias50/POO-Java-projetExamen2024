package ticketOffice.backend;

public interface TicketOfficeListener {

    void StateTicketOfficeChanged(EnumStateTicketOffice oldState, EnumStateTicketOffice newState, ITicketOffice ticketOffice);
}
