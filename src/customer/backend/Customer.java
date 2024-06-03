package customer.backend;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code Customer} class represents a customer in the system.
 * It manages the customer's state, arrival time, and interactions with listeners.
 */
public class Customer implements ICustomer {

    private int id;
    private long arrivingTime;
    private EnumStateCustomer state = EnumStateCustomer.ON_HOLD;
    private final Set<CustomerListener> listeners = new HashSet<>();

    private int idTicketOffice;
    private long pickUpTime;

    /**
     * Constructs a new {@code Customer} with the specified ID.
     *
     * @param id the ID of the customer
     */
    public Customer(int id) {
        setId(id);
        setArrivingTime(System.currentTimeMillis());
    }

    /**
     * Gets the pick-up time of the customer.
     *
     * @return the pick-up time in milliseconds
     */
    @Override
    public long getPickUpTime() {
        return pickUpTime;
    }

    /**
     * Sets the pick-up time of the customer.
     *
     * @param pickUpTime the pick-up time in milliseconds
     */

    private void setPickUpTime(long pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    /**
     * Gets the ID of the customer.
     *
     * @return the ID of the customer
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the customer.
     *
     * @param id the ID of the customer
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the arriving time of the customer.
     *
     * @return the arriving time in milliseconds
     */
    @Override
    public long getArrivingTime() {
        return arrivingTime;
    }

    /**
     * Sets the arriving time of the customer.
     *
     * @param arrivingTime the arriving time in milliseconds
     */
    private void setArrivingTime(long arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    /**
     * Gets the ID of the ticket office associated with the customer.
     *
     * @return the ID of the ticket office
     */
    @Override
    public int getIdTicketOffice() {
        return idTicketOffice;
    }

    /**
     * Sets the ID of the ticket office associated with the customer.
     *
     * @param idTicketOffice the ID of the ticket office
     */
    private void setIdTicketOffice(int idTicketOffice) {
        this.idTicketOffice = idTicketOffice;
    }

    /**
     * Gets the current state of the customer.
     *
     * @return the current state of the customer
     */
    @Override
    public EnumStateCustomer getCustomerState() {
        return state;
    }

    /**
     * Sets the state of the customer and notifies listeners of the state change.
     *
     * @param state the new state of the customer
     */
    @Override
    public void setState(EnumStateCustomer state) {
        if (state == this.state) return;
        if (this.state == EnumStateCustomer.LEFT) return;
        if (this.state == EnumStateCustomer.MISSED_APPOINTMENT && state != EnumStateCustomer.LEFT) return;
        EnumStateCustomer oldState = getCustomerState();
        this.state = state;
        fireCustomerListenerStateChanged(oldState, state);
    }

    /**
     * Gets the set of listeners for the customer.
     *
     * @return the set of listeners
     */
    private Set<CustomerListener> getListeners() {
        return listeners;
    }

    /**
     * Adds a listener to the customer.
     *
     * @param customerListener the listener to add
     */
    public void addCustomerListener(CustomerListener customerListener) {
        getListeners().add(customerListener);
    }

    /**
     * Removes a listener from the customer.
     *
     * @param customerListener the listener to remove
     */
    @Override
    public void removeCustomerListener(CustomerListener customerListener) {
        if (!getListeners().contains(customerListener)) return;
        getListeners().remove(customerListener);
    }

    /**
     * Notifies all listeners that the state of the customer has changed.
     *
     * @param oldState the old state of the customer
     * @param newState the new state of the customer
     */
    @Override
    public void fireCustomerListenerStateChanged(EnumStateCustomer oldState, EnumStateCustomer newState) {
        for (CustomerListener listener : getListeners()) {
            listener.stateCustomerChanged(oldState, newState, this);
        }
    }

    /**
     * Sets the state of the customer to {@code LEFT}.
     */
    @Override
    public void leave() {
        setState(EnumStateCustomer.LEFT);
    }

    /**
     * Sets the state of the customer to {@code SUPPORTED} and records the pick-up time.
     */
    @Override
    public void goToTheTicketOffice() {
        setState(EnumStateCustomer.SUPPORTED);
        setPickUpTime(System.currentTimeMillis());
    }

    /**
     * Sets the state of the customer to {@code IS_EXPECTED} and assigns a ticket office ID.
     *
     * @param idTicketOffice the ID of the ticket office
     */
    @Override
    public void call(int idTicketOffice) {
        setIdTicketOffice(idTicketOffice);
        setState(EnumStateCustomer.IS_EXPECTED);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", arrivingTime=" + arrivingTime +
                ", state=" + state +
                '}';
    }
}