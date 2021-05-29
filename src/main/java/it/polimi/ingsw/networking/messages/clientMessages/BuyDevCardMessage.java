package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class BuyDevCardMessage extends ClientMessage {
    private final int cardID;

    public BuyDevCardMessage(String nickname, int cardID) {
        super(nickname);
        this.cardID = cardID;
        messageType = MessageType.BUY_DEV_CARD;
    }

    public int getCardID() {
        return cardID;
    }
}
