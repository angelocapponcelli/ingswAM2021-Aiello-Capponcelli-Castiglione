package it.polimi.ingsw.server.model;


import it.polimi.ingsw.server.model.player.Player;

public class MultiplayerGame extends Game {

    public MultiplayerGame(Integer maxPlayerNumber) {
        super(maxPlayerNumber);
    }


    @Override
    public void endGame(Player player) {

    }

}
