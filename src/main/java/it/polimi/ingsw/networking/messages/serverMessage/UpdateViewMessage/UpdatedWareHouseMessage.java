package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedContainer;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;

import java.util.List;

public class UpdatedWareHouseMessage extends ServerMessage {
    private final List<ReducedContainer> wareHouse;

    public UpdatedWareHouseMessage(List<ReducedContainer> wareHouse) {
        messageType = MessageType.UPDATED_WAREHOUSE;
        this.wareHouse = wareHouse;
    }

    public List<ReducedContainer> getWareHouse() {
        return wareHouse;
    }
}
