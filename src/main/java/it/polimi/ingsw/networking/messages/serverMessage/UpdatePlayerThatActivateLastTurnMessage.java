package it.polimi.ingsw.networking.messages.serverMessage;

public class UpdatePlayerThatActivateLastTurnMessage extends ServerMessage {
    private final String nickName;

    public UpdatePlayerThatActivateLastTurnMessage(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
