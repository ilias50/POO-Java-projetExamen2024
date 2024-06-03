package ticketOffice.backend;
import garner.IGarner;
import garner.exception.GarnerIsNullException;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code TicketOffice} class represents a ticket office in the system.
 * It manages the ticket office's state, including transitions between states and customer interactions.
 */
public class TicketOffice extends Consumer implements ITicketOffice {
    static int numberForId = 0;

    private int id;
    private EnumStateTicketOffice state = EnumStateTicketOffice.DO_NOTHING;
    private final Set<TicketOfficeListener> listeners = new HashSet<>();
    private ITicketOfficeAdministrator administrator;
    private final int numberForDo_Nothing = -1;
    private int theExpectedCustomer = numberForDo_Nothing;
    private int numberOfCallBack = 0;
    private long timeSinceOnHold = 0;
    private long timeSinceLastCallBack = 0;

    /**
     * Constructs a new {@code TicketOffice} with the specified administrator and garner.
     *
     * @param administrator the administrator for the ticket office
     * @param garner        the garner to use
     */
    public TicketOffice(ITicketOfficeAdministrator administrator, IGarner garner) {
        super(garner);
        setAdministrator(administrator);
        setId(++numberForId);
        doNothing();
    }
    @Override
    public long getTimeSinceLastCallBack() {
        return timeSinceLastCallBack;
    }

    @Override
    public void close() {
        setState(EnumStateTicketOffice.CLOSE);
    }


    private void setTimeSinceLastCallBack(long timeSinceLastCallBack) {
        this.timeSinceLastCallBack = timeSinceLastCallBack;
    }
    @Override
    public long getTimeSinceOnHold() {
        return timeSinceOnHold;
    }

    private void setTimeSinceOnHold(long timeSinceOnHold) {
        this.timeSinceOnHold = timeSinceOnHold;
    }

    public ITicketOfficeAdministrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(ITicketOfficeAdministrator administrator) {
        this.administrator = administrator;
    }
    @Override
    public int getNumberForDo_Nothing() {
        return numberForDo_Nothing;
    }
    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /**
     * Gets the current state of the ticket office.
     *
     * @return the current state of the ticket office
     */
    @Override
    public EnumStateTicketOffice getStateTicketOffice() {
        return state;
    }

    /**
     * Gets the ID of the expected customer.
     *
     * @return the ID of the expected customer
     */
    @Override
    public int getTheExpectedCustomer() {
        return theExpectedCustomer;
    }

    /**
     * Sets the ID of the expected customer.
     *
     * @param theExpectedCustomer the ID of the expected customer
     */
    public void setTheExpectedCustomer(int theExpectedCustomer) {
        this.theExpectedCustomer = theExpectedCustomer;
    }


    /**
     * Sets the state of the ticket office and notifies listeners of the state change.
     *
     * @param state the new state of the ticket office
     */
    private void setState(EnumStateTicketOffice state) {
        EnumStateTicketOffice oldState = getStateTicketOffice();
        this.state = state;
        fireTicketOfficeListener(oldState, getStateTicketOffice());
        if (getStateTicketOffice() == EnumStateTicketOffice.ON_HOLD) {
            setTimeSinceOnHold(System.currentTimeMillis());
        }
    }


    public Set<TicketOfficeListener> getListeners() {
        return listeners;
    }
    @Override
    public int getNumberOfCallBack() {
        return numberOfCallBack;
    }

    public void setNumberOfCallBack(int numberOfCallBack) {
        this.numberOfCallBack = numberOfCallBack;
    }
    @Override
    public void doNothing(){
        setState(EnumStateTicketOffice.DO_NOTHING);
        setTheExpectedCustomer(getNumberForDo_Nothing());
    }
    @Override
    public void addTicketOfficeListener(TicketOfficeListener ticketOfficeListener){
        getListeners().add(ticketOfficeListener);
    }
    @Override
    public void removeTicketOfficeListener(TicketOfficeListener ticketOfficeListener){
        getListeners().remove(ticketOfficeListener);

    }

    /**
     * Notifies all listeners that the state of the ticket office has changed.
     *
     * @param oldState the old state of the ticket office
     * @param newState the new state of the ticket office
     */
    @Override
    public void fireTicketOfficeListener(EnumStateTicketOffice oldState, EnumStateTicketOffice newState){
        for (TicketOfficeListener listener: getListeners()) {
            listener.StateTicketOfficeChanged(oldState, newState, this);
        }

    }

    @Override
    public void supportsTheCustomer() {
        setState(EnumStateTicketOffice.MANAGE_CLIENT);
    }
    @Override
    public void callNextCustomer() {
        if (getTheExpectedCustomer() != getNumberForDo_Nothing()){
            getAdministrator().removeCustomer(getTheExpectedCustomer());
        }


        try {
            if (!removeProduct(1)){
                doNothing();
                return;
            }
        } catch (GarnerIsNullException e) {
            throw new RuntimeException(e);
        }

        setTimeSinceLastCallBack(0);
        setNumberOfCallBack(0);
        setTimeSinceOnHold(System.currentTimeMillis());
        int idCustomer = getAdministrator().getFirstIdCustomerOnHold();
        setTheExpectedCustomer(idCustomer);
        setState(EnumStateTicketOffice.ON_HOLD);
        getAdministrator().callCustomer(getTheExpectedCustomer(), getId());
    }
    @Override
    public void callBackCustomer(){
        getAdministrator().callCustomer(getTheExpectedCustomer(), getId());
        setNumberOfCallBack(getNumberOfCallBack()+1);
        setTimeSinceLastCallBack(System.currentTimeMillis());
    }

    @Override
    public void startBreak() {
        setState(EnumStateTicketOffice.ON_BREAK);
        if (getTheExpectedCustomer() != getNumberForDo_Nothing()){
            getAdministrator().removeCustomer(getTheExpectedCustomer());
        }

    }

    @Override
    public void endOfBreak() {
        setState(EnumStateTicketOffice.DO_NOTHING);
        setTheExpectedCustomer(getNumberForDo_Nothing());

    }


    @Override
    public String toString() {
        return "TicketOffice{" +
                "id=" + id +
                ", state=" + state +
                ", theExpectedCustomer=" + theExpectedCustomer +
                '}';
    }


}
