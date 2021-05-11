package it.polimi.ingsw.networking;

public class InsertNicknameMessage extends BeforeGameMessage{

    private String nickname;

    public InsertNicknameMessage(String nickname) {
        this.nickname=nickname;
    }

    @Override
    public Boolean check(){
        if(this.nickname.length()>15){
            return false;
        }
        else
            return true;
    }
}
