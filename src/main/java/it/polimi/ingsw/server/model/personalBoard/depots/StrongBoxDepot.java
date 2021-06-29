package it.polimi.ingsw.server.model.personalBoard.depots;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedContainer;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedStrongBoxMessage;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.StrongBoxContainer;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;

import java.util.ArrayList;
import java.util.List;

/**
 * Depot that include four StrongBoxContainers: one for each resource
 */
public class StrongBoxDepot extends Depot {
    protected final List<StrongBoxContainer> containers;

    /**
     * Class constructor. Instantiates a new Strong Box. It also instantiates four strong box containers set with the
     * four different concrete resource.
     */
    public StrongBoxDepot() {
        containers = new ArrayList<>();
        containers.add(new StrongBoxContainer(ResourceType.COIN));
        containers.add(new StrongBoxContainer(ResourceType.STONE));
        containers.add(new StrongBoxContainer(ResourceType.SERVANT));
        containers.add(new StrongBoxContainer(ResourceType.SHIELD));
    }

    /**
     * Performs the increment of the resource's count in the strong box
     * @param resourceType the resource type of the resources to be added
     * @param numResource  the number of resources to be added
     */
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


    /**
     * Performs the removal of the quantity of the specified resource from the correspondent strong box container.
     * @param resourceType the resource to be removed
     * @param numResource the amount of the resource that has to be removed
     * @throws DepotException if the strongbox container's size is less than the numResource
     */
    @Override
    public void removeResources(ResourceType resourceType, int numResource) throws DepotException {
        for (StrongBoxContainer selectedContainer : containers) {
            if (selectedContainer.getType() == resourceType)
                selectedContainer.remove(numResource);
        }
        notifyObserver(new UpdatedStrongBoxMessage(toReduced()));
    }

    /**
     * Gets the count of all resources, which it is useful when the game is ending to count the resources and convert
     * them into victory points
     * @return sum of all resources in the strong box
     */
    @Override
    public int getAllResourceCount() {
        int count = 0;
        for (StrongBoxContainer tmpContainer : containers) {
            count = count + tmpContainer.getCount();
        }
        return count;
    }

    /**
     * Gets the count of a specific resource in the strong box
     * @param resourceType the resource
     * @return count of the resource
     */
    @Override
    public int getSpecificResourceCount(ResourceType resourceType) {
        int count = 0;
        for (StrongBoxContainer tmpContainer : containers) {
            if (tmpContainer.getType() == resourceType)
                count = count + tmpContainer.getCount();
        }
        return count;
    }

    /**
     * Performs the reduction of the strongbox. It gets a reduced version of the strongbox
     * @return reduced version of the strongbox
     */
    private List<ReducedContainer> toReduced(){
        List<ReducedContainer> reduced = new ArrayList<>();
        for(StrongBoxContainer strongBoxContainer: containers){
            reduced.add(new ReducedContainer(strongBoxContainer.getType(), strongBoxContainer.getCount()));
        }

        return reduced;

    }

}
