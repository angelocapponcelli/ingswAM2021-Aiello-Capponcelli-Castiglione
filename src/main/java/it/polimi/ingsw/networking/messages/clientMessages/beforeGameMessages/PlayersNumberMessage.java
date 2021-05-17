package it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages;

public class PlayersNumberMessage extends BeforeGameMessage {
    private final int playerNumber;

    public PlayersNumberMessage(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public Boolean check() {
        return this.playerNumber <= 4 && this.playerNumber >= 1;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
