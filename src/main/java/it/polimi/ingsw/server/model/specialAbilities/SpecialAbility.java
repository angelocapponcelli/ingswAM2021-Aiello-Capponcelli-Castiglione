package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.client.view.reducedGameModel.SpecialAbilityType;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.utils.observer.Observable;

/**
 * Special ability abstract class. Every ability is correlated to a specific resource.
 */
public abstract class SpecialAbility{
    protected SpecialAbilityType specialAbilityType;
    protected Resource resource;

    /**
     * Activates this ability.
     * @param player that activates this special ability
     */
    public void onActivation(RealPlayer player) {
        player.addActivatedSpecialAbility(this);
    }

    /**
     * Gets resource of this ability
     * @return resource of this ability
     */

    public Resource getResource() {
        return resource;
    }

    /**
     * Gets the type of the special ability.
     *
     * @return the special ability type
     */
    public SpecialAbilityType getSpecialAbilityType() {
        return specialAbilityType;
    }
}
