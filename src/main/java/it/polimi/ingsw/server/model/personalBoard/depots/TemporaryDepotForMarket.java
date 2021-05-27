package it.polimi.ingsw.server.model.personalBoard.depots;

import it.polimi.ingsw.utils.exceptions.DepotException;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.StrongBoxContainer;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.ArrayList;
import java.util.List;

/**
 * Depot where resources are temporary stored after being took from the market.
 */
public class TemporaryDepotForMarket extends StrongBoxDepot {
    private final List<StrongBoxContainer> containers;

    public TemporaryDepotForMarket() {
        containers = new ArrayList<>();
        containers.add(new StrongBoxContainer(ResourceType.ANY));
        containers.add(new StrongBoxContainer(ResourceType.COIN));
        containers.add(new StrongBoxContainer(ResourceType.STONE));
        containers.add(new StrongBoxContainer(ResourceType.SERVANT));
        containers.add(new StrongBoxContainer(ResourceType.SHIELD));
    }

    public void addResource(ResourceType resourceType) {
        for (StrongBoxContainer selectedContainer : containers) {
            if (selectedContainer.getType() == resourceType) {
                try {
                    selectedContainer.addResource(1);
                } catch (DepotException e) {
                    e.printStackTrace();
                }
            }
        }
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
}
