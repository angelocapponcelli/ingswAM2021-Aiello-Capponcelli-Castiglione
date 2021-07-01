package it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages;

import it.polimi.ingsw.networking.messages.MessageType;

/**
 * Join game message.
 */
public class JoinGameMessage extends BeforeGameMessage {
    private final int gameID;

    /**
     * Class constructor
     * @param gameID the id of the game to join
     */
    public JoinGameMessage(int gameID) {
        messageType = MessageType.JOIN_GAME;
        this.gameID = gameID;
    }

    /**
     *
     * @return the id of the game to join
     */
    public int getGameId() {
        return gameID;
    }

}
