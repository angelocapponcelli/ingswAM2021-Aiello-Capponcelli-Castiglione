package it.polimi.ingsw.server.model;

/**
 * The interface Producible.
 * Implemented by items that can be produced like resources and faith.
 */
public interface Producible {

    void onProduction(RealPlayer realPlayer);
}
