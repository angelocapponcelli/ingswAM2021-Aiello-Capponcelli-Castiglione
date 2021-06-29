package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

/**
 * Special ability that can be activated when you play a LeaderCard
 * It allow to have an extra depot that contain resources of the same type
 */
public class SpecialExtraDepot extends SpecialAbility {

    /**
     * Class constructor. Instantiates a new Special Extra Depot.
     * @param resource Specify the special resource type
     */
    public SpecialExtraDepot(Resource resource) {
        this.resource = resource;
    }

    /**
     * activate special ability that is add an extra depot to the player
     *
     * @param player who special ability is activated to
     */
    @Override
    public void onActivation(RealPlayer player) {
        player.getPersonalBoard().getSpecialDepots().addSpecialContainer(resource.getResourceType());
    }
}
