package it.polimi.ingsw.networking.ServerMessage;

public class SetUpFirstPlayerMessage extends ServerMessage{
    private String nickname;

    public SetUpFirstPlayerMessage(String nickname){
        this.nickname=nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
