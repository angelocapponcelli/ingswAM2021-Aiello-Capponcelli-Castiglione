package it.polimi.ingsw.networking;

public class PlayersNumberMessage extends BeforeGameMessage {
    private final int playerNumber;

    public PlayersNumberMessage(Integer playerNumber){
        this.playerNumber=playerNumber;
    }

    @Override
    public Boolean check(){
        if(this.playerNumber>4 || this.playerNumber<1){
            return false;
        }
        else
            return true;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }
}
