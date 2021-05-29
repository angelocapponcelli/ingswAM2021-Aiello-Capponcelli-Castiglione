package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

import java.util.ArrayList;
import java.util.List;

public class DiscardedLeaderCardsMessage extends ClientMessage{
    List<Integer> IDsToDiscard = new ArrayList<>();

    public DiscardedLeaderCardsMessage(String nickname, int card1, int card2) {
        super(nickname);
        messageType = MessageType.DISCARDED_LEADER_CARD;
        IDsToDiscard.add(card1);
        IDsToDiscard.add(card2);
    }

    public List<Integer> getIDsToDiscard() {
        return IDsToDiscard;
    }
}
