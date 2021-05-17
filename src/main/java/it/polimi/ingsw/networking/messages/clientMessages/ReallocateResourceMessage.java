package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.server.model.resources.Any;
import it.polimi.ingsw.server.model.resources.Faith;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class ReallocateResourceMessage extends ClientMessage {

    private final ResourceType resourceType;
    private final String depotName1;
    private final String depotName2;
    private final Integer numberOfContainer1;
    private final Integer numberOfContainer2;

    public ReallocateResourceMessage(String nickname, ResourceType resourceType, Integer numberOfContainer1, Integer numberOfContainer2, String depotName1, String depotName2) {
        super(nickname);
        this.resourceType = resourceType;
        this.numberOfContainer1 = numberOfContainer1;
        this.numberOfContainer2 = numberOfContainer2;
        this.depotName1 = depotName1;
        this.depotName2 = depotName2;
    }

    @Override
    public Boolean check() {
        if (this.depotName1 != "Warehouse" || this.depotName1 != "Special") {
            return false;
        }
        if (ResourceType.getResourceClass(this.resourceType).equals(Any.getInstance()) || ResourceType.getResourceClass(this.resourceType).equals(Faith.getInstance())) {
            return false;
        }
        return this.depotName2 == "Warehouse" && this.depotName2 == "Special";
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
