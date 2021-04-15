package it.polimi.ingsw.server.model;

/**
 * The interface Takeable.
 * Implemented by items that can be taken from the market tray like resources, faith and the white marble.
 */
public interface Takeable {

    void onTaking(RealPlayer realPlayer);
}
