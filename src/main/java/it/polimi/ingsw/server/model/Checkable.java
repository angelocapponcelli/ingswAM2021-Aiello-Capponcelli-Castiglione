package it.polimi.ingsw.server.model;

/**
 * The interface Checkable.
 * Implemented by items that must be checked.
 */
public interface Checkable {
    /**
     * Performs the checking.
     *
     * @param realPlayer The player on which perform the checking.
     */
    boolean check(RealPlayer realPlayer);
}
