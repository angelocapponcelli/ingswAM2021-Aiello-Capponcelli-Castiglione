package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class SelectDeckFromGridClientMessage extends ClientMessage {
    private Integer row;
    private Integer column;

    public SelectDeckFromGridClientMessage(RealPlayer realPlayer1, Integer row, Integer column) {
        super(realPlayer1);
        this.row=row;
        this.column=column;
    }
    @Override
    public Boolean check(){
        if(this.column<0 || this.column>3){
            return false;
        }
        if(this.row<0 || this.row>2){
            return false;
        }
        else
            return true;
    }
}
