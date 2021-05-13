package it.polimi.ingsw.networking.ClientMessage;

import it.polimi.ingsw.server.model.resources.Any;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class SelectResourceTypeClientMessage extends ClientMessage {
    private ResourceType resourceType;

    public SelectResourceTypeClientMessage(String nickname, ResourceType resourceType1) {
        super(nickname);
        this.resourceType = resourceType1;
    }
    @Override
    public Boolean check(){
        if (ResourceType.getResourceClass(this.resourceType) != Any.getInstance()){
            return false;
        }
        return true;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }
}
