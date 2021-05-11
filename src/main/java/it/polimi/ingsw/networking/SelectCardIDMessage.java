package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class SelectCardIDMessage extends Message{
    protected Integer id; /** o posizione*/

    public SelectCardIDMessage(RealPlayer realPlayer, Integer id){
        super(realPlayer);
        this.id= id;
    }
    @Override
    public Boolean check(){
        return true;
    }
}
