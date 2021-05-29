package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.Message;

public abstract class ClientMessage extends Message {
    protected final String nickname;

    public ClientMessage(String nickname) {
        this.nickname = nickname;
    }

    public Boolean check() {
        return true;
    }

    public String getNickname() {
        return this.nickname;
    }
}
