package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * Discard resource message
 */
public class DiscardResourceMessage extends ClientMessage{
    private final ResourceType resourceType;

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param resourceType the type of the resource to discard
     */
    public DiscardResourceMessage(String nickname, ResourceType resourceType) {
        super(nickname);
        this.resourceType = resourceType;
        messageType = MessageType.DISCARD_RESOURCE;
    }

    /**
     *
     * @return the type of the resource to discard
     */
    public ResourceType getResourceType(){
        return resourceType;
    }
}
