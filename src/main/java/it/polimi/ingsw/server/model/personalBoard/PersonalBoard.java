package it.polimi.ingsw.server.model.personalBoard;

import it.polimi.ingsw.server.model.personalBoard.depots.SpecialDepot;
import it.polimi.ingsw.server.model.personalBoard.depots.StrongBoxDepot;
import it.polimi.ingsw.server.model.personalBoard.depots.TemporaryDepot;
import it.polimi.ingsw.server.model.personalBoard.depots.WareHouseDepot;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;


/**
 * Real Player's personal board
 */
public class PersonalBoard extends Observable {
    private final TemporaryDepot temporaryDepot;
    private final WareHouseDepot wareHouseDepot;
    private final SpecialDepot specialDepot;
    private final StrongBoxDepot strongBoxDepot;
    private final PersonalDevelopmentBoard personalDevelopmentBoard;
    private final InHandLeaderCard inHandLeaderCards;
    private final List<ProductionPower> productionPowers;


    /**
     * Class constructor. Instantiates a new Personal Board.
     * It instantiates a new Warehouse depot, a new Special depot, a new temporary depot, a new strongbox, a new personal
     * development board, a new in hand leader card, a new list of production power.
     */
    public PersonalBoard() {
        wareHouseDepot = new WareHouseDepot();
        specialDepot = new SpecialDepot();
        temporaryDepot = new TemporaryDepot();
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
    public TemporaryDepot getTemporaryDepot() {
        return temporaryDepot;
    }

    /**
     * Gets special depot
     * @return special depot
     */
    public SpecialDepot getSpecialDepots() {
        return specialDepot;
    }

    /**
     * Gets warehouse depot of the player
     * @return warehouse depot
     */
    public WareHouseDepot getWareHouseDepot() {
        return wareHouseDepot;
    }

    /**
     * Gets personal development board
     * @return personal development board of the player
     */
    public PersonalDevelopmentBoard getPersonalDevelopmentBoard() {
        return personalDevelopmentBoard;
    }

    /**
     * Gets in hand leader card
     * @return leader cards of the player
     */
    public InHandLeaderCard getInHandLeaderCards() {
        return inHandLeaderCards;
    }

    /**
     * Gets list of the production power
     * @return list of possible production power
     */
    public List<ProductionPower> getProductionPowers() {
        return productionPowers;
    }

    /**
     * Gets strongbox
     * @return strongbox
     */
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

    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        inHandLeaderCards.addObserver(obs);
        temporaryDepot.addObserver(obs);
        wareHouseDepot.addObserver(obs);
        specialDepot.addObserver(obs);
        personalDevelopmentBoard.addObserver(obs);
        strongBoxDepot.addObserver(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        super.removeObserver(obs);
        inHandLeaderCards.removeObserver(obs);
        temporaryDepot.removeObserver(obs);
        wareHouseDepot.removeObserver(obs);
        personalDevelopmentBoard.removeObserver(obs);
        strongBoxDepot.removeObserver(obs);
    }
}
