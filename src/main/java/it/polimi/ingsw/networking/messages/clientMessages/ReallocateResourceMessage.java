package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.server.model.resources.Any;
import it.polimi.ingsw.server.model.resources.Faith;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class ReallocateResourceMessage extends ClientMessage{

    private ResourceType resourceType;
    private String depotName1;
    private String depotName2;
    private Integer numberOfContainer1;
    private Integer numberOfContainer2;

    public ReallocateResourceMessage(String nickname,ResourceType resourceType,Integer numberOfContainer1,Integer numberOfContainer2,String depotName1,String depotName2) {
        super(nickname);
        this.resourceType=resourceType;
        this.numberOfContainer1=numberOfContainer1;
        this.numberOfContainer2=numberOfContainer2;
        this.depotName1=depotName1;
        this.depotName2=depotName2;
    }

    @Override
    public Boolean check(){
        if (this.depotName1!= "Warehouse" || this.depotName1!="Special"){
            return false;
        }
        if (this.resourceType.getResourceClass(this.resourceType).equals(Any.getInstance())||this.resourceType.getResourceClass(this.resourceType).equals(Faith.getInstance())){
            return false;
        }
        if (this.depotName2!= "Warehouse" || this.depotName2!="Special"){
            return false;
        }
        return true;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public Integer getNumberOfContainer1() {
        return numberOfContainer1;
    }

    public Integer getNumberOfContainer2() {
        return numberOfContainer2;
    }

    public String getDepotName1() {
        return depotName1;
    }

    public String getDepotName2() {
        return depotName2;
    }
}
