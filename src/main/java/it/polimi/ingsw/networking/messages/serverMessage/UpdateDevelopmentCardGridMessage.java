package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;


public class UpdateDevelopmentCardGridMessage extends ServerMessage {
    private DevelopmentCard[][] developmentCards;

    public UpdateDevelopmentCardGridMessage(DevelopmentCard[][] developmentCard) {
        this.developmentCards = developmentCard;
    }

    public DevelopmentCard[][] getDevelopmentCards() {
        return developmentCards;
    }
}
