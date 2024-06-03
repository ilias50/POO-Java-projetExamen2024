package ticketDispenser.factory;

import garner.IGarner;
import ticketDispenser.backend.ITicketDispenser;
import ticketDispenser.backend.TicketDispenser;
import ticketDispenser.exception.TicketDispenserException;
import ticketDispenser.factory.exception.TicketDispenserFactoryException;
import ticketDispenser.frontend.PanTicketDispenser;
import ticketDispenser.frontend.WindowTicketDispenser;
import ticketDispenser.numberManager.INumberManager;

public abstract class TicketDispenserFactory {

    public static ITicketDispenser createTicketDispenser(EnumTicketDispenserType ticketDispenserType, INumberManager numberManager, IGarner garner) throws TicketDispenserException, TicketDispenserFactoryException {
        switch (ticketDispenserType){
            case TICKET_DISPENSER -> {
                return new TicketDispenser(numberManager, garner);
            }
            case PAN_TICKET_DISPENSER -> {
                return new PanTicketDispenser(numberManager, garner);
            }
            case WINDOW_TICKET_DISPENSER -> {
                return new WindowTicketDispenser(numberManager, garner);
            }
            default -> {
                throw new TicketDispenserFactoryException("le type de distributeur est invalide");
            }
        }
    }
}
