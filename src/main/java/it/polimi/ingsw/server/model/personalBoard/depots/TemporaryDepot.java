package it.polimi.ingsw.server.model.personalBoard.depots;

import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedTemporaryDepotMessage;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.StrongBoxContainer;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

        /*for (StrongBoxContainer selectedContainer : containers) {
            if (selectedContainer.getType().equals(resourceType)) {
                try {
                    selectedContainer.addResource(1);
                    notifyObserver(new UpdatedTemporaryDepotMessage(toReduced()));
                } catch (DepotException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }


    @Override
    public void removeResources(ResourceType resourceType, int numResource) throws DepotException {

    }

    @Override
    public int getAllResourceCount() {
        return 0;
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
