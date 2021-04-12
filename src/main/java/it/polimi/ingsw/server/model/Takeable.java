package it.polimi.ingsw.server.model;

/**
 * The interface Takeable.
 * Implemented by items that can be taken from the market tray like resources, faith and the white marble.
 */
public interface Takeable {
    /**
     * Performs the taking from the market tray.
     *
     * @param player The player who performs the taking.
     */
    void onTaking(Player player);
}
