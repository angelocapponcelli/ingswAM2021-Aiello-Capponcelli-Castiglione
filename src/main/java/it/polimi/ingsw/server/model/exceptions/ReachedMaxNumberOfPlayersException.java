package it.polimi.ingsw.server.model.exceptions;

public class ReachedMaxNumberOfPlayersException extends Exception{
    public ReachedMaxNumberOfPlayersException() {
        super("Already reached max number of players");
    }
}
