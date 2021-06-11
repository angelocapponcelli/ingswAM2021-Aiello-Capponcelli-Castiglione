package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedContainer;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;

import java.util.List;

public class UpdatedStrongBoxMessage extends ServerMessage {
    private final List<ReducedContainer> strongBox;

    public UpdatedStrongBoxMessage(List<ReducedContainer> containerList) {
        messageType = MessageType.UPDATED_STRONG_BOX;
        strongBox = containerList;
    }

    public List<ReducedContainer> getStrongBox() {
        return strongBox;
    }
}
