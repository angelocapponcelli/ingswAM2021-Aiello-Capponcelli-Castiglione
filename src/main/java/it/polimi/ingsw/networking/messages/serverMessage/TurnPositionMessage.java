package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.networking.messages.MessageType;

public class TurnPositionMessage extends ServerMessage {
    private final Integer turnPosition;

    public TurnPositionMessage(Integer turnPosition) {
        messageType = MessageType.TURN_POSITION_MESSAGE;
        this.turnPosition = turnPosition;
    }

    public Integer getTurnPosition() {
        return turnPosition;
    }
}
