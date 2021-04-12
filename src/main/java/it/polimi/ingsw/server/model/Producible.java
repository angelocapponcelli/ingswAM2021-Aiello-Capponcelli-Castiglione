package it.polimi.ingsw.server.model;

/**
 * The interface Producible.
 * Implemented by items that can be produced like resources and faith.
 */
public interface Producible {
    /**
     * Performs the production.
     *
     * @param player The player who performs the production.
     */
    void onProduction(Player player);
}
