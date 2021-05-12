package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;

import java.io.Serializable;

public abstract class ClientMessage implements Serializable {
    protected RealPlayer realPlayer;
    public ClientMessage(RealPlayer realPlayer){
        this.realPlayer=realPlayer;
    }
    public Boolean check(){
        return true;
    }
}
