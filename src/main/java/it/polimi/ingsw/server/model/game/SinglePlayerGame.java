package it.polimi.ingsw.server.model.game;

import it.polimi.ingsw.server.model.player.Player;

/**
 * The type Single player game.
 */
public class SinglePlayerGame extends Game {
    /**
     * Instantiates a new Single player game.
     */
    public SinglePlayerGame() {
        super(2);
    }


    /**
     * @param player the player who performs the action that causes the end of the game
     */
    public void endGame(Player player) {
        setWinner(player);
    }
}
