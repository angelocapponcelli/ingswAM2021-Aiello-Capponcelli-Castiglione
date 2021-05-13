package it.polimi.ingsw.networking.ClientMessage.BeforeGameMessage;

public class SelectGameIDMessage extends BeforeGameMessage{
    private Integer id;
    public SelectGameIDMessage(Integer id){
        this.id=id;
    }

    @Override
    public Boolean check(){
        if (this.id<0){
            return false;
        }
        else
            return true;
    }
    public Integer getId(){
        return this.id;
    }
}
