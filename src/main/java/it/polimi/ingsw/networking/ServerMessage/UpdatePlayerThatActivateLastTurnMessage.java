package it.polimi.ingsw.networking.ServerMessage;

public class UpdatePlayerThatActivateLastTurnMessage extends ServerMessage{
    private String nickName;

    public UpdatePlayerThatActivateLastTurnMessage(String nickName){
        this.nickName=nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
