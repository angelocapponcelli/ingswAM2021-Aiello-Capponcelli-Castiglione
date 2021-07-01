package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * Resource replacement message
 */
public class SelectResourceReplacementMessage extends ClientMessage {
    private final ResourceType resourceType;

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param resourceType the type of the resource to be changed into
     */
    public SelectResourceReplacementMessage(String nickname, ResourceType resourceType) {
        super(nickname);
        messageType = MessageType.SelectResourceTypeMessage;
        this.resourceType = resourceType;
        this.messageType = MessageType.SELECT_RESOURCE;
    }

    /**
     *
     * @return the type of the resource to be changed into
     */
    public ResourceType getResourceType() {
        return resourceType;
    }
}
