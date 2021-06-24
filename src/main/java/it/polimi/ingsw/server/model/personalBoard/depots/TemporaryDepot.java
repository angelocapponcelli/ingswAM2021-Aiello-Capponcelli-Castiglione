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

    public TemporaryDepot() {
        containers = new ArrayList<>();
        containers.add(new StrongBoxContainer(ResourceType.ANY));
        containers.add(new StrongBoxContainer(ResourceType.COIN));
        containers.add(new StrongBoxContainer(ResourceType.STONE));
        containers.add(new StrongBoxContainer(ResourceType.SERVANT));
        containers.add(new StrongBoxContainer(ResourceType.SHIELD));
    }

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


    @Override
    public void removeResources(ResourceType resourceType, int numResource) throws DepotException {
        containers.stream()
                .filter(containers -> containers.getType().equals(resourceType))
                .findFirst()
                .orElse(null).remove(numResource);
    }


    public int getAllResourceCountNoAny() {
        AtomicInteger count = new AtomicInteger();
        containers.stream().filter(strongBoxContainer -> !strongBoxContainer.getType().equals(ResourceType.ANY)).forEach(container -> {
            count.addAndGet(container.getCount());
        });
        return count.get();
    }


    @Override
    public int getAllResourceCount() {
        AtomicInteger count = new AtomicInteger();
        containers.forEach(container -> {
                count.addAndGet(container.getCount());
        });
        return count.get();
    }



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

    private Map<ResourceType, Integer> toReduced() {
        return containers.stream().collect(Collectors.toMap(StrongBoxContainer::getType, StrongBoxContainer::getCount));
    }
}
