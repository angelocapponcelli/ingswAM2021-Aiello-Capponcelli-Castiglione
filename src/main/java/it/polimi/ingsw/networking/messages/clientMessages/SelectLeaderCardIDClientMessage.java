package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class SelectLeaderCardIDClientMessage extends SelectCardIDClientMessage {
    public SelectLeaderCardIDClientMessage(String nickname, Integer id) {
        super(nickname, id);
        this.messageType = MessageType.SELECT_LEADER_CARD;
    }
}
