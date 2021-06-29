package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.player.RealPlayer;

/**
 * Faith class. This is a resource but it is not a concrete Resource because it can't be put in a deposit.
 * When it is taken the player that takes it advances by one cell in the faith track.
 */
public class Faith extends Resource {
    private static final Faith INSTANCE = new Faith();
    private static final ResourceType resourceType = ResourceType.FAITH;

    private Faith() {
    }

    /**
     * Gets the instance of faith
     * @return instance of faith
     */
    public static Faith getInstance() {
        return INSTANCE;
    }

    /**
     * to string method.
     * @return "faith"
     */
    @Override
    public String toString() {
        return "Faith";
    }

    /**
     * Method called when a faith is produced when the player activates the production.
     * @param realPlayer   The Player who performs the production.
     * @param multiplicity The multiplicity of the produced resource.
     */
    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) {
        for (int i = 0; i < multiplicity; i++) realPlayer.increaseFaithPosition();
    }

    /**
     * Method called when a player takes the faith
     * @param realPlayer The player who take the resource.
     */
    @Override
    public void onTaking(RealPlayer realPlayer) {
        realPlayer.increaseFaithPosition();
    }

    /**
     * Gets resource type of faith
     * @return resource type
     */
    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }

}
