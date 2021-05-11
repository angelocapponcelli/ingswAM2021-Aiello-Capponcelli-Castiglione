package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;



public class DevelopmentCardGridServerMessage extends ServerMessage{
    private DevelopmentCard developmentCards[][]= new DevelopmentCard[4][3];

    public DevelopmentCardGridServerMessage(DevelopmentCard developmentCard[][]){
        this.developmentCards= developmentCard;
    }
}
