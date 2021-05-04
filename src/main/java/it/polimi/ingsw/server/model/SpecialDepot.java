package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.exceptions.DepotException;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.ArrayList;
import java.util.List;

/**
 * Special Depot. Consists of a list of SpecialContainers for a specific resource
 */

public class SpecialDepot extends Depot {
    private final List<SpecialContainer> specialContainers;

    public SpecialDepot() {
        specialContainers = new ArrayList<>();
    }

    /**
     * Add a resource container for a specific resource.
     *
     * @param resourceType the only resource type that the container can store.
     */
    public void addSpecialContainer(ResourceType resourceType) {
        specialContainers.add(new SpecialContainer(resourceType));
    }


    @Override
    public void addResources(ResourceType resourceType, int numResources) throws DepotException {
        boolean notExist = true;
        for (SpecialContainer selectedContainer : specialContainers) {
            if (selectedContainer.getType() == resourceType) {
                selectedContainer.addResource(numResources);
                notExist = false;
            }
        }
        if (notExist) throw new DepotException("Impossible to add resources, SpecialDepot not active");
    }

    @Override
    public void removeResources(ResourceType resourceType, int numResource) throws DepotException {
        boolean notExist = true;
        for (SpecialContainer selectedContainer : specialContainers) {
            if (selectedContainer.getType() == resourceType) {
                selectedContainer.remove(numResource);
                notExist = false;
            }
        }
        if (notExist) throw new DepotException("Impossible to remove resources, SpecialDepot not active");
    }



    @Override
    public int getAllResourceCount() {
        int count = 0;
        for (SpecialContainer tmpContainer : specialContainers) {
            count = count + tmpContainer.getCount();
        }
        return count;
    }

    @Override
    public int getSpecificResourceCount(ResourceType resourceType) {
        int count = 0;
        for (SpecialContainer tmpContainer : specialContainers) {
            if (tmpContainer.getType() == resourceType)
                count = count + tmpContainer.getCount();
        }
        return count;
    }
}
