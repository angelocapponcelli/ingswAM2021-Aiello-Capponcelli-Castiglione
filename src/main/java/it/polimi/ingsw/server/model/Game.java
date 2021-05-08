package it.polimi.ingsw.server.model;

/*
 * has to be finished
 */

import it.polimi.ingsw.server.model.exceptions.PlayerWithSameNameException;
import it.polimi.ingsw.server.model.exceptions.ReachedMaxNumberOfPlayersException;
import it.polimi.ingsw.server.model.globalBoard.GlobalBoard;
import it.polimi.ingsw.server.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Game {
    private final Integer id;
    private final Integer maxPlayerNumber;
    private final List<Player> players;
    private final GlobalBoard globalBoard;
    private final Player winner;
    private Player currentPlayer;

    public Game(Integer id, Integer maxPlayerNumber) {
        this.id = id;
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

    public Integer getId() {
        return id;
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

    }

    public void endGame(Player player) {
    }
}