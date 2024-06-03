package ticketOffice.backend;

import garner.exception.GarnerIsNullException;

public interface ITicketOffice {

    void callNextCustomer() throws GarnerIsNullException;

    void addTicketOfficeListener(TicketOfficeListener ticketOfficeListener);

    void removeTicketOfficeListener(TicketOfficeListener ticketOfficeListener);
    void fireTicketOfficeListener(EnumStateTicketOffice oldState, EnumStateTicketOffice newState);

    void callBackCustomer();

    void startBreak();

    void endOfBreak();


    void supportsTheCustomer();

    int getId();

    int getTheExpectedCustomer();

    EnumStateTicketOffice getStateTicketOffice();

    void doNothing();

    int getNumberForDo_Nothing();

    long getTimeSinceOnHold();

    int getNumberOfCallBack();

    long getTimeSinceLastCallBack();

    void close();

}
