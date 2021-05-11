package it.polimi.ingsw.networking;

public class PlayersNumberMessage extends BeforeGameMessage {
    private Integer number;

    public PlayersNumberMessage(Integer number){
        this.number=number;
    }

    @Override
    public Boolean check(){
        if(this.number>4 || this.number<1){
            return false;
        }
        else
            return true;
    }
}
