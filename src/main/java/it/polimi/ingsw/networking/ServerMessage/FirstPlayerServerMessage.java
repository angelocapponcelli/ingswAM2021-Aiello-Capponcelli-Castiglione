package it.polimi.ingsw.networking.ServerMessage;

public class FirstPlayerServerMessage extends ServerMessage{
    private String nickname;

    public FirstPlayerServerMessage(String nickname){
        this.nickname=nickname;
    }
}
