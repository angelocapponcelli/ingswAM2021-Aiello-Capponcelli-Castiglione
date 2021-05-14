package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.player.RealPlayer;

import java.io.Serializable;

public abstract class ClientMessage extends Message {
    protected String nickname;
    public ClientMessage(String nickname){
        this.nickname=nickname;
    }
    public Boolean check(){
        return true;
    }
    public String getNickname(){
        return this.nickname;
    }
}
