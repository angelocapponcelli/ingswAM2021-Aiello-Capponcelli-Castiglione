package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class BuyDevCardMessage extends ClientMessage {
    private final int cardID;

    public BuyDevCardMessage(String nickname, int cardID) {
        super(nickname);
        messageType = MessageType.BUY_DEV_CARD;
        this.cardID = cardID;
    }

    public int getCardID() {
        return cardID;
    }
}
