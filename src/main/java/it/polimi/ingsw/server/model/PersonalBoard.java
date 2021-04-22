package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.List;

/** HAS TO BE MODIFIED BECAUSE THERE ARE NO DEPOT FOR MARKET*/
public class PersonalBoard {
    private List<Depot> depotForMarket;
    private StrongBoxDepot strongBoxDepot;
    private PersonalDevelopmentBoard personalDevelopmentBoard;
    private InHandLeaderCard inHandLeaderCard;
    private List<ProductionPower> productionPower;

    public PersonalBoard(List<Depot> depotForMarket, StrongBoxDepot strongBoxDepot, PersonalDevelopmentBoard personalDevelopmentBoard,InHandLeaderCard inHandLeaderCard, List<ProductionPower> productionPower){
        this.depotForMarket=depotForMarket;
        this.strongBoxDepot=strongBoxDepot;
        this.personalDevelopmentBoard= personalDevelopmentBoard;
        this.inHandLeaderCard=inHandLeaderCard;
        this.productionPower=productionPower;
    }
    /**to do verify resources(?)*/

    public void addProductionPower(ProductionPower productionPower1){
        this.productionPower.add(productionPower1);
    }

    public List<Depot> getDepotForMarket(){
        return depotForMarket;
    }

    public List<SpecialDepot> getSpecialDepot () {
        List<SpecialDepot> specialDepots = new ArrayList<>();
        for (Depot tmp : depotForMarket) {
            if(tmp.getClass() == SpecialDepot.class) specialDepots.add((SpecialDepot) tmp);
        }
            return  specialDepots;
    }

    public List<WareHouseDepot> getWarehouseDepot () {
        List<WareHouseDepot> wareHouseDepots = new ArrayList<>();
        for (Depot tmp : depotForMarket) {
            if(tmp.getClass() == WareHouseDepot.class) wareHouseDepots.add((WareHouseDepot) tmp);
        }
        return  wareHouseDepots;
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
