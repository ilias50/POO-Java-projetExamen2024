package customer.backend;


public interface ICustomer {

    void call(int idTicketOffice);
    void goToTheTicketOffice();
    void leave();
    void fireCustomerListenerStateChanged(EnumStateCustomer oldState, EnumStateCustomer newState);
    void addCustomerListener(CustomerListener customerListener);
    void removeCustomerListener(CustomerListener customerListener);

    EnumStateCustomer getCustomerState();
    void setState(EnumStateCustomer state);
    int getId();
    long getArrivingTime();

    long getPickUpTime();

    int getIdTicketOffice();

}
