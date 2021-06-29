package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

import java.util.Collections;

/**
 * Coin is a concrete resource. it is one of the four resources of the game. Can be put in the deposits.
 */
public class Coin extends ConcreteResource {
    private static final Coin INSTANCE = new Coin();
    private static final ResourceType resourceType = ResourceType.COIN;

    private Coin() {
    }

    /**
     * Gets the instance of Coin
     * @return instance of coin
     */
    public static Coin getInstance() {
        return INSTANCE;
    }

    /**
     * to string method
     * @return "coin"
     */
    @Override
    public String toString() {
        return "Coin";
    }

    /**
     * Method called when a coin is produced when player activates the production.
     * @param realPlayer   The Player who performs the production.
     * @param multiplicity The multiplicity of the produced resource.
     */
    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(resourceType, multiplicity);
    }

    /**
     * Method called when coin is taken by a player.
     * @param realPlayer The player who take the resource.
     */
    @Override
    public void onTaking(RealPlayer realPlayer) {
        realPlayer.getPersonalBoard().getTemporaryDepot().addResource(Collections.singletonList(resourceType));
    }

    /**
     * Gets resource type of coin
     * @return resource type
     */
    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }
}
