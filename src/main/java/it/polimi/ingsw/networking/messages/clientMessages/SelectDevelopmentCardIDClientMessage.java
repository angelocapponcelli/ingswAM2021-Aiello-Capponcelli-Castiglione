package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class SelectDevelopmentCardIDClientMessage extends SelectCardIDClientMessage {
    public SelectDevelopmentCardIDClientMessage(String nickname, Integer id) {
        super(nickname, id);
        this.messageType = MessageType.SELECT_DEVELOPMENT_CARD;
    }

    @Override
    public Boolean check() {
        return this.id >= 0;
    }
}
