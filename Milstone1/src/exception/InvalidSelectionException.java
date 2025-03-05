package exception;

public abstract class InvalidSelectionException extends Exception {
    public InvalidSelectionException() {
        super();
    }

    public InvalidSelectionException(String message) {
        super(message);
    }
}