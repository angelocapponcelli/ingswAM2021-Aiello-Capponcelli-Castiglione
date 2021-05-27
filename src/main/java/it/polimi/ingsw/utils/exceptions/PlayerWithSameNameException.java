package it.polimi.ingsw.utils.exceptions;

public class PlayerWithSameNameException extends Exception {
    public PlayerWithSameNameException() {
        super("A player with the same name already exists");
    }
}
