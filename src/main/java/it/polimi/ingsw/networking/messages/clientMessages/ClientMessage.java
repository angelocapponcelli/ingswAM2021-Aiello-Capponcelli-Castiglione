package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.Message;

/**
 * Message sent by the client.
 */
public abstract class ClientMessage extends Message {
    protected final String nickname;

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     */
    public ClientMessage(String nickname) {
        this.nickname = nickname;
    }

    /**
     *
     * @return the nickname of the real player
     */
    public String getNickname() {
        return this.nickname;
    }
}
