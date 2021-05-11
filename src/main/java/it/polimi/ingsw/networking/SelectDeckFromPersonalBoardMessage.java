package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class SelectDeckFromPersonalBoardMessage extends Message{
    private Integer numberOfDeck;

    public SelectDeckFromPersonalBoardMessage(RealPlayer realPlayer) {
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
