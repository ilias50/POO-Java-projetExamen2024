package ticketOffice.backend;




public interface ITicketOfficeAdministrator {


    int getFirstIdCustomerOnHold();

    void removeCustomer(int idCustomer);

    void callCustomer(int idCustomer, int idTicketOffice);



}
