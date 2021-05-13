package it.polimi.ingsw.networking.ClientMessage;

public class SelectDeckFromPersonalBoardClientMessage extends ClientMessage {
    private Integer numberOfDeck;

    public SelectDeckFromPersonalBoardClientMessage(String nickname) {
        super(nickname);
    }

    @Override
    public Boolean check() {
        if (this.numberOfDeck > 2 || this.numberOfDeck < 0) {
            return false;
        }
        return true;
    }

    public Integer getNumberOfDeck() {
        return numberOfDeck;
    }
}
