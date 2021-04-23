package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.DepotException;
import it.polimi.ingsw.server.model.RealPlayer;

public class Shield extends ConcreteResource {
    private static final Shield INSTANCE = new Shield();
    private static final ResourceType resourceType = ResourceType.SHIELD;

    private Shield() {
    }

    public static Shield getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Shield";
    }

    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) throws DepotException {

    }

    @Override
    public void onTaking(RealPlayer realPlayer) {

    }
}
