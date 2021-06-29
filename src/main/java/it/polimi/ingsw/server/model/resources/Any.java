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

    /**
     * Gets instance of Any
     * @return instance of any
     */
    public static Any getInstance() {
        return INSTANCE;
    }

    /**
     * to string method.
     * @return "any"
     */
    @Override
    public String toString() {
        return "Any";
    }

    /**
     * Method called when a resource is produced when the player activates the production.
     * @param realPlayer   The Player who performs the production.
     * @param multiplicity The multiplicity of the produced resource.
     */
    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        // ask for a resource
        //resource.onProduction

    }

    /**
     * Method called when a player takes the resource.
     * @param realPlayer The player who take the resource.
     */

    @Override
    public void onTaking(RealPlayer realPlayer) {
        realPlayer.getPersonalBoard().getTemporaryDepot().addResource(Collections.singletonList(resourceType));
    }

    /**
     * Gets the resource type.
     * @return resource type
     */
    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }

}
