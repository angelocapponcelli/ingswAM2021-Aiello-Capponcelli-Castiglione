package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.Resource;

public class SpecialExtraDepot extends SpecialAbility {
    private final Resource resource;

    public SpecialExtraDepot(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void onActivation(RealPlayer player) {
        /* need to wait the finish of the depot to write this part*/
    }
}
