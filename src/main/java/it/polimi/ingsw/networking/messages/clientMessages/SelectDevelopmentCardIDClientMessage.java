package it.polimi.ingsw.networking.messages.clientMessages;

public class SelectDevelopmentCardIDClientMessage extends SelectCardIDClientMessage {
    public SelectDevelopmentCardIDClientMessage(String nickname, Integer id) {
        super(nickname, id);
    }

    @Override
    public Boolean check() {
        return this.id >= 0;
    }
}
