package it.polimi.ingsw.networking.ServerMessage;

public class FaithPositionUpdateServerMessage extends ServerMessage{
    private String nickname;
    private Integer faithPosition;

    public FaithPositionUpdateServerMessage(String nickname, Integer faithPosition){
        this.faithPosition=faithPosition;
        this.nickname=nickname;
    }

}
