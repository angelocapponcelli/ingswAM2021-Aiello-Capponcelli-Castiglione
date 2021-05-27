package it.polimi.ingsw.networking.messages;

public class ErrorMessage extends Message {
    String errorMessage;
    public ErrorMessage(String errorMessage) {
        messageType = MessageType.ERROR;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
