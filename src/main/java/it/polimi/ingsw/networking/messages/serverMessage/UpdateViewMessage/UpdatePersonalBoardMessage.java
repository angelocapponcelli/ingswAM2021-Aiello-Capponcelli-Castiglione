package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;

import java.util.ArrayList;

public class UpdatePersonalBoardMessage extends ServerMessage {
    private final ArrayList<DevelopmentCard> developmentCards;

    public UpdatePersonalBoardMessage(ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }
}
