package garner.exception;

public class GarnerInvalideNumberException extends Exception {

    public GarnerInvalideNumberException() {
    }

    public GarnerInvalideNumberException(String message) {
        super(message);
    }

    public GarnerInvalideNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
