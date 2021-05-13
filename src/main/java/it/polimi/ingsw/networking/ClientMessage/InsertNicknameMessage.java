package it.polimi.ingsw.networking.ClientMessage;

public class InsertNicknameMessage extends ClientMessage{

    public InsertNicknameMessage(String nickname) {
        super(nickname);
    }

    @Override
    public Boolean check(){
        if(this.nickname.length()>15){
            return false;
        }
        else
            return true;
    }

    public String getNickname() {
        return nickname;
    }
}
