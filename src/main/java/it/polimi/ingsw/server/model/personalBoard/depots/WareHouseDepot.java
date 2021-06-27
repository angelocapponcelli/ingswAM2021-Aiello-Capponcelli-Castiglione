package it.polimi.ingsw.server.model.personalBoard.depots;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedContainer;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedWareHouseMessage;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.WareHouseContainer;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Depot that include a three WareHouseContainer, each container represent a shelf so each
 * container has resources different from the other, it's possible to swap container
 */

public class WareHouseDepot extends Depot {
    private final List<WareHouseContainer> wareHouseContainers;

    public WareHouseDepot() {
        wareHouseContainers = new ArrayList<>();
        wareHouseContainers.add(new WareHouseContainer(1));
        wareHouseContainers.add(new WareHouseContainer(2));
        wareHouseContainers.add(new WareHouseContainer(3));
    }

    public void addResource(ResourceType resource, int numResource, int indexShelf) throws DepotException {
        if (wareHouseContainers.get(indexShelf).getType() == resource)
            wareHouseContainers.get(indexShelf).addResource(numResource);
        else if (wareHouseContainers.get(indexShelf).getType() == null) {
            for (WareHouseContainer selectedContainer : wareHouseContainers) {
                if (selectedContainer.getType() == resource)
                    throw new DepotException("Not possible to add resources to this shelf: another shelf have same resource type");
            }
            wareHouseContainers.get(indexShelf).setResourceType(resource);
            try {
                wareHouseContainers.get(indexShelf).addResource(numResource);
            } catch (DepotException e) {
                wareHouseContainers.get(indexShelf).setResourceType(null);
                throw e;
            }
        } else throw new DepotException("Not possible to add resources to this shelf: shelf not Empty");

        notifyObserver(new UpdatedWareHouseMessage(toReduced()));

    }

    @Override
    public void removeResources(ResourceType resourceType, int numResources) throws DepotException {
        boolean notExist = true;
        for (WareHouseContainer selectedContainer : wareHouseContainers) {
            if (selectedContainer.getType() != null && selectedContainer.getType() == resourceType) {
                selectedContainer.remove(numResources);
                if (selectedContainer.getCount() == 0) selectedContainer.setResourceType(null);
                notExist = false;
            }
        }
        if (notExist) throw new DepotException("Impossible to remove resources, resource type not available");
        notifyObserver(new UpdatedWareHouseMessage(toReduced()));
    }

    public void swap(int shelf1, int shelf2) throws DepotException {
        int tmpCapacity;
        if (shelf1 > shelf2) {
            tmpCapacity = wareHouseContainers.get(shelf1).getCapacity();
            wareHouseContainers.get(shelf1).setCapacity(wareHouseContainers.get(shelf2).getCapacity());
            wareHouseContainers.get(shelf2).setCapacity(tmpCapacity);
        } else {
            tmpCapacity = wareHouseContainers.get(shelf2).getCapacity();
            wareHouseContainers.get(shelf2).setCapacity(wareHouseContainers.get(shelf1).getCapacity());
            wareHouseContainers.get(shelf1).setCapacity(tmpCapacity);
        }
        Collections.swap(wareHouseContainers, shelf1, shelf2);
        notifyObserver(new UpdatedWareHouseMessage(toReduced()));
    }

    @Override
    public int getAllResourceCount() {
        int count = 0;
        for (WareHouseContainer tmpContainer : wareHouseContainers) {
            count = count + tmpContainer.getCount();
        }
        return count;
    }

    @Override
    public int getSpecificResourceCount(ResourceType resourceType) {
        int count = 0;
        for (WareHouseContainer tmpContainer : wareHouseContainers) {
            if (tmpContainer.getType() == resourceType)
                count = count + tmpContainer.getCount();
        }
        return count;
    }


    private List<ReducedContainer> toReduced(){
        List<ReducedContainer> reduced = new ArrayList<>();
        for(WareHouseContainer wareHouseContainer: wareHouseContainers){
            reduced.add(new ReducedContainer(wareHouseContainer.getType(), wareHouseContainer.getCount()));
        }

        return reduced;

    }
}
