package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;
import it.polimi.ingsw.server.model.resources.ResourceType;
import java.util.Map;

public class UpdatedTemporaryDepotMessage extends ServerMessage {
    private final Map<ResourceType, Integer> temporaryDepot;

    public UpdatedTemporaryDepotMessage(Map<ResourceType, Integer> temporaryDepot){
        messageType = MessageType.UPDATED_TEMPORARY_DEPOT;
        this.temporaryDepot = temporaryDepot;

    }

    public Map<ResourceType, Integer> getTemporaryDepot() {
        return temporaryDepot;
    }
}
