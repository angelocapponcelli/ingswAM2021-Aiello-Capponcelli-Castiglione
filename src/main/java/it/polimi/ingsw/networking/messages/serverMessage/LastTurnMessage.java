package it.polimi.ingsw.networking.messages.serverMessage;


import it.polimi.ingsw.networking.messages.MessageType;

public class LastTurnMessage extends ServerMessage {
    private final String playerNickName;

    public LastTurnMessage(String playerNickName) {
        messageType = MessageType.LAST_TURN_MESSAGE;
        this.playerNickName = playerNickName;
    }

    public String getPlayerNickName() {
        return playerNickName;
    }
}
