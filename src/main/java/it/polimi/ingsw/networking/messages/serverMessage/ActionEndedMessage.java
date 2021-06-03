package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.networking.messages.MessageType;

public class ActionEndedMessage extends ServerMessage{
    public ActionEndedMessage(){
        messageType = MessageType.ACTION_ENDED;
    }
}