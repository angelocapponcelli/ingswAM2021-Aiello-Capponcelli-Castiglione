package it.polimi.ingsw.server.model.game;

/*
 * has to be finished
 */

import it.polimi.ingsw.server.model.globalBoard.GlobalBoard;
import it.polimi.ingsw.server.model.player.Player;
import it.polimi.ingsw.utils.exceptions.PlayerWithSameNameException;
import it.polimi.ingsw.utils.exceptions.ReachedMaxNumberOfPlayersException;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Game extends Observable {
    private final Integer maxPlayerNumber;
    private final List<Player> players;
    private final GlobalBoard globalBoard;
    private Player winner;
    private Player currentPlayer;

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

    public List<Player> getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public GlobalBoard getGlobalBoard() {
        return globalBoard;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player player) {
        winner = player;
    }

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