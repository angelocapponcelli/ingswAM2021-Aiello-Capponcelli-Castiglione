package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

public class SpecialDiscount extends SpecialAbility {

    private final int discount;

    public SpecialDiscount(Resource resource, int discount) {
        this.resource = resource;
        this.discount = discount;
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
