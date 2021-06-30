package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedContainer;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;

import java.util.List;

public class UpdatedSpecialDepotMessage extends ServerMessage {
    private final List<ReducedContainer> specialDepot;

    public UpdatedSpecialDepotMessage(List<ReducedContainer> specialDepot) {
        messageType = MessageType.UPDATED_SPECIALDEPOT;
        this.specialDepot = specialDepot;
    }

    public List<ReducedContainer> getSepcialDepot() {
        return specialDepot;
    }
}
