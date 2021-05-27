package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;

import java.util.List;

public class UpdatedInHandLeaderCardMessage extends ServerMessage {
    private final List<Integer> inHandLeaderCardIDs;

    public UpdatedInHandLeaderCardMessage(List<Integer> inHandLeaderCard) {
        messageType = MessageType.UPDATED_IN_HAND_LEADER_CARD;
        this.inHandLeaderCardIDs = inHandLeaderCard;
    }

    public List<Integer> getInHandLeaderCard() {
        return inHandLeaderCardIDs;
    }

}
