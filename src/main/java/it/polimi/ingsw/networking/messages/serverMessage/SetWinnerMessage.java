package it.polimi.ingsw.networking.messages.serverMessage;

public class SetWinnerMessage extends ServerMessage {
    private final String nickname;

    public SetWinnerMessage(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
