package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.List;

/**
 * Initial resource  chose message.
 */
public class ChosenInitialResourcesMessage extends ClientMessage {
    List<ResourceType> chosenResource;

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param chosenResource the resources chosen
     */
    public ChosenInitialResourcesMessage(String nickname, List<ResourceType> chosenResource) {
        super(nickname);
        messageType = MessageType.CHOSEN_INITIAL_RESOURCES;
        this.chosenResource = chosenResource;
    }

    public List<ResourceType> getChosenResource() {
        return chosenResource;
    }
}
