package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.List;

public class ChosenInitialResourcesMessage extends ClientMessage {
    List<ResourceType> chosenResource;

    public ChosenInitialResourcesMessage(String nickname, List<ResourceType> chosenResource) {
        super(nickname);
        messageType = MessageType.CHOSEN_INITIAL_RESOURCES;
        this.chosenResource = chosenResource;
    }

    public List<ResourceType> getChosenResource() {
        return chosenResource;
    }
}
