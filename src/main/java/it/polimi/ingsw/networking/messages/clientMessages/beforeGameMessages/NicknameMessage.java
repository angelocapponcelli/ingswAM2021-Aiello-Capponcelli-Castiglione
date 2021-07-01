package it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.clientMessages.ClientMessage;

/**
 * Nickname message
 */
public class NicknameMessage extends ClientMessage {
    /**
     * Class constructor
     * @param nickname the real player's nickname
     */
    public NicknameMessage(String nickname) {
        super(nickname);
        messageType = MessageType.NICKNAME;
    }

    /**
     *
     * @return the nickname of the user
     */
    public String getNickname() {
        return nickname;
    }

}
