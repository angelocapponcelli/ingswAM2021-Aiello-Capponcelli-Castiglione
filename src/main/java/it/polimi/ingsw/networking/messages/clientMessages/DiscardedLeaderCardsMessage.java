package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

import java.util.ArrayList;
import java.util.List;
/**
 * Leader Card discard message
 */

public class DiscardedLeaderCardsMessage extends ClientMessage {
    private final List<Integer> IDsToDiscard = new ArrayList<>();

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param card1 the id of the first card
     * @param card2 the id of the second card
     */
    public DiscardedLeaderCardsMessage(String nickname, int card1, int card2) {
        super(nickname);
        messageType = MessageType.DISCARDED_LEADER_CARD;
        IDsToDiscard.add(card1);
        IDsToDiscard.add(card2);
    }

    /**
     *
     * @return the list of the id of the leader cards
     */
    public List<Integer> getIDsToDiscard() {
        return IDsToDiscard;
    }
}
