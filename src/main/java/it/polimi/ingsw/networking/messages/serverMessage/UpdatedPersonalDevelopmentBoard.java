package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;

import java.util.List;

public class UpdatedPersonalDevelopmentBoard extends ServerMessage{
    MessageType messageType;
    List<DevelopmentCard> developmentCards;

    public UpdatedPersonalDevelopmentBoard(List<DevelopmentCard> developmentCards) {
        messageType = MessageType.UPDATED_PERSONAL_DEVELOPMENT_BOARD;
        this.developmentCards = developmentCards;
    }

    public List<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }
}
