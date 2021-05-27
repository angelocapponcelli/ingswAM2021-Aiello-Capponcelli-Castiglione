package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class ClientText extends ClientMessage{
    String text;
    public ClientText(String nickname, String text) {
        super(nickname);
        messageType = MessageType.TEXT;
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
