package it.polimi.ingsw.server.model;

public class ExtraDepot extends SpecialAbility{
    private Resource resource;

    public ExtraDepot(Resource resource){
        this.resource=resource;
    }

    @Override
    public void onActivation(Player player){
        /** need to wait the finish of the depot to write this part*/
    }
}
