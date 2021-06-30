package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.client.view.reducedGameModel.SpecialAbilityType;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedLeaderCard;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedInHandLeaderCardMessage;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedSpecialDepotMessage;
import it.polimi.ingsw.client.view.reducedGameModel.SpecialAbilityType;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.utils.observer.Observable;

import java.util.stream.Collectors;

/**
 * Special ability that can be activated when you play a LeaderCard
 * It allow to have an extra depot that contain resources of the same type
 */
public class SpecialExtraDepot extends SpecialAbility {
    private final int capacity;

    /**
     * Class constructor. Instantiates a new Special Extra Depot.
     * @param resource Specify the special resource type
     */
    public SpecialExtraDepot(Resource resource, int capacity) {
        specialAbilityType = SpecialAbilityType.EXTRADEPOT;
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
        super.onActivation(player);
        player.getPersonalBoard().getSpecialDepots().addSpecialContainer(resource.getResourceType(),capacity);
    }
}
