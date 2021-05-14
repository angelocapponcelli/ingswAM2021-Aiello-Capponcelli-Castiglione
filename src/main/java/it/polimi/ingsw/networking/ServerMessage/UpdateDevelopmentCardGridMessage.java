package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;



public class UpdateDevelopmentCardGridMessage extends ServerMessage{
    private DevelopmentCard developmentCards[][]= new DevelopmentCard[4][3];

    public UpdateDevelopmentCardGridMessage(DevelopmentCard developmentCard[][]){
        this.developmentCards= developmentCard;
    }

    public DevelopmentCard[][] getDevelopmentCards() {
        return developmentCards;
    }
}
