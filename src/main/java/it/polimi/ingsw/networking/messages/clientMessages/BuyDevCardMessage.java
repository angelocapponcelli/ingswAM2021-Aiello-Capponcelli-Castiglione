package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

/**
 * Buy Development card message.
 */
public class BuyDevCardMessage extends ClientMessage {
    private final int cardID;

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param cardID the id of the selected card
     */
    public BuyDevCardMessage(String nickname, int cardID) {
        super(nickname);
        messageType = MessageType.BUY_DEV_CARD;
        this.cardID = cardID;
    }

    /**
     *
     * @return id of the card selected
     */
    public int getCardID() {
        return cardID;
    }
}
