package it.polimi.ingsw.networking.messages;

public class GenericTextMessage extends Message {
    private final String text;

    public GenericTextMessage(String text) {
        messageType = MessageType.TEXT;
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
