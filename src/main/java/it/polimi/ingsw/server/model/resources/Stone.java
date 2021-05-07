package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class Stone extends ConcreteResource {
    private static final Stone INSTANCE = new Stone();
    private static final ResourceType resourceType = ResourceType.STONE;

    private Stone() {
    }

    public static Stone getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Stone";
    }

    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(resourceType, multiplicity);
    }

    @Override
    public void onTaking(RealPlayer realPlayer) {
        realPlayer.getPersonalBoard().getTemporaryDepotForMarket().addResource(resourceType);
    }

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }
}
