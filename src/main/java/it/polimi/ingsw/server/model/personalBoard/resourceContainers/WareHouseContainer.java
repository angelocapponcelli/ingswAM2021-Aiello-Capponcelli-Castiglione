package it.polimi.ingsw.server.model.personalBoard.resourceContainers;

import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;

/**
 * WareHouseContainer allows to set resource type, or capacity
 */

public class WareHouseContainer extends ResourceContainer {

    /**
     * Class constructor. Instantiates a new Special container
     * @param capacity the maximum amount of resources that characterizes the container
     */
    public WareHouseContainer(int capacity) {
        super(capacity);
    }

    /**
     * set resource type of container
     *
     * @param resource type of resource to setting
     * @throws DepotException if is not possible to set new resource type
     */
    public void setResourceType(ResourceType resource) throws DepotException {
        if (count > 0) throw new DepotException("Depot not empty, impossible to change resource type");
        this.resourceType = resource;
    }

    /**
     * Performs the setting of the capacity of the container
     * @param capacity the maximum amount of resources that characterizes the container
     * @throws DepotException if there are too many resources
     */
    public void setCapacity(int capacity) throws DepotException {
        if (capacity < 0) this.capacity = -1;
        else {
            if (count > capacity)
                throw new DepotException("There are too many resources, impossible to change capacity");
            else this.capacity = capacity;
        }
    }
}
