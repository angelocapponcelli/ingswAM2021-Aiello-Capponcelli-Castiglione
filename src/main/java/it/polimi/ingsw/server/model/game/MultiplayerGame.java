package it.polimi.ingsw.server.model.game;


import it.polimi.ingsw.server.model.player.Player;

/**
 * Multiplayer Class extends the abstract class Game.
 */

public class MultiplayerGame extends Game {

    /**
     * Class Constructor.
     * @param maxPlayerNumber that can join the game.
     */

    public MultiplayerGame(Integer maxPlayerNumber) {
        super(maxPlayerNumber);
    }

    /**
     *
     * @param player the player who performs the action that causes the end of the game
     */
    @Override
    public void endGame(Player player) {

    }

}
