package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

import java.util.Collections;

/**
 * The Any resource. Represents a resource that can be chosen by a player.
 */
public class Any extends Resource {
    private static final Any INSTANCE = new Any();
    private static final ResourceType resourceType = ResourceType.ANY;

    private Any() {
    }

    public static Any getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Any";
    }

    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        // ask for a resource
        //resource.onProduction

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
