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
     * @param resourceType the only resource type that the container can store.
     */
    public void addSpecialContainer(ResourceType resourceType) {
        specialContainers.add(new SpecialContainer(resourceType));
    }

    /**
     * Add resources to special depot
     * @param resourceType resource type to be added
     * @param numResources resource number to be added
     * @throws DepotException If is impossible to complete operation
     */
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

    /**
     * Remove resources to special depot
     * @param resourceType resource type to be removed
     * @param numResource resource number to be removed
     * @throws DepotException If is impossible to complete operation
     */
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

    /**
     * @return total resources count in the depot
     */
    @Override
    public int getAllResourceCount() {
        int count = 0;
        for (SpecialContainer tmpContainer : specialContainers) {
            count = count + tmpContainer.getCount();
        }
        return count;
    }

    /**
     * @return total resources count in the depot of a specific resource type
     */
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
