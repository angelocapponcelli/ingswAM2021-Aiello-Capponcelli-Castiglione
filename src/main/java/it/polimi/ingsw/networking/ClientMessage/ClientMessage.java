package it.polimi.ingsw.networking.ClientMessage;

import it.polimi.ingsw.server.model.player.RealPlayer;

import java.io.Serializable;

public abstract class ClientMessage implements Serializable {
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
