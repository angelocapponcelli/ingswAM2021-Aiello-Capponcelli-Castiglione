package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

public class SpecialDiscount extends SpecialAbility {

    public SpecialDiscount(Resource resource) {
        this.resource = resource;
    }

    /**
     * It's a passive ability, should consider It in the pay transaction
     */
    @Override
    public void onActivation(RealPlayer player) {
    }

    public Resource getResource() {
        return resource;
    }
}
