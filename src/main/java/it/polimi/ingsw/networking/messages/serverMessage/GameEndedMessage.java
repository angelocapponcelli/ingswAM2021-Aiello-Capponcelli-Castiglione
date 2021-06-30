package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.networking.messages.MessageType;

public class GameEndedMessage extends ServerMessage{
    private final String winner;

    public GameEndedMessage(String winner) {
        messageType = MessageType.GAME_ENDED;
        this.winner = winner;
    }
}
