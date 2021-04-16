package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Takeable;

public class WhiteMarble implements Takeable {
    @Override
    public void onTaking(RealPlayer realPlayer) {
        // TODO: check for special abilities
        // TODO: resource = ask for resource
        // TODO: //ùplayer.getDepotForMarket().add(resource);

    }
}
