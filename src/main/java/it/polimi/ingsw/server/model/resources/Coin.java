package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.exceptions.DepotException;
import it.polimi.ingsw.server.model.RealPlayer;

public class Coin extends ConcreteResource {
    private static final Coin INSTANCE = new Coin();
    private static final ResourceType resourceType = ResourceType.COIN;

    private Coin() {
    }

    public static Coin getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Coin";
    }

    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) throws DepotException {

    }

    @Override
    public void onTaking(RealPlayer realPlayer) {
        // TODO: ask for shelf; notifica al controller
        //TODO: realPlayer.getPersonalBoard.getDepotForMarket().add(this.type, shelf);
    }
}
