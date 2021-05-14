package it.polimi.ingsw.server.model.personalBoard;

import it.polimi.ingsw.server.model.personalBoard.depots.SpecialDepot;
import it.polimi.ingsw.server.model.personalBoard.depots.StrongBoxDepot;
import it.polimi.ingsw.server.model.personalBoard.depots.TemporaryDepotForMarket;
import it.polimi.ingsw.server.model.personalBoard.depots.WareHouseDepot;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.ArrayList;
import java.util.List;


/**
 * Real Player's personal board
 */
public class PersonalBoard {
    private final TemporaryDepotForMarket temporaryDepotForMarket;
    private final WareHouseDepot wareHouseDepot;
    private final SpecialDepot specialDepot;
    private final StrongBoxDepot strongBoxDepot;
    private final PersonalDevelopmentBoard personalDevelopmentBoard;
    private final InHandLeaderCard inHandLeaderCards;
    private final List<ProductionPower> productionPowers;

    public PersonalBoard() {
        wareHouseDepot = new WareHouseDepot();
        specialDepot = new SpecialDepot();
        temporaryDepotForMarket = new TemporaryDepotForMarket();
        strongBoxDepot = new StrongBoxDepot();
        personalDevelopmentBoard = new PersonalDevelopmentBoard();
        inHandLeaderCards = new InHandLeaderCard();
        productionPowers = new ArrayList<>();
    }


    /**
     * Add a production power to the personalBoard.
     *
     * @param productionPower the production power to be added.
     */
    public void addProductionPower(ProductionPower productionPower) {
        this.productionPowers.add(productionPower);
    }

    /**
     * Gets depot for market.
     *
     * @return the temporary depot used to store resources took from market.
     */
    public TemporaryDepotForMarket getTemporaryDepotForMarket() {
        return temporaryDepotForMarket;
    }

    public SpecialDepot getSpecialDepots() {
        return specialDepot;
    }

    public WareHouseDepot getWareHouseDepot() {
        return wareHouseDepot;
    }

    public PersonalDevelopmentBoard getPersonalDevelopmentBoard() {
        return personalDevelopmentBoard;
    }

    public InHandLeaderCard getInHandLeaderCards() {
        return inHandLeaderCards;
    }

    public List<ProductionPower> getProductionPowers() {
        return productionPowers;
    }

    public StrongBoxDepot getStrongBoxDepot() {
        return strongBoxDepot;
    }

    /**
     * @param resourceType The resource's type of which the count is requested
     * @return the resource's count of the specified type.
     */
    public int getSpecificResourceCount(ResourceType resourceType) {
        return wareHouseDepot.getSpecificResourceCount(resourceType) + specialDepot.getSpecificResourceCount(resourceType) + strongBoxDepot.getSpecificResourceCount(resourceType);
    }

    /**
     * Get the count of all the resources, stored inside the depots, regardless of the type.
     * Used at the end of the game to calculate the victory points.
     *
     * @return the int
     */
    public int getAllResourceCount() {
        return wareHouseDepot.getAllResourceCount() + specialDepot.getAllResourceCount() + strongBoxDepot.getAllResourceCount();
    }

}