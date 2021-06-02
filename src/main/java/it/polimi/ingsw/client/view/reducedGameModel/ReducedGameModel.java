package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.*;
import java.util.stream.Stream;

public class ReducedGameModel {
    private final ReducedMarketTray marketTray;
    private final ReducedInHandLeaderCards inHandLeaderCards;
    private  List<ReducedPlayer> players;
    private ReducedDevelopmentCard[][] developmentCardsGrid;
    private final Boolean[] popeSpaceFlipped;
    private final ReducedDevelopmentCard[] personalDevelopmentBoard;
    private  List<ReducedContainer> wareHouseDepot;
    private final Map<ResourceType, Integer> strongBoxDepot;
    private final Map<ResourceType, Integer> specialDepot;
    private Map<ResourceType, Integer> productionPowerInputBoard;
    private Map<ResourceType, Integer> productionPowerOutputBoard;
    private Map<ResourceType, Integer> temporaryDepot;
    private Integer playerTurnPosition;
    private final Map<SpecialAbilityType, ResourceType> activatedSpecialAbilities;

    public ReducedGameModel() {
        marketTray = new ReducedMarketTray();
        inHandLeaderCards = new ReducedInHandLeaderCards();
        developmentCardsGrid = new ReducedDevelopmentCard[3][4];
        popeSpaceFlipped = new Boolean[]{false, false, false};
        personalDevelopmentBoard = new ReducedDevelopmentCard[3];
        wareHouseDepot = new ArrayList<>();
        temporaryDepot = new HashMap<>();

        wareHouseDepot.add(new ReducedContainer(null, 0));
        wareHouseDepot.add(new ReducedContainer(null, 0));
        wareHouseDepot.add(new ReducedContainer(null, 0));
        strongBoxDepot = Map.of(ResourceType.COIN,0,ResourceType.SHIELD,0,ResourceType.STONE,0,ResourceType.SERVANT,0);
        specialDepot = new HashMap<>();
        activatedSpecialAbilities = new HashMap<>();
    }


    public Map<SpecialAbilityType, ResourceType> getActivatedSpecialAbilities() {
        return activatedSpecialAbilities;
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

    public Map<Colors, Integer> getPlayers() {
        Map<Colors, Integer> listPlayers = new HashMap<>();
        for (ReducedPlayer reducedPlayer : players) {
            listPlayers.put(reducedPlayer.getPlayerColor(), reducedPlayer.getFaithPosition());
        }
        return listPlayers;
    }

    public ReducedDevelopmentCard[][] getDevelopmentCardsGrid() {
        return developmentCardsGrid;
    }

    public void setDevelopmentCardsGrid(ReducedDevelopmentCard[][] developmentCardsGrid) {
        this.developmentCardsGrid = developmentCardsGrid;
    }

    public Boolean[] getPopeSpaceFlipped() {
        return popeSpaceFlipped;
    }

    public ReducedDevelopmentCard[] getPersonalDevelopmentBoard() {
        return personalDevelopmentBoard;
    }

    public void setWareHouseDepot(List<ReducedContainer> reducedWareHouse){
        wareHouseDepot = reducedWareHouse;
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
