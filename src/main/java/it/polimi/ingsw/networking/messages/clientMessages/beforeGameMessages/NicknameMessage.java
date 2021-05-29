package it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages;

import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.clientMessages.ClientMessage;

public class NicknameMessage extends ClientMessage {

    public NicknameMessage(String nickname) {
        super(nickname);
        messageType = MessageType.NICKNAME;
    }

    public String getNickname() {
        return nickname;
    }

}
