package tp_ed_2024.Collections.Exceptions;

public class ElementNotFoundException extends RuntimeException {

    /**
     * Creates a new ElementNotFoundException with no message.
     */
    public ElementNotFoundException() {
        super();
    }

    /**
     * Creates a new ElementNotFoundException with the specified message.
     *
     * @param message the message for this exception.
     */
    public ElementNotFoundException(String message) {
        super(message);
    }
}
