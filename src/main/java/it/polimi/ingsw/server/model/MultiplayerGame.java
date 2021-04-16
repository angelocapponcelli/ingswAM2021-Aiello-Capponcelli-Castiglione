package it.polimi.ingsw.server.model;

import java.util.List;

public class MultiplayerGame extends Game{

    public MultiplayerGame(Integer id, List<Player> players, GlobalBoard globalBoard, Player currentPlayer, Player winner) {
        super(id, players, globalBoard, currentPlayer, winner);
    }


    /**to do the doLastTurn method*/
}
