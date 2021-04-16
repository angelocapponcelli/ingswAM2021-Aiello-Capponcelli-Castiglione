package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.DepotException;
import it.polimi.ingsw.server.model.RealPlayer;

public class Stone extends Resource{
    private static final Stone INSTANCE = new Stone();
    private static final ResourceType resourceType = ResourceType.STONE;

    private Stone() {}

    public static Stone getInstance(){
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Stone";
    }

    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) throws DepotException {

    }

    @Override
    public void onTaking(RealPlayer realPlayer) {

    }
}
