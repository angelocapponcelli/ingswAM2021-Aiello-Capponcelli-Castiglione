package it.polimi.ingsw.networking.ServerMessage;

public class SetWinnerMessage extends ServerMessage{
    private String nickname;

    public SetWinnerMessage(String nickname){
        this.nickname=nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
