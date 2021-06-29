package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

/**
 * Special ability abstract class. Every ability is correlated to a specific resource.
 */
public abstract class SpecialAbility {
    protected Resource resource;

    /**
     * Activates this ability.
     * @param player that activates this special ability
     */
    public void onActivation(RealPlayer player) {

    }

    /**
     * Gets resource of this ability
     * @return resource of this ability
     */

    public Resource getResource() {
        return resource;
    }
}
