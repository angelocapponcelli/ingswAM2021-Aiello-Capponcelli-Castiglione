package it.polimi.ingsw.networking.messages.clientMessages;

public class InsertNicknameMessage extends ClientMessage{

    public InsertNicknameMessage(String nickname) {
        super(nickname);
    }

    @Override
    public Boolean check(){
        return this.nickname.length() <= 15;
    }

    public String getNickname() {
        return nickname;
    }
}
