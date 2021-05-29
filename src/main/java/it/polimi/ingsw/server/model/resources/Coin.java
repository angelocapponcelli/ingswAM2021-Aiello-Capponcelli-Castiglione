package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class Coin extends ConcreteResource {
    private static final Coin INSTANCE = new Coin();
    private static final ResourceType resourceType = ResourceType.COIN;

    private Coin() {
    }

    public static Coin getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Coin";
    }

    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(resourceType, multiplicity);
    }

    @Override
    public void onTaking(RealPlayer realPlayer) {
        realPlayer.getPersonalBoard().getTemporaryDepot().addResource(resourceType);
    }


    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }
}
