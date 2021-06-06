package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;

public class UpdatedFaithPositionMessage extends ServerMessage {
    private final String nickname;
    private final Integer faithPosition;

    public UpdatedFaithPositionMessage(String nickname, Integer faithPosition) {
        messageType = MessageType.UPDATED_FAITH_POSITION;
        this.faithPosition = faithPosition;
        this.nickname = nickname;
    }

    public Integer getFaithPosition() {
        return faithPosition;
    }

    public String getNickname() {
        return nickname;
    }
}
