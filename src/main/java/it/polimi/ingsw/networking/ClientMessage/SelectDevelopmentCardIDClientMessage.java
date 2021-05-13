package it.polimi.ingsw.networking.ClientMessage;

public class SelectDevelopmentCardIDClientMessage extends SelectCardIDClientMessage {
    public SelectDevelopmentCardIDClientMessage(String nickname, Integer id) {
        super(nickname, id);
    }

    @Override
    public Boolean check() {
        if (this.id < 0) {
            return false;
        }
        return true;
    }
}
