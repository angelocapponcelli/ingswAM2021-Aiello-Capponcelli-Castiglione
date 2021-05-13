package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.cards.LeaderCard;

import java.util.LinkedList;

public class LeaderCardStartServerMessage extends ServerMessage{
    private LinkedList<LeaderCard> fourListLeaderCard;

    public LeaderCardStartServerMessage(LinkedList<LeaderCard> list){
        this.fourListLeaderCard= list;
    }

    public LinkedList<LeaderCard> getFourListLeaderCard() {
        return fourListLeaderCard;
    }
}
