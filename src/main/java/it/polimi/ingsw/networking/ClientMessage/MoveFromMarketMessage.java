package it.polimi.ingsw.networking.ClientMessage;


import it.polimi.ingsw.server.model.resources.Any;
import it.polimi.ingsw.server.model.resources.Faith;
import it.polimi.ingsw.server.model.resources.ResourceType;


public class MoveFromMarketMessage extends ClientMessage{

    private ResourceType resourceType;
    private Integer numberOfContainer;
    private String depot;

    public MoveFromMarketMessage(String nickname,ResourceType resourceType, Integer numberOfContainer, String depot) {
        super(nickname);
        this.numberOfContainer= numberOfContainer;
        this.resourceType=resourceType;
        this.depot=depot;
    }

    @Override
    public Boolean check(){
        if(this.depot!="Warehouse" || this.depot!="Special"){
            return false;
        }
        if (ResourceType.getResourceClass(this.resourceType) != Any.getInstance() || ResourceType.getResourceClass(this.resourceType) != Faith.getInstance()){
            return false;
        }
        return true;
    }

    public Integer getNumberOfContainer() {
        return numberOfContainer;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public String getDepot(){
        return this.depot;
    }
}
