package it.polimi.ingsw.networking.messages.serverMessage;

public class SetUpFirstPlayerMessage extends ServerMessage {
    private final String nickname;

    public SetUpFirstPlayerMessage(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
