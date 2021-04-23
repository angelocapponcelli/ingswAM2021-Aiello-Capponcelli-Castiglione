package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * HAS TO BE MODIFIED BECAUSE THERE ARE NO DEPOT FOR MARKET
 */
public class PersonalBoard {
    private final List<Depot> depotForMarket;
    private final StrongBoxDepot strongBoxDepot;
    private final PersonalDevelopmentBoard personalDevelopmentBoard;
    private final InHandLeaderCard inHandLeaderCard;
    private final List<ProductionPower> productionPower;

    public PersonalBoard(){
        depotForMarket = new ArrayList<>();
        depotForMarket.add(new WareHouseDepot());
        depotForMarket.add(new SpecialDepot());
        strongBoxDepot = new StrongBoxDepot();
        this.personalDevelopmentBoard= new PersonalDevelopmentBoard();
        this.inHandLeaderCard= new InHandLeaderCard();
        this.productionPower= new ArrayList<>();
    }


    public void addProductionPower(ProductionPower productionPower1) {
        this.productionPower.add(productionPower1);
    }

    public List<Depot> getDepotForMarket() {
        return depotForMarket;
    }

    public List<SpecialDepot> getSpecialDepot() {
        List<SpecialDepot> specialDepots = new ArrayList<>();
        for (Depot tmp : depotForMarket) {
            if (tmp.getClass() == SpecialDepot.class) specialDepots.add((SpecialDepot) tmp);
        }
        return specialDepots;
    }

    public List<WareHouseDepot> getWareHouseDepot() {
        List<WareHouseDepot> wareHouseDepots = new ArrayList<>();
        for (Depot tmp : depotForMarket) {
            if (tmp.getClass() == WareHouseDepot.class) wareHouseDepots.add((WareHouseDepot) tmp);
        }
        return wareHouseDepots;
    }

    public PersonalDevelopmentBoard getPersonalDevelopmentBoard() {
        return personalDevelopmentBoard;
    }

    public InHandLeaderCard getInHandLeaderCard() {
        return inHandLeaderCard;
    }

    public List<ProductionPower> getProductionPower() {
        return productionPower;
    }

    public StrongBoxDepot getStrongBoxDepot() {
        return strongBoxDepot;
    }

}
