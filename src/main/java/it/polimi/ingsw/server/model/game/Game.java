package it.polimi.ingsw.server.model.game;

import it.polimi.ingsw.server.model.globalBoard.GlobalBoard;
import it.polimi.ingsw.server.model.player.Player;
import it.polimi.ingsw.utils.exceptions.PlayerWithSameNameException;
import it.polimi.ingsw.utils.exceptions.ReachedMaxNumberOfPlayersException;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract Game class that extends Observable. This class contains the number of players, the list of players, the global board
 * which is used by the players to play, the player that wins the game and the current player that has the right to play the turn.
 */
public abstract class Game extends Observable {
    private final Integer maxPlayerNumber;
    private final List<Player> players;
    private final GlobalBoard globalBoard;
    private Player winner;
    private Player currentPlayer;

    /**
     * Class constructor. The class is abstract.
     * @param maxPlayerNumber the number of players of the game
     */

    public Game(Integer maxPlayerNumber) {
        this.maxPlayerNumber = maxPlayerNumber;
        players = new ArrayList<>();
        globalBoard = new GlobalBoard();
        currentPlayer = null;
        winner = null;
    }


    /**
     * Add a player to the game.
     *
     * @param player the player to be added
     * @throws ReachedMaxNumberOfPlayersException when a player tries to be added to an already full game.
     * @throws PlayerWithSameNameException        when a player with the same nickname already is in the game.
     */
    public void addPlayer(Player player) throws Exception {
        if (players.size() == maxPlayerNumber) {
            throw new ReachedMaxNumberOfPlayersException();
        }
        if (players.stream().map(Player::getNickName).collect(Collectors.toList()).contains(player.getNickName())) {
            throw new PlayerWithSameNameException();
        }
        players.add(player);
    }

    /**
     * Gets the list of players that plays the game.
     * @return players of this game
     */

    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Gets the player that has the right to play the turn.
     * @return currentPlayer of this game
     */

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the new player that can play the turn.
     * @param player who can plays the turn
     */

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    /**
     * Gets global board of this game.
     * @return globalboard of this game
     */

    public GlobalBoard getGlobalBoard() {
        return globalBoard;
    }

    /**
     * Gets the player that at the end of the game has the most victory points.
     * @return winner of this game
     */

    public Player getWinner() {
        return winner;
    }

    /**
     * Sets the winner of the game.
     * @param player who has the most victory points at the end of the game
     */

    public void setWinner(Player player) {
        winner = player;
    }

    /**
     * Activates the ending of the game.
     * @param player the player who performs the action that causes the end of the game.
     */

    public abstract void endGame(Player player);

    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        globalBoard.addObserver(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        super.removeObserver(obs);
        globalBoard.removeObserver(obs);
    }
}