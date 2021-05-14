package it.polimi.ingsw.networking.ServerMessage;

public class UpdateFaithPositionMessage extends ServerMessage{
    private String nickname;
    private Integer faithPosition;

    public UpdateFaithPositionMessage(String nickname, Integer faithPosition){
        this.faithPosition=faithPosition;
        this.nickname=nickname;
    }

    public Integer getFaithPosition() {
        return faithPosition;
    }

    public String getNickname() {
        return nickname;
    }
}
