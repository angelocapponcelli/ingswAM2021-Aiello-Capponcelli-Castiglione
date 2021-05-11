package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;

import java.util.ArrayList;

public class UpdatePersonalBoardServerMessage extends ServerMessage{
    private ArrayList<DevelopmentCard> developmentCards;

    public UpdatePersonalBoardServerMessage(ArrayList<DevelopmentCard> developmentCards){
        this.developmentCards=developmentCards;
    }
}
