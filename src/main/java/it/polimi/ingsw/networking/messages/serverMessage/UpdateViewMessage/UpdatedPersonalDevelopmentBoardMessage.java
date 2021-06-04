package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;

import java.util.List;

public class UpdatedPersonalDevelopmentBoardMessage extends ServerMessage {
    MessageType messageType;
    List<ReducedDevelopmentCard> developmentCards;

    public UpdatedPersonalDevelopmentBoardMessage(List<ReducedDevelopmentCard> developmentCards) {
        messageType = MessageType.UPDATED_PERSONAL_DEVELOPMENT_BOARD;
        this.developmentCards = developmentCards;
    }

    public List<ReducedDevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }
}
