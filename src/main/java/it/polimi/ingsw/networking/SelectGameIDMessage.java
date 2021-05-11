package it.polimi.ingsw.networking;

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
}
