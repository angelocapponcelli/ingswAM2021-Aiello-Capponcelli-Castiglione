package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.server.Server;

public class LastTurnMessage extends ServerMessage {
    private final String playerNickName;

    public LastTurnMessage(String playerNickName) {
        this.playerNickName = playerNickName;
    }

    public String getPlayerNickName() {
        return playerNickName;
    }
}
