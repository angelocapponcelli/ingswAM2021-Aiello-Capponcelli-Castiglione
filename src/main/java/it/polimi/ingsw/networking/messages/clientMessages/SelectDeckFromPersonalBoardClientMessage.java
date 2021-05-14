package it.polimi.ingsw.networking.messages.clientMessages;

public class SelectDeckFromPersonalBoardClientMessage extends ClientMessage {
    private Integer numberOfDeck;

    public SelectDeckFromPersonalBoardClientMessage(String nickname) {
        super(nickname);
    }

    @Override
    public Boolean check() {
        return this.numberOfDeck <= 2 && this.numberOfDeck >= 0;
    }

    public Integer getNumberOfDeck() {
        return numberOfDeck;
    }
}
