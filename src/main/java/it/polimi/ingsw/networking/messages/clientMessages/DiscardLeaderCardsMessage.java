package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

import java.util.ArrayList;
import java.util.List;

public class DiscardLeaderCardsMessage extends ClientMessage{
    List<Integer> IDsToDiscard = new ArrayList<>();

    public DiscardLeaderCardsMessage(String nickname, int card1, int card2) {
        super(nickname);
        messageType = MessageType.SELECT_LEADER_CARD_TO_DISCARD;
        IDsToDiscard.add(card1);
        IDsToDiscard.add(card2);
    }

    public List<Integer> getIDsToDiscard() {
        return IDsToDiscard;
    }
}
