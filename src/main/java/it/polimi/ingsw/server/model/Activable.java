package it.polimi.ingsw.server.model;

/**
 * The interface Activable.
 * Implemented by items that can be activated like production power outputs and special abilities.
 */
public interface Activable {
    /**
     * Performs the activation.
     *
     * @param realPlayer The player who performs the activation.
     */
    void onActivation(RealPlayer realPlayer);
}
