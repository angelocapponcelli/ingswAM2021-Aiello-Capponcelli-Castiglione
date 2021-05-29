package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReducedGameModel {
    private final ReducedMarketTray marketTray;
    private final ReducedInHandLeaderCards inHandLeaderCards;
    private List<ReducedPlayer> players;
    private ReducedDevelopmentCard[][] developmentCardsGrid;
    private Boolean[] popeSpaceFlipped;
    private ReducedDevelopmentCard[] personalDevelopmentBoard;
    private List<ReducedContainer> wareHouseDepot;
    private Map<ResourceType, Integer> strongBoxDepot;
    private Map<ResourceType, Integer> specialDepot;
    private Map<ResourceType, Integer> productionPowerInputBoard;
    private Map<ResourceType, Integer> productionPowerOutputBoard;
    private Map<ResourceType, Integer> temporaryDepot;
    private Integer playerTurnPosition;

    public ReducedGameModel() {
        marketTray = new ReducedMarketTray();
        inHandLeaderCards = new ReducedInHandLeaderCards();
        developmentCardsGrid = new ReducedDevelopmentCard[3][4];
        popeSpaceFlipped = new Boolean[]{false, false, false};
        personalDevelopmentBoard = new ReducedDevelopmentCard[3];
        wareHouseDepot = new ArrayList<>();
        wareHouseDepot.add(new ReducedContainer(null, 0));
        wareHouseDepot.add(new ReducedContainer(null, 0));
        wareHouseDepot.add(new ReducedContainer(null, 0));
        strongBoxDepot = new HashMap<>();
        strongBoxDepot.put(ResourceType.COIN, 0);
        strongBoxDepot.put(ResourceType.SERVANT, 0);
        strongBoxDepot.put(ResourceType.SHIELD, 0);
        strongBoxDepot.put(ResourceType.STONE, 0);
        specialDepot = new HashMap<>();
    }


    public Map<ResourceType, Integer> getTemporaryDepot() {
        return temporaryDepot;
    }

    public void setTemporaryDepot(Map<ResourceType, Integer> temporaryDepot) {
        this.temporaryDepot = temporaryDepot;
    }

    public Integer getPlayerTurnPosition() {
        return playerTurnPosition;
    }

    public void setPlayerTurnPosition(Integer playerTurnPosition) {
        this.playerTurnPosition = playerTurnPosition;
    }

    public ReducedInHandLeaderCards getReducedInHandLeaderCards() {
        return inHandLeaderCards;
    }

    public List<ReducedPlayer> getPlayers() {
        return players;
    }

    public ReducedDevelopmentCard[][] getDevelopmentCardsGrid() {
        return developmentCardsGrid;
    }

    public Boolean[] getPopeSpaceFlipped() {
        return popeSpaceFlipped;
    }

    public ReducedDevelopmentCard[] getPersonalDevelopmentBoard() {
        return personalDevelopmentBoard;
    }

    public List<ReducedContainer> getWareHouseDepot() {
        return wareHouseDepot;
    }

    public Map<ResourceType, Integer> getStrongBoxDepot() {
        return strongBoxDepot;
    }

    public Map<ResourceType, Integer> getSpecialDepot() {
        return specialDepot;
    }

    public Map<ResourceType, Integer> getProductionPowerInputBoard() {
        return productionPowerInputBoard;
    }

    public Map<ResourceType, Integer> getProductionPowerOutputBoard() {
        return productionPowerOutputBoard;
    }

    public ReducedMarketTray getMarketTray() {
        return marketTray;
    }


}
