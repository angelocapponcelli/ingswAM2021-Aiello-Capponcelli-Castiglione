package it.polimi.ingsw.server.model.game;


import it.polimi.ingsw.networking.messages.serverMessage.LastTurnMessage;
import it.polimi.ingsw.server.controller.gameStates.GameState;
import it.polimi.ingsw.server.model.player.Player;

/**
 * Multiplayer game. It is one type of game with more than one player.
 */
public class MultiplayerGame extends Game {
    /**
     * to avoid that another player does the endGame routine after the player who just did it.
     */
    private boolean gameIsEnded = false;
    /**
     * Class constructor. Instantiates a new Multiplayer Game.
     * @param maxPlayerNumber the maximum number of player that can join this game
     */
    public MultiplayerGame(Integer maxPlayerNumber) {
        super(maxPlayerNumber);
    }

    /**
     * @param player the player who performs the action that causes the end of the game.
     */
    @Override
    public void endGame(Player player) {
        gameController.setCurrentGameState(GameState.END);
        if(!gameIsEnded){
            //notifyObserver(new LastTurnMessage(player.getNickName()));
            gameIsEnded=true;
        }

    }
}