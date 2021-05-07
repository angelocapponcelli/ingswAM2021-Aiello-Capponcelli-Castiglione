package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class Faith extends Resource {
    private static final Faith INSTANCE = new Faith();
    private static final ResourceType resourceType = ResourceType.FAITH;

    private Faith() {
    }

    public static Faith getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Faith";
    }


    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        for (int i = 0; i < multiplicity; i++) realPlayer.increaseFaithPosition();
    }

    @Override
    public void onTaking(RealPlayer realPlayer) {
        realPlayer.increaseFaithPosition();
    }

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }

}
