package it.polimi.ingsw.networking.messages;

public class ErrorMessage extends Message{
    public ErrorMessage() {
        messageType = MessageType.ERROR;
    }
}
