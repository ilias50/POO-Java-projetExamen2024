package ticketOffice.factory;

import garner.IGarner;
import ticketOffice.backend.ITicketOffice;
import ticketOffice.backend.ITicketOfficeAdministrator;
import ticketOffice.backend.TicketOffice;
import ticketOffice.factory.exception.TicketOfficeFactoryException;
import ticketOffice.frontend.PanTicketOffice;
import ticketOffice.frontend.WindowTicketOffice;

public abstract class TicketOfficeFactory {

    public static ITicketOffice createTicketOffice(EnumTypeOfTicketOffice typeOfTicketOffice, ITicketOfficeAdministrator ticketOfficeAdministrator, IGarner garner) throws TicketOfficeFactoryException {
        switch (typeOfTicketOffice){
            case TICKET_OFFICE -> {
                return new TicketOffice(ticketOfficeAdministrator, garner);
            }
            case PAN_TICKET_OFFICE -> {
                return new PanTicketOffice(ticketOfficeAdministrator, garner);
            }
            case WINDOW_TICKET_OFFICE -> {
                return new WindowTicketOffice(ticketOfficeAdministrator, garner);
            }
            default -> throw new TicketOfficeFactoryException("le type de guichet est invalide ");
        }
    }
}
