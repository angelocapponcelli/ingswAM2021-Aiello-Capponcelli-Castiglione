package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;


public class UpdatedDevelopmentCardGridMessage extends ServerMessage {
    private final ReducedDevelopmentCard[][] developmentCardGrid;

    public UpdatedDevelopmentCardGridMessage(ReducedDevelopmentCard[][] developmentCard) {
        messageType = MessageType.UPDATED_DEV_CARD_GRID;
        this.developmentCardGrid = developmentCard;
    }

    public ReducedDevelopmentCard[][] getDevelopmentCardGrid() {
        return developmentCardGrid;
    }
}
