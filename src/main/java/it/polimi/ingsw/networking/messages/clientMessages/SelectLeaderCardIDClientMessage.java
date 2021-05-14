package it.polimi.ingsw.networking.messages.clientMessages;

public class SelectLeaderCardIDClientMessage extends SelectCardIDClientMessage {
    public SelectLeaderCardIDClientMessage(String nickname, Integer id) {
        super(nickname, id);
    }

    @Override
    public Boolean check() {
        return this.id >= 0;
    }
}
