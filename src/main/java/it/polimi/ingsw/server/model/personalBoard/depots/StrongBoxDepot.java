package it.polimi.ingsw.server.model.personalBoard.depots;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedContainer;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedStrongBoxMessage;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.StrongBoxContainer;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.WareHouseContainer;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;

import java.util.ArrayList;
import java.util.List;

/**
 * Depot that include four StrongBoxContainers: one for each resource
 */
public class StrongBoxDepot extends Depot {
    protected final List<StrongBoxContainer> containers;

    public StrongBoxDepot() {
        containers = new ArrayList<>();
        containers.add(new StrongBoxContainer(ResourceType.COIN));
        containers.add(new StrongBoxContainer(ResourceType.STONE));
        containers.add(new StrongBoxContainer(ResourceType.SERVANT));
        containers.add(new StrongBoxContainer(ResourceType.SHIELD));
    }

    @Override
    public void addResources(ResourceType resourceType, int numResource) {
        for (StrongBoxContainer selectedContainer : containers) {
            if (selectedContainer.getType() == resourceType) {
                try {
                    selectedContainer.addResource(numResource);
                    notifyObserver(new UpdatedStrongBoxMessage(toReduced()));

                } catch (DepotException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void removeResources(ResourceType resourceType, int numResource) throws DepotException {
        for (StrongBoxContainer selectedContainer : containers) {
            if (selectedContainer.getType() == resourceType)
                selectedContainer.remove(numResource);
        }
        notifyObserver(new UpdatedStrongBoxMessage(toReduced()));
    }

    @Override
    public int getAllResourceCount() {
        int count = 0;
        for (StrongBoxContainer tmpContainer : containers) {
            count = count + tmpContainer.getCount();
        }
        return count;
    }

    @Override
    public int getSpecificResourceCount(ResourceType resourceType) {
        int count = 0;
        for (StrongBoxContainer tmpContainer : containers) {
            if (tmpContainer.getType() == resourceType)
                count = count + tmpContainer.getCount();
        }
        return count;
    }


    private List<ReducedContainer> toReduced(){
        List<ReducedContainer> reduced = new ArrayList<>();
        for(StrongBoxContainer strongBoxContainer: containers){
            reduced.add(new ReducedContainer(strongBoxContainer.getType(), strongBoxContainer.getCount()));
        }

        return reduced;

    }

}
