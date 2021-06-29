package it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class JoinGameMessage extends BeforeGameMessage {
    private final int gameID;

    public JoinGameMessage(int gameID) {
        messageType = MessageType.JOIN_GAME;
        this.gameID = gameID;
    }

    public int getGameId() {
        return gameID;
    }

}
