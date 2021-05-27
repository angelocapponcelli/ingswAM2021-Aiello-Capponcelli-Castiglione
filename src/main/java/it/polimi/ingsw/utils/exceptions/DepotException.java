package it.polimi.ingsw.utils.exceptions;

/**
 * Exception launched by depots during add, remove, swap operations
 */

public class DepotException extends Exception {
    public DepotException() {
        super();
    }

    public DepotException(String s) {
        super(s);
    }
}