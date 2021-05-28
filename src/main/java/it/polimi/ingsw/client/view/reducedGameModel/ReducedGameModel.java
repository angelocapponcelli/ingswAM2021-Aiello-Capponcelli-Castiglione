package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.resources.ResourceType;
import java.util.*;

public class ReducedGameModel {

    private ResourceType[][] reducedMarketTray;
    private ResourceType slide;
    private List<ReducedLeaderCard> reducedInHandLeaderCard;
    private List<ReducedPlayer> players;
    private ReducedDevelopmentCard[][] reducedDevelopmentCardsGrid;
    private Boolean[] popeSpaceFlipped;
    private ReducedDevelopmentCard[] reducedPersonalDevelopmentBoard;
    private List<ReducedContainer> reducedWareHouseDepot;
    private Map<ResourceType, Integer> reducedStrongBoxDepot;
    private Map<ResourceType, Integer> reducedSpecialDepot;
    private Map<ResourceType, Integer> productionPowerInputBoard;
    private Map<ResourceType, Integer> productionPowerOutputBoard;

    public ReducedGameModel() {
        reducedMarketTray = new ResourceType[3][4];
        reducedDevelopmentCardsGrid = new ReducedDevelopmentCard[3][4];
        popeSpaceFlipped = new Boolean[]{false, false, false};
        reducedPersonalDevelopmentBoard = new ReducedDevelopmentCard[3];
        reducedWareHouseDepot = new ArrayList<>();
        reducedWareHouseDepot.add(new ReducedContainer(null, 0));
        reducedWareHouseDepot.add(new ReducedContainer(null, 0));
        reducedWareHouseDepot.add(new ReducedContainer(null, 0));
        reducedStrongBoxDepot = new HashMap<>();
        reducedStrongBoxDepot.put(ResourceType.COIN, 0);
        reducedStrongBoxDepot.put(ResourceType.SERVANT, 0);
        reducedStrongBoxDepot.put(ResourceType.SHIELD, 0);
        reducedStrongBoxDepot.put(ResourceType.STONE, 0);
        reducedSpecialDepot = new HashMap<>();
    }

    public List<ReducedLeaderCard> getReducedInHandLeaderCard() {
        return reducedInHandLeaderCard;
    }

    public List<ReducedPlayer> getPlayers() {
        return players;
    }

    public ReducedDevelopmentCard[][] getReducedDevelopmentCardsGrid() {
        return reducedDevelopmentCardsGrid;
    }

    public Boolean[] getPopeSpaceFlipped() {
        return popeSpaceFlipped;
    }

    public ReducedDevelopmentCard[] getReducedPersonalDevelopmentBoard() {
        return reducedPersonalDevelopmentBoard;
    }

    public List<ReducedContainer> getReducedWareHouseDepot() {
        return reducedWareHouseDepot;
    }

    public Map<ResourceType, Integer> getReducedStrongBoxDepot() {
        return reducedStrongBoxDepot;
    }

    public Map<ResourceType, Integer> getReducedSpecialDepot() {
        return reducedSpecialDepot;
    }

    public Map<ResourceType, Integer> getProductionPowerInputBoard() {
        return productionPowerInputBoard;
    }

    public Map<ResourceType, Integer> getProductionPowerOutputBoard() {
        return productionPowerOutputBoard;
    }

    public ResourceType[][] getReducedMarketTray() {
        return reducedMarketTray;
    }

    public ResourceType getSlide() {
        return slide;
    }

    public void setReducedMarketTray(ResourceType[][] reducedMarketTray) {
        this.reducedMarketTray = reducedMarketTray;
    }

    public void setSlide(ResourceType slide) {
        this.slide = slide;
    }

    public void setReducedInHandLeaderCard(List<Integer> reducedInHandLeaderCardID) {
        for (int i : reducedInHandLeaderCardID) {
            reducedInHandLeaderCard.add(new ReducedLeaderCard(i));
        }
    }

    public void setPlayers(List<ReducedPlayer> players) {
        this.players = players;
    }

    public void setReducedDevelopmentCardsGrid(ReducedDevelopmentCard[][] reducedDevelopmentCardsGrid) {
        this.reducedDevelopmentCardsGrid = reducedDevelopmentCardsGrid;
    }

    public void setPopeSpaceFlipped(Boolean[] popeSpaceFlipped) {
        this.popeSpaceFlipped = popeSpaceFlipped;
    }

    public void setReducedPersonalDevelopmentBoard(ReducedDevelopmentCard[] reducedPersonalDevelopmentBoard) {
        this.reducedPersonalDevelopmentBoard = reducedPersonalDevelopmentBoard;
    }

    public void setReducedWareHouseDepot(List<ReducedContainer> reducedWareHouseDepot) {
        this.reducedWareHouseDepot = reducedWareHouseDepot;
    }

    public void setReducedStrongBoxDepot(Map<ResourceType, Integer> reducedStrongBoxDepot) {
        this.reducedStrongBoxDepot = reducedStrongBoxDepot;
    }

    public void setReducedSpecialDepot(Map<ResourceType, Integer> reducedSpecialDepot) {
        this.reducedSpecialDepot = reducedSpecialDepot;
    }

    public void setProductionPowerInputBoard(Map<ResourceType, Integer> productionPowerInputBoard) {
        this.productionPowerInputBoard = productionPowerInputBoard;
    }

    public void setProductionPowerOutputBoard(Map<ResourceType, Integer> productionPowerOutputBoard) {
        this.productionPowerOutputBoard = productionPowerOutputBoard;
    }
}
