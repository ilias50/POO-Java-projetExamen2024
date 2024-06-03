package customer.factory.Exception;

public class CustomerTypeNotFoundException extends Exception{

    public CustomerTypeNotFoundException(String message) {
        super(message);
    }

    public CustomerTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerTypeNotFoundException() {
    }
}
