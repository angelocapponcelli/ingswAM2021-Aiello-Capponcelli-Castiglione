package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

import java.util.Collections;

/**
 * Shield is a concrete resource. it is one of the four resources of the game. Can be put in the deposits.
 */
public class Shield extends ConcreteResource {
    private static final Shield INSTANCE = new Shield();
    private static final ResourceType resourceType = ResourceType.SHIELD;

    private Shield() {
    }

    /**
     * Gets the instance of Shield
     * @return instance of shield
     */
    public static Shield getInstance() {
        return INSTANCE;
    }

    /**
     * to string method
     * @return "shield"
     */
    @Override
    public String toString() {
        return "Shield";
    }


    /**
     * Method called when a shield is produced when player activates the production.
     * @param realPlayer   The Player who performs the production.
     * @param multiplicity The multiplicity of the produced resource.
     */
    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(resourceType, multiplicity);
    }

    /**
     * Method called when shield is taken by a player.
     * @param realPlayer The player who take the resource.
     */
    @Override
    public void onTaking(RealPlayer realPlayer) {
        realPlayer.getPersonalBoard().getTemporaryDepot().addResource(Collections.singletonList(resourceType));
    }

    /**
     * Gets resource type of shield
     * @return resource type
     */
    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }
}
