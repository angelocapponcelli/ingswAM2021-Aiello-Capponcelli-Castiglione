package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

/**
 * Discard Any type. Message
 */
public class DiscardAny extends ClientMessage{
    /**
     * Class constructor
     * @param nickname the nickname of the real player
     */
    public DiscardAny(String nickname) {
        super(nickname);
        messageType = MessageType.DISCARD_ANY;
    }
}
