package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.ResourceType;

public class ExtraDepot extends SpecialAbility {
    private final ResourceType resource;

    public ExtraDepot(ResourceType resource) {
        this.resource = resource;
    }

    @Override
    public void onActivation(RealPlayer player) {
        /* need to wait the finish of the depot to write this part*/
    }
}
