package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class SelectDeckFromPersonalBoardClientMessage extends ClientMessage {
    private Integer numberOfDeck;

    public SelectDeckFromPersonalBoardClientMessage(RealPlayer realPlayer) {
        super(realPlayer);
    }

    @Override
    public Boolean check(){
        if(this.numberOfDeck>2 || this.numberOfDeck<0){
            return false;
        }
        return true;
    }
}
