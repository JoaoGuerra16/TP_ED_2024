package tp_ed_2024.Collections.Exceptions;

public class EmptyCollectionException extends RuntimeException {

    /**
     * Creates an EmptyCollectionException with no message
     */
    public EmptyCollectionException() {
        super();
    }

    /**
     * Creates an EmptyCollectionException with the specified message
     *
     * @param message the message to be displayed with the exception
     */
    public EmptyCollectionException(String message) {
        super(message);
    }
}
