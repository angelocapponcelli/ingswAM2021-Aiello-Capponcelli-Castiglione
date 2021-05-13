package it.polimi.ingsw.networking.ServerMessage;

public class WinnerServerMessage extends ServerMessage{
    private String nickname;

    public WinnerServerMessage(String nickname){
        this.nickname=nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
