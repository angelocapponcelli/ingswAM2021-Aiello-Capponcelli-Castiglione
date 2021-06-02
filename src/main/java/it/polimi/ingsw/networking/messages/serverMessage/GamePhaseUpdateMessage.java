package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.networking.messages.MessageType;

public class GamePhaseUpdateMessage extends ServerMessage{
    public GamePhaseUpdateMessage(MessageType messageType){
        this.messageType = messageType;
    }
}
