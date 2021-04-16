package it.polimi.ingsw.server.model;
/** has to be finished*/
import java.util.List;

abstract class Game {
    private Integer id;
    private List<Player> players;
    private GlobalBoard globalBoard;
    private Player currentPlayer;
    private Player winner;

    public Game(Integer id, List<Player> players, GlobalBoard globalBoard, Player currentPlayer, Player winner){
        this.id = id;
        this.players= players;
        this.globalBoard=globalBoard;
        this.currentPlayer=currentPlayer;
        this.winner=winner;
    }

    public Integer getId(){
        return id;
    }
    public List<Player> getPlayers(){
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GlobalBoard getGlobalBoard() {
        return globalBoard;
    }

    public Player getWinner() {
        return winner;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setWinner(Player player) {
        this.winner = player;
    }
}

