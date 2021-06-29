package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

/**
 * Special Discount class. It extends SpecialAbility. This ability it is used when a player want to buy a Development card.
 */
public class SpecialDiscount extends SpecialAbility {
    /**
     * Class constructor. Instantiates a new Special Discount.
     * @param resource that characterizes this special ability
     */
    public SpecialDiscount(Resource resource) {
        this.resource = resource;
    }

    /**
     * It's a passive ability. It should be considered during the pay transaction
     * @param player that activates this special ability
     */
    @Override
    public void onActivation(RealPlayer player) {
    }

    /**
     * Gets resource of this special ability
     * @return resource that characterizes this ability
     */
    public Resource getResource() {
        return resource;
    }
}
