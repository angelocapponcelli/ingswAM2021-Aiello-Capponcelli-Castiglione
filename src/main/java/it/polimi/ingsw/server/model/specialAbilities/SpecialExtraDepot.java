package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

/**
 * Special ability that can be activated when you play a LeaderCard
 * It allow to have an extra depot that contain resources of the same type
 */
public class SpecialExtraDepot extends SpecialAbility {
    private final int capacity;

    /**
     * @param resource Specify the special resource type
     */
    public SpecialExtraDepot(Resource resource, int capacity) {
        this.resource = resource;
        this.capacity = capacity;
    }

    /**
     * activate special ability that is add an extra depot to the player
     *
     * @param player who special ability is activated to
     */
    @Override
    public void onActivation(RealPlayer player) {
        player.getPersonalBoard().getSpecialDepots().addSpecialContainer(resource.getResourceType(),capacity);
    }
}
