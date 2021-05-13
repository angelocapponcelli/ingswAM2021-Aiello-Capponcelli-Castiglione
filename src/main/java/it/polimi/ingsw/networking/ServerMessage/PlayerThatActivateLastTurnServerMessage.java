package it.polimi.ingsw.networking.ServerMessage;

public class PlayerThatActivateLastTurnServerMessage extends ServerMessage{
    private String nickName;

    public PlayerThatActivateLastTurnServerMessage(String nickName){
        this.nickName=nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
