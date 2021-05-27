package it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages;

import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.MessageType;

public class NicknameMessage extends Message {
    private final String nickname;

    public NicknameMessage(String nickname) {
        messageType = MessageType.NICKNAME;
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

}
