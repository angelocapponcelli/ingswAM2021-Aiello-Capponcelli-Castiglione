package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Depot that include a three WareHouseContainer, each container represent a shelf so each
 * container has resources different from the other, it's possible to swap container
 */

public class WareHouseDepot extends Depot{
    private List<WareHouseContainer> containers;

    public WareHouseDepot() {
        containers = new ArrayList<>();
        containers.add(new WareHouseContainer(1));
        containers.add(new WareHouseContainer(2));
        containers.add(new WareHouseContainer(3));
    }

    public void add(ResourceType resource, int numResource, int indexShelf) throws DepotException {
        if (containers.get(indexShelf).getType() == resource)
            containers.get(indexShelf).add(numResource);
        else if (containers.get(indexShelf).getType() == null) {
            for (WareHouseContainer selectedContainer: containers) {
                if (selectedContainer.getType() == resource)
                    throw new DepotException("Not possible to add resources to this shelf: another shelf have same resource type");
            }
            containers.get(indexShelf).setResourceType(resource);
            try {
                containers.get(indexShelf).add(numResource);
            } catch (DepotException e) {
                containers.get(indexShelf).setResourceType(null);
                throw e;
            }
        } else throw new DepotException("Not possible to add resources to this shelf: shelf not Empty");
    }

    @Override
    public void remove(Resource resource, int numResource) throws DepotException {
        boolean notExist = true;
        for (WareHouseContainer selectedContainer: containers) {
            if (selectedContainer.getType() != null && ResourceType.getResourceClass(selectedContainer.getType()) == resource) {
                selectedContainer.remove(numResource);
                if (selectedContainer.getCount() == 0) selectedContainer.setResourceType(null);
                notExist = false;
            }
        }
        if(notExist) throw new DepotException("Impossible to remove resources, resource type not available");
    }

    public void swap (int shelf1, int shelf2) throws DepotException {
        int tmpCapacity;
        if (shelf1 > shelf2){
            tmpCapacity = containers.get(shelf1).getCapacity();
            containers.get(shelf1).setCapacity(containers.get(shelf2).getCapacity());
            containers.get(shelf2).setCapacity(tmpCapacity);
        } else {
            tmpCapacity = containers.get(shelf2).getCapacity();
            containers.get(shelf2).setCapacity(containers.get(shelf1).getCapacity());
            containers.get(shelf1).setCapacity(tmpCapacity);
        }
        Collections.swap(containers,shelf1,shelf2);
    }
    @Override
    public int getResourceCount() {
        int count = 0;
        for (WareHouseContainer tmpContainer: containers) {
            count = count + tmpContainer.getCount();
        }
        return count;
    }

    @Override
    public int getResourceCount(Resource resource){
        int count = 0;
        for (WareHouseContainer tmpContainer: containers) {
            if (tmpContainer.getType() != null && ResourceType.getResourceClass(tmpContainer.getType()) == resource) count = count + tmpContainer.getCount();
        }
        return count;
    }
}
