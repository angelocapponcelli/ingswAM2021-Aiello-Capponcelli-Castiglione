package it.polimi.ingsw.server.model.personalBoard.depots;

import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedTemporaryDepotMessage;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.StrongBoxContainer;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Depot where resources are temporary stored after being took from the market.
 */
public class TemporaryDepot extends StrongBoxDepot {
    private final List<StrongBoxContainer> containers;

    /**
     * Class constructor. Instantiates a new Temporary Depot.
     */
    public TemporaryDepot() {
        containers = new ArrayList<>();
        containers.add(new StrongBoxContainer(ResourceType.ANY));
        containers.add(new StrongBoxContainer(ResourceType.COIN));
        containers.add(new StrongBoxContainer(ResourceType.STONE));
        containers.add(new StrongBoxContainer(ResourceType.SERVANT));
        containers.add(new StrongBoxContainer(ResourceType.SHIELD));
    }


    /**
     * Adds resources taken from the market
     * @param resources the resources
     */
    public void addResource(List<ResourceType> resources) {

        resources.forEach(resource -> {
                    try {
                        containers.stream()
                                .filter(containers -> containers.getType().equals(resource))
                                .findFirst().orElse(null).addResource(1);
                    } catch (DepotException e) {
                        e.printStackTrace();
                    }
                });
        notifyObserver(new UpdatedTemporaryDepotMessage(toReduced()));

    }

    /**
     * Removes the resource selected
     * @param resourceType the resource to be removed
     * @param numResource the amount of the resource that has to be removed
     * @throws DepotException if the count of the resources is less than numResource
     */
    @Override
    public void removeResources(ResourceType resourceType, int numResource) throws DepotException {
        containers.stream()
                .filter(containers -> containers.getType().equals(resourceType))
                .findFirst()
                .orElse(null).remove(numResource);
    }


    /**
     * Gets the sum of every resource that it isn't an 'ANY' resource.
     * @return sum of resources different from 'ANY'
     */
    public int getAllResourceCountNoAny() {
        AtomicInteger count = new AtomicInteger();
        containers.stream().filter(strongBoxContainer -> !strongBoxContainer.getType().equals(ResourceType.ANY)).forEach(container -> {
            count.addAndGet(container.getCount());
        });
        return count.get();
    }


    /**
     * Gets the sum of every resource in it
     * @return sum of resources
     */
    @Override
    public int getAllResourceCount() {
        AtomicInteger count = new AtomicInteger();
        containers.forEach(container -> {
                count.addAndGet(container.getCount());
        });
        return count.get();
    }


    /**
     * @param resourceType the resource
     * @return 0
     */
    @Override
    public int getSpecificResourceCount(ResourceType resourceType) {
        return 0;
    }

    /**
     * Clear. Re-initializes the depot for market by removing all the resources stored in.
     */
    public void clear() {
        for (StrongBoxContainer strongBoxContainer : containers) {
            strongBoxContainer.clear();
        }


    }

    /**
     * Creates a reduced version of this depot
     * @return map that represents the reduced version of this depot
     */
    private Map<ResourceType, Integer> toReduced() {
        return containers.stream().collect(Collectors.toMap(StrongBoxContainer::getType, StrongBoxContainer::getCount));
    }
}
