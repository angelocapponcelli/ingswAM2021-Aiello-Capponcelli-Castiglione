package it.polimi.ingsw.networking.messages;

public class NicknameMessage extends Message {
    private String nickname;

    NicknameMessage(String nickname){
        messageType = MessageType.NICKNAME;
        this.nickname = nickname;
    }

    public String getNickname(){
        return nickname;
    }

}
