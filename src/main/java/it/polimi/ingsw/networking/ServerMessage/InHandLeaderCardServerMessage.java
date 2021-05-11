package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.cards.LeaderCard;

import java.util.ArrayList;

public class InHandLeaderCardServerMessage extends ServerMessage{
    private ArrayList<LeaderCard> inHandLeaderCard;
    public InHandLeaderCardServerMessage(ArrayList<LeaderCard> inHandLeaderCard){
        this.inHandLeaderCard=inHandLeaderCard;
    }
}
