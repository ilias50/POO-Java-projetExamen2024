package ticketDispenser.backend;


import garner.IGarner;
import garner.exception.GarnerInvalideNumberException;
import garner.exception.GarnerIsNullException;
import ticketDispenser.exception.TicketDispenserException;
import ticketDispenser.numberManager.INumberManager;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code TicketDispenser} class represents a ticket dispenser in the system.
 * It manages the ticket dispensing process, including state transitions and listener notifications.
 */
public class TicketDispenser extends Producer implements ITicketDispenser {

    static int numberForId = 1;

    private int id;
    private INumberManager numberManager;
    private final Set<TicketDispenserListener> listeners = new HashSet<>();
    private EnumStateTicketDispenser state = EnumStateTicketDispenser.OPEN;

    /**
     * Constructs a new {@code TicketDispenser} with the specified number manager and garner.
     *
     * @param numberManager the number manager to use
     * @param garner        the garner to use
     * @throws TicketDispenserException if the number manager is null
     */
    public TicketDispenser(INumberManager numberManager, IGarner garner) throws TicketDispenserException {
        super(garner);
        setNumberManager(numberManager);
        setId(numberForId++);
    }

    /**
     * Gets the ID of the ticket dispenser.
     *
     * @return the ID of the ticket dispenser
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Closes the ticket dispenser, changing its state to {@code CLOSE}.
     */
    @Override
    public void close() {
        setState(EnumStateTicketDispenser.CLOSE);
    }

    /**
     * Opens the ticket dispenser, changing its state to {@code OPEN}.
     */
    @Override
    public void open() {
        setState(EnumStateTicketDispenser.OPEN);
    }

    /**
     * Gets the current state of the ticket dispenser.
     *
     * @return the current state of the ticket dispenser
     */
    @Override
    public EnumStateTicketDispenser getStateTicketDispenser() {
        return state;
    }

    /**
     * Sets the state of the ticket dispenser and notifies listeners of the state change.
     *
     * @param state the new state of the ticket dispenser
     */
    @Override
    public void setState(EnumStateTicketDispenser state) {
        if (state == getStateTicketDispenser()) return;
        EnumStateTicketDispenser oldState = getStateTicketDispenser();
        this.state = state;
        fireTicketDispenserListenerStateChanged(oldState, getStateTicketDispenser());
    }

    /**
     * Sets the ID of the ticket dispenser.
     *
     * @param id the ID of the ticket dispenser
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the number manager associated with the ticket dispenser.
     *
     * @return the number manager
     */
    public INumberManager getNumberManager() {
        return numberManager;
    }

    /**
     * Gets the set of listeners for the ticket dispenser.
     *
     * @return the set of listeners
     */
    public Set<TicketDispenserListener> getListeners() {
        return listeners;
    }

    /**
     * Sets the number manager for the ticket dispenser.
     *
     * @param numberManager the number manager to set
     * @throws TicketDispenserException if the number manager is null
     */
    public void setNumberManager(INumberManager numberManager) throws TicketDispenserException {
        if (numberManager == null) throw new TicketDispenserException("numberManager ne peut pas être null");
        this.numberManager = numberManager;
    }

    /**
     * Adds a listener to the ticket dispenser.
     *
     * @param ticketDispenserListener the listener to add
     */
    @Override
    public void addTicketDispenserListener(TicketDispenserListener ticketDispenserListener) {
        getListeners().add(ticketDispenserListener);
    }

    /**
     * Removes a listener from the ticket dispenser.
     *
     * @param ticketDispenserListener the listener to remove
     */
    @Override
    public void removeTicketDispenserListener(TicketDispenserListener ticketDispenserListener) {
        getListeners().remove(ticketDispenserListener);
    }

    /**
     * Notifies all listeners that a ticket was given.
     *
     * @param idCustomer the ID of the customer who received the ticket
     */
    @Override
    public void fireTicketDispenserListenerTicketWasGiven(int idCustomer) {
        for (TicketDispenserListener listener : getListeners()) {
            listener.ticketWasGiven(idCustomer, this);
        }
    }

    /**
     * Notifies all listeners that the state of the ticket dispenser has changed.
     *
     * @param oldState the old state of the ticket dispenser
     * @param newState the new state of the ticket dispenser
     */
    @Override
    public void fireTicketDispenserListenerStateChanged(EnumStateTicketDispenser oldState, EnumStateTicketDispenser newState) {
        for (TicketDispenserListener listener : getListeners()) {
            listener.StateTicketDispenserChanged(this, oldState, newState);
        }
    }

    /**
     * Takes a ticket, adding a product and notifying listeners.
     */
    @Override
    public void takeTicket() {
        if (getStateTicketDispenser() == EnumStateTicketDispenser.CLOSE) return;
        try {
            addProduct(1);
        } catch (GarnerInvalideNumberException | GarnerIsNullException e) {
            return;
        }
        fireTicketDispenserListenerTicketWasGiven(getNumberManager().getNextNumber());
    }


    @Override
    public String toString() {
        return "TicketDispenser{" +
                "id=" + id +
                ", numberManager=" + numberManager +
                ", ticketDispenserListeners=" + listeners +
                '}';
    }
}
