package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;

public abstract class Message {
    protected RealPlayer realPlayer;
    public Message(RealPlayer realPlayer){
        this.realPlayer=realPlayer;
    }
    public Boolean check(){
        return true;
    }
}
