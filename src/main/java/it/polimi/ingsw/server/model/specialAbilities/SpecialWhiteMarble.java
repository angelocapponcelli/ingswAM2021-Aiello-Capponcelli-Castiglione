package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

public class SpecialWhiteMarble extends SpecialAbility {
    private final Resource resource;

    public SpecialWhiteMarble(Resource resource) {
        this.resource = resource;
    }

    @Override
    void onActivation(RealPlayer player) {
        super.onActivation(player);
    }
}
