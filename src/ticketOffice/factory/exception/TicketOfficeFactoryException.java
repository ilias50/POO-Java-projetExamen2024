package ticketOffice.factory.exception;

public class TicketOfficeFactoryException extends Exception{

    public TicketOfficeFactoryException() {
    }

    public TicketOfficeFactoryException(String message) {
        super(message);
    }

    public TicketOfficeFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
