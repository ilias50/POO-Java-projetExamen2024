package customer.factory;

import customer.backend.Customer;
import customer.backend.ICustomer;
import customer.factory.Exception.CustomerTypeNotFoundException;
import customer.frontend.PanCustomer;
import customer.frontend.WindowCustomer;

public abstract class CustomerFactory {

    public static ICustomer createCustomer(EnumCustomerFactory customerFactory, int idCustomer) throws CustomerTypeNotFoundException {
       switch (customerFactory){
           case SIMPLE_CUSTOMER -> {
               return new Customer(idCustomer);
           }
           case PANEL_CUSTOMER -> {
               return new PanCustomer(idCustomer);
           }
           case WINDOW_CUSTOMER -> {
               return new WindowCustomer(idCustomer);
           }
           default -> throw new CustomerTypeNotFoundException("le type de client choisi est invalide");
       }

    }
}
