package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class SelectDevelopmentCardIDClientMessage extends SelectCardIDClientMessage {
    public SelectDevelopmentCardIDClientMessage(RealPlayer realPlayer, Integer id) {
        super(realPlayer, id);
    }
    @Override
    public Boolean check(){
        if(this.id<0){
            return false;
        }
        return true;
    }
}
