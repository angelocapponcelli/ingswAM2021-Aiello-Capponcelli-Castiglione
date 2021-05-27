package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.server.model.cards.LeaderCard;

import java.util.LinkedList;

public class SetUpLeaderCardMessage extends ServerMessage {
    private final LinkedList<LeaderCard> fourListLeaderCard;

    public SetUpLeaderCardMessage(LinkedList<LeaderCard> list) {
        this.fourListLeaderCard = list;
    }

    public LinkedList<LeaderCard> getFourListLeaderCard() {
        return fourListLeaderCard;
    }
}
