package customer.backend;

public interface CustomerListener {

    void stateCustomerChanged(EnumStateCustomer oldSate, EnumStateCustomer newState, ICustomer customer);

}
