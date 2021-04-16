package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.Resource;

public class AdditionalProductionPower extends SpecialAbility{
    private Resource resource;

    public AdditionalProductionPower(Resource resource){
        this.resource=resource;
    }

    @Override
    public void onActivation(Player player){
        /**to do*/
    }
}
