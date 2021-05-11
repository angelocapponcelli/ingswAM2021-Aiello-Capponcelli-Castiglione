package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class ChooseMainActionMessage extends Message{

    private String string;

    public ChooseMainActionMessage(RealPlayer realPlayer1, String s) {
        super(realPlayer1);
        this.string=s;
    }

    @Override
    public Boolean check(){
        if (this.string!="Take" || this.string!= "Buy" || this.string!= "Produce"){
            return false;
        }
        return true;
    }
}
