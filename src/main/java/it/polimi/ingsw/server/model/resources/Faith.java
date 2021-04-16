package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.DepotException;
import it.polimi.ingsw.server.model.RealPlayer;
import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.interfaces.Takeable;

public class Faith implements Takeable, Producible {
    private static final Faith INSTANCE = new Faith();
    private static final ResourceType resourceType = ResourceType.FAITH;

    private Faith() {}

    public static Faith getInstance(){
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Faith";
    }

    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity){
        int loop;
        for (loop=0; loop>= multiplicity; loop++){
            realPlayer.increaseFaithPosition();
        }
    }
    @Override
    public void onTaking(RealPlayer realPlayer){
        realPlayer.increaseFaithPosition();
    }

}
