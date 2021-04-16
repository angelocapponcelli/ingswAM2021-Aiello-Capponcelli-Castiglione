package it.polimi.ingsw.server.model;

/**
 * The interface Payable.
 * Implemented by items that must be payed by the player like development card's cost and production power's input
 */
public interface Payable {
    void pay(RealPlayer realPlayer);
}
