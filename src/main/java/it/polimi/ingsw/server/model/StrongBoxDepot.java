package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.ArrayList;
import java.util.List;

/**
 * Depot that include four StrongBoxContainers: one for each resource
 */
public class StrongBoxDepot extends Depot {
    private final List<StrongBoxContainer> containers;

    public StrongBoxDepot() {
        containers = new ArrayList<>();
        containers.add(new StrongBoxContainer(ResourceType.COIN));
        containers.add(new StrongBoxContainer(ResourceType.STONE));
        containers.add(new StrongBoxContainer(ResourceType.SERVANT));
        containers.add(new StrongBoxContainer(ResourceType.SHIELD));
    }

    @Override
    public void add(ResourceType resource, int numResource) throws DepotException {
        for (StrongBoxContainer selectedContainer : containers) {
            if (selectedContainer.getType() == resource) selectedContainer.add(numResource);
        }
    }

    @Override
    public void remove(Resource resource, int numResource) throws DepotException {
        for (StrongBoxContainer selectedContainer : containers) {
            if (ResourceType.getResourceClass(selectedContainer.getType()) == resource)
                selectedContainer.remove(numResource);
        }
    }

    @Override
    public int getResourceCount() {
        int count = 0;
        for (StrongBoxContainer tmpContainer : containers) {
            count = count + tmpContainer.getCount();
        }
        return count;
    }

    @Override
    public int getResourceCount(Resource resource) {
        int count = 0;
        for (StrongBoxContainer tmpContainer : containers) {
            if (ResourceType.getResourceClass(tmpContainer.getType()) == resource)
                count = count + tmpContainer.getCount();
        }
        return count;
    }
}
