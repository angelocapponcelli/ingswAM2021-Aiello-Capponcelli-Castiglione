package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

public class SpecialWhiteMarble extends SpecialAbility {
    private final int multiplicity;

    public SpecialWhiteMarble(Resource resource, int multiplicity) {
        this.resource = resource;
        this.multiplicity = multiplicity;
    }

    @Override
    public void onActivation(RealPlayer player) {
        super.onActivation(player);
    }
}
