package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.networking.messages.MessageType;

public class ItIsMyTurnMessage extends ServerMessage{
    public ItIsMyTurnMessage() {
        messageType = MessageType.MY_TURN_MESSAGE;
    }
}
