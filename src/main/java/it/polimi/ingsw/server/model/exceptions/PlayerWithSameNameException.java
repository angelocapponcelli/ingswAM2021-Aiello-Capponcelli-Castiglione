package it.polimi.ingsw.server.model.exceptions;

public class PlayerWithSameNameException extends Exception {
    public PlayerWithSameNameException() {
        super("A player with the same name already exists");
    }
}
