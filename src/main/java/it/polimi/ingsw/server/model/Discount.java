package it.polimi.ingsw.server.model;

public class Discount extends SpecialAbility{
    private Resource resource;

    public Discount(Resource resource){
        this.resource=resource;
    }

    @Override
    public void onActivation(Player player){
        /**to do*/
    }
}
