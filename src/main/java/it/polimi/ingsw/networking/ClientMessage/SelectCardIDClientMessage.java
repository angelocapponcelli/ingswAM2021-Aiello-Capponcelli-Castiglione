package it.polimi.ingsw.networking.ClientMessage;

public class SelectCardIDClientMessage extends ClientMessage {
    protected Integer id; /** o posizione*/

    public SelectCardIDClientMessage(String nickname, Integer id){
        super(nickname);
        this.id= id;
    }
    @Override
    public Boolean check(){
        return true;
    }
}
