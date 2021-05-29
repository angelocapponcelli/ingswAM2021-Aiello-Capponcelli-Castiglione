package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.networking.messages.MessageType;

public class RequestMessage extends ServerMessage{
    public RequestMessage(MessageType messageType){
        this.messageType = messageType;
    }
}
