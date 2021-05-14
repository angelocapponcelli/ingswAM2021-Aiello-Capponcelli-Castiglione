package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;

import java.util.ArrayList;

public class UpdatePersonalBoardMessage extends ServerMessage{
    private ArrayList<DevelopmentCard> developmentCards;

    public UpdatePersonalBoardMessage(ArrayList<DevelopmentCard> developmentCards){
        this.developmentCards=developmentCards;
    }

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }
}
