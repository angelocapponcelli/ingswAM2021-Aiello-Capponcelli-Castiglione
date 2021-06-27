package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class SelectDeckFromPersonalBoardClientMessage extends ClientMessage {
    private Integer numberOfDeck;

    public SelectDeckFromPersonalBoardClientMessage(String nickname) {
        super(nickname);
        this.messageType = MessageType.SELECT_DECK_FROM_PERSONAL;
    }

    public Integer getNumberOfDeck() {
        return numberOfDeck;
    }
}
