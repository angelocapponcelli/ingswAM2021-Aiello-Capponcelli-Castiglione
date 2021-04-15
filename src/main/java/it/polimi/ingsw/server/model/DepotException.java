package it.polimi.ingsw.server.model;

/**
 * Exception launched by depots during add, remove, swap operations
 */

public class DepotException extends Exception {
    public DepotException(){ super(); }
    public DepotException(String s){ super(s); }
}