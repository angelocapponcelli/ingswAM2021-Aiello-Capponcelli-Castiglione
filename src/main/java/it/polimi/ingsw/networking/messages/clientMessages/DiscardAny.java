package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class DiscardAny extends ClientMessage{
    public DiscardAny(String nickname) {
        super(nickname);
        messageType = MessageType.DISCARD_ANY;
    }
}
