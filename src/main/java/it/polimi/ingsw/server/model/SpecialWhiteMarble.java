package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.Resource;

public class SpecialWhiteMarble extends SpecialAbility{
    private final Resource resource;

    public SpecialWhiteMarble(Resource resource) {
        this.resource = resource;
    }

    @Override
    void onActivation(RealPlayer player) {
        super.onActivation(player);
    }
}
