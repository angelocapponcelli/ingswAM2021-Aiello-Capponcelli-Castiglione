package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

import java.util.Collections;

public class Shield extends ConcreteResource {
    private static final Shield INSTANCE = new Shield();
    private static final ResourceType resourceType = ResourceType.SHIELD;

    private Shield() {
    }

    public static Shield getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Shield";
    }

    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(resourceType, multiplicity);
    }

    @Override
    public void onTaking(RealPlayer realPlayer) {
        realPlayer.getPersonalBoard().getTemporaryDepot().addResource(Collections.singletonList(resourceType));
    }

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }
}
