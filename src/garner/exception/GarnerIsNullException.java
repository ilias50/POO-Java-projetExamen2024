package garner.exception;

public class GarnerIsNullException extends Exception{

    public GarnerIsNullException() {
    }

    public GarnerIsNullException(String message) {
        super(message);
    }

    public GarnerIsNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
