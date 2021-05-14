package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.cards.LeaderCard;

import java.util.ArrayList;

public class UpdateInHandLeaderCardMessage extends ServerMessage{
    private ArrayList<LeaderCard> inHandLeaderCard;
    public UpdateInHandLeaderCardMessage(ArrayList<LeaderCard> inHandLeaderCard){
        this.inHandLeaderCard=inHandLeaderCard;
    }

    public ArrayList<LeaderCard> getInHandLeaderCard() {
        return inHandLeaderCard;
    }
}
