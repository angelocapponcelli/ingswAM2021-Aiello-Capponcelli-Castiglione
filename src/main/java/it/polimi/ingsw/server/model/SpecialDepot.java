package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.List;

public class SpecialDepot extends Depot{
    private List<SpecialContainer> containers;

    public SpecialDepot() {
        containers = new ArrayList<>();
    }

    public void addContainer(ResourceType resource) {
        containers.add(new SpecialContainer(resource));
    }

    @Override
    public void add(ResourceType resource, int numResource) throws DepotException {
        boolean notExist = true;
        for (SpecialContainer selectedContainer: containers) {
            if (selectedContainer.getType() == resource) {
                selectedContainer.add(numResource);
                notExist = false;
            }
        }
        if(notExist) throw new DepotException("Impossible to add resources, SpecialDepot not active");
    }

    @Override
    public void remove(ResourceType resource, int numResource) throws DepotException {
        boolean notExist = true;
        for (SpecialContainer selectedContainer: containers) {
            if (selectedContainer.getType() == resource) {
                selectedContainer.remove(numResource);
                notExist = false;
            }
        }
        if(notExist) throw new DepotException("Impossible to remove resources, SpecialDepot not active");
    }

    @Override
    public int getResourceCount() {
        int count = 0;
        for (SpecialContainer tmpContainer: containers) {
            count = count + tmpContainer.getCount();
        }
        return count;
    }
}
