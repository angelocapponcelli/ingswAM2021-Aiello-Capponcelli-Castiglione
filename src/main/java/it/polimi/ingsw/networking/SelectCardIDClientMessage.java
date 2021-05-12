package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class SelectCardIDClientMessage extends ClientMessage {
    protected Integer id; /** o posizione*/

    public SelectCardIDClientMessage(RealPlayer realPlayer, Integer id){
        super(realPlayer);
        this.id= id;
    }
    @Override
    public Boolean check(){
        return true;
    }
}
