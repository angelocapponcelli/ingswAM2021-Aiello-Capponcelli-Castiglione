package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedLeaderCard;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;

import java.util.List;

public class UpdatedInHandLeaderCardMessage extends ServerMessage {
    private final List<ReducedLeaderCard> inHandLeaderCardIDs;

    public UpdatedInHandLeaderCardMessage(List<ReducedLeaderCard> inHandLeaderCard) {
        messageType = MessageType.UPDATED_IN_HAND_LEADER_CARD;
        this.inHandLeaderCardIDs = inHandLeaderCard;
    }

    public List<ReducedLeaderCard> getInHandLeaderCard() {
        return inHandLeaderCardIDs;
    }

}
