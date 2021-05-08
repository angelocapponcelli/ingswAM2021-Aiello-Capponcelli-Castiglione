package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.player.Player;

/**
 * The type Single player game.
 */
public class SinglePlayerGame extends Game {
    /**
     * Instantiates a new Single player game.
     *
     * @param id the id of the game
     */
    public SinglePlayerGame(Integer id) {
        super(id, 2);
    }


    /**
     * @param player the player who performs the action that causes the end of the game
     */
    public void endGame(Player player) {
        setWinner(player);
    }
}
