package it.polimi.ingsw.server.model;

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
