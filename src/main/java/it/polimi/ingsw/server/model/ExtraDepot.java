package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * special ability that enable player to have a special depot of a specific resource
 */
public class ExtraDepot extends SpecialAbility {
    private ResourceType resource;

    public ExtraDepot(ResourceType resource) {
        this.resource = resource;
    }

    /**
     * usually activated when a leaderCard is taken
     * @param player which the special container in special depot is added to
     */
    @Override
    public void onActivation(RealPlayer player) {
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(resource);
    }
}
