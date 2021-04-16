package it.polimi.ingsw.server.model;

import java.util.List;

public class SinglePlayerGame extends Game{
    public SinglePlayerGame(Integer id, List<Player> players, GlobalBoard globalBoard, Player currentPlayer, Player winner) {
        super(id, players, globalBoard, currentPlayer, winner);
    }

    /** needs to be controlled this last method*/

    public void endGame(Player player){
        setWinner(player);
        /** need to control if it is a realplayer or it is lorenzo*/
    }
}
