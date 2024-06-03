package ticketDispenser.factory.exception;

public class TicketDispenserFactoryException extends Exception{

    public TicketDispenserFactoryException() {
    }

    public TicketDispenserFactoryException(String message) {
        super(message);
    }

    public TicketDispenserFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
