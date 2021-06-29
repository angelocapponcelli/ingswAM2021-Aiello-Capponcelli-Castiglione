package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

import java.util.Collections;

/**
 * Servant is a concrete resource. it is one of the four resources of the game. Can be put in the deposits.
 */
public class Servant extends ConcreteResource {
    private static final Servant INSTANCE = new Servant();
    private static final ResourceType resourceType = ResourceType.SERVANT;

    private Servant() {
    }

    /**
     * Gets the instance of Servant
     * @return instance of servant
     */
    public static Servant getInstance() {
        return INSTANCE;
    }

    /**
     * to string method
     * @return "servant"
     */
    @Override
    public String toString() {
        return "Servant";
    }

    /**
     * Method called when a servant is produced when player activates the production.
     * @param realPlayer   The Player who performs the production.
     * @param multiplicity The multiplicity of the produced resource.
     */

    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(resourceType, multiplicity);
    }

    /**
     * Method called when servant is taken by a player.
     * @param realPlayer The player who take the resource.
     */
    @Override
    public void onTaking(RealPlayer realPlayer) {
        realPlayer.getPersonalBoard().getTemporaryDepot().addResource(Collections.singletonList(resourceType));

    }


    /**
     * Gets resource type of servant
     * @return resource type
     */
    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }
}
