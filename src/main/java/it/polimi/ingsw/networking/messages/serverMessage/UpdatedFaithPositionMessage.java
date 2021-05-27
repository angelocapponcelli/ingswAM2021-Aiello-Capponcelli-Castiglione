package it.polimi.ingsw.networking.messages.serverMessage;

public class UpdatedFaithPositionMessage extends ServerMessage {
    private final String nickname;
    private final Integer faithPosition;

    public UpdatedFaithPositionMessage(String nickname, Integer faithPosition) {
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
