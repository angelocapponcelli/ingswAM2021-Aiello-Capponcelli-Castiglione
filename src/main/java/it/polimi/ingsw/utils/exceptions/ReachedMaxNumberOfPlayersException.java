package it.polimi.ingsw.utils.exceptions;

public class ReachedMaxNumberOfPlayersException extends Exception {
    public ReachedMaxNumberOfPlayersException() {
        super("Already reached max number of players");
    }
}
