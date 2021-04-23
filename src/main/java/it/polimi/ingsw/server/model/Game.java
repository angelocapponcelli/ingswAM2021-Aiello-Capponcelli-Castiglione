package it.polimi.ingsw.server.model;

/*
 * has to be finished
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    private static Integer id;
    private static List<Player> players;
    private static GlobalBoard globalBoard;
    private static Player currentPlayer;
    private static Player winner;

    public Game(Integer id) {
        Game.id = id;
        players = new ArrayList<>();
        globalBoard = new GlobalBoard();
        currentPlayer = null;
        winner = null;
    }

    public static List<Player> getPlayers() {
        return players;
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

    public static void setWinner(Player player) {

    }

    public void endGame(Player player) {
    }
}