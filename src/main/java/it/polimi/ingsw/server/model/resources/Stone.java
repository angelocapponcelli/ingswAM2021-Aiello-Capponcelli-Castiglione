package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

import java.util.Collections;

/**
 * Stone is a concrete resource. it is one of the four resources of the game. Can be put in the deposits.
 */
public class Stone extends ConcreteResource {
    private static final Stone INSTANCE = new Stone();
    private static final ResourceType resourceType = ResourceType.STONE;

    private Stone() {
    }


    /**
     * Gets the instance of Stone
     * @return instance of stone
     */
    public static Stone getInstance() {
        return INSTANCE;
    }

    /**
     * to string method
     * @return "stone"
     */

    @Override
    public String toString() {
        return "Stone";
    }


    /**
     * Method called when a stone is produced when player activates the production.
     * @param realPlayer   The Player who performs the production.
     * @param multiplicity The multiplicity of the produced resource.
     */
    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(resourceType, multiplicity);
    }

    /**
     * Method called when stone is taken by a player.
     * @param realPlayer The player who take the resource.
     */

    @Override
    public void onTaking(RealPlayer realPlayer) {
        realPlayer.getPersonalBoard().getTemporaryDepot().addResource(Collections.singletonList(resourceType));
    }

    /**
     * Gets resource type of stone
     * @return resource type
     */
    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }
}
