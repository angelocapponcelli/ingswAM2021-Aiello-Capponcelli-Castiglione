package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class DiscardResourceMessage extends ClientMessage{

    public DiscardResourceMessage(String nickname) {
        super(nickname);
        messageType = MessageType.DISCARD_RESOURCE;
    }
}
