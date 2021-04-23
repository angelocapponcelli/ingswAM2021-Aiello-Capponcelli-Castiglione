package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.Resource;

public class Discount extends SpecialAbility {
    private final Resource resource;

    public Discount(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void onActivation(RealPlayer player) {
        /*to do*/
    }

    public Resource getResource() {
        return resource;
    }
}
