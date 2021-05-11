package it.polimi.ingsw.networking;

import java.io.Serializable;

public abstract class BeforeGameMessage implements Serializable {

    public Boolean check(){
        return true;
    }
}
