package it.polimi.ingsw.networking.messages;

public class ErrorMessage extends Message {
    ErrorType errorType;

    public ErrorMessage(ErrorType errorType) {
        messageType = MessageType.ERROR;
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
