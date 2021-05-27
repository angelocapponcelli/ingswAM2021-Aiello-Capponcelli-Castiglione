package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

/**
 * to do
 **/
public abstract class SpecialAbility {
    protected Resource resource;

    public void onActivation(RealPlayer player) {

    }

    public Resource getResource() {
        return resource;
    }
}
