package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.MessageType;

public class ServerText extends Message {
    private final String text;

    public ServerText(String text) {
        messageType = MessageType.TEXT;
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
