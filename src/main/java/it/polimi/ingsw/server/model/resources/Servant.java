package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class Servant extends ConcreteResource {
    private static final Servant INSTANCE = new Servant();
    private static final ResourceType resourceType = ResourceType.SERVANT;

    private Servant() {
    }

    public static Servant getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Servant";
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
