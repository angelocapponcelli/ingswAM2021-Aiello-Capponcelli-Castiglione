package it.polimi.ingsw.networking.messages;

/**
 * Error message. Extends message
 */
public class ErrorMessage extends Message {
    private final ErrorType errorType;

    /**
     * Constructor
     * @param errorType the type of the error of this message
     */
    public ErrorMessage(ErrorType errorType) {
        messageType = MessageType.ERROR;
        this.errorType = errorType;
    }

    /**
     *
     * @return the type of the error of this message
     */
    public ErrorType getErrorType() {
        return errorType;
    }
}
