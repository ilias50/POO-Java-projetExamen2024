package ticketDispenser.exception;

public class TicketDispenserException extends Exception{

    public TicketDispenserException(String message) {
        super(message);
    }

    public TicketDispenserException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getInfo(){
        return "envoie une erreur si des garner ou numberManager dans TicketDispenser est null";
    }
}
