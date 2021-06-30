package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.client.view.reducedGameModel.reducedFaithTrack.ReducedFaithTrack;
import it.polimi.ingsw.client.view.reducedGameModel.reducedFaithTrack.ReducedVaticanReportSection;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.*;

public class ReducedGameModel {
    private final ReducedMarketTray marketTray;
    private final ReducedInHandLeaderCards inHandLeaderCards;
    private List<ReducedPlayer> players;
    private ReducedDevelopmentCard[][] developmentCardsGrid;
    private List<ReducedDevelopmentCard> personalDevelopmentBoard;
    private Map<ResourceType, Integer> temporaryDepot;
    private List<ReducedContainer> wareHouseDepot;
    private List<ReducedContainer> strongBoxDepot;
    private Map<ResourceType, Integer> specialDepot;
    private final ReducedProductionPower productionPower = new ReducedProductionPower();
    private Integer playerTurnPosition;
    private Map<SpecialAbilityType, ResourceType> activatedSpecialAbilities;
    private final ReducedFaithTrack faithTrack;
    private List<ReducedVaticanReportSection> flippedVaticanReportSections = new ArrayList<>();
    private final List<Boolean> flipped = Arrays.asList(false,false,false);




    public ReducedGameModel() {
        marketTray = new ReducedMarketTray();
        inHandLeaderCards = new ReducedInHandLeaderCards();
        developmentCardsGrid = new ReducedDevelopmentCard[3][4];
        personalDevelopmentBoard = Arrays.asList(null,null,null);
        faithTrack = new ReducedFaithTrack();
        wareHouseDepot = new ArrayList<>();
        temporaryDepot = new HashMap<>();
        wareHouseDepot = Arrays.asList(new ReducedContainer(null, 0),
                new ReducedContainer(null, 0),
                new ReducedContainer(null, 0)
        );
        strongBoxDepot = Arrays.asList(
                new ReducedContainer(ResourceType.COIN, 0),
                new ReducedContainer(ResourceType.STONE, 0),
                new ReducedContainer(ResourceType.SHIELD, 0),
                new ReducedContainer(ResourceType.SERVANT, 0)
        );
        specialDepot = new HashMap<>();
        activatedSpecialAbilities = new HashMap<>();
    }

    public Map<SpecialAbilityType, ResourceType> getActivatedSpecialAbilities() {
        return activatedSpecialAbilities;
    }
    public void setActivatedSpecialAbilities(Map<SpecialAbilityType, ResourceType> activatedSpecialAbilities) {
        this.activatedSpecialAbilities = activatedSpecialAbilities;
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

    public void setPlayers(List<ReducedPlayer> players) {
        this.players = players;
    }

    public ReducedDevelopmentCard[][] getDevelopmentCardsGrid() {
        return developmentCardsGrid;
    }
    public void setDevelopmentCardsGrid(ReducedDevelopmentCard[][] developmentCardsGrid) {
        this.developmentCardsGrid = developmentCardsGrid;
    }


    public List<ReducedDevelopmentCard> getPersonalDevelopmentBoard() {
        return personalDevelopmentBoard;
    }
    public void setPersonalDevelopmentBoard(List<ReducedDevelopmentCard> personalDevelopmentBoard) {
        this.personalDevelopmentBoard = personalDevelopmentBoard;
    }

    public List<ReducedContainer> getWareHouseDepot() {
        return wareHouseDepot;
    }
    public void setWareHouseDepot(List<ReducedContainer> reducedWareHouse){
        wareHouseDepot = reducedWareHouse;
    }

    public List<ReducedContainer> getStrongBoxDepot() {
        return strongBoxDepot;
    }
    public void setStrongBoxDepot(List<ReducedContainer> strongBoxDepot) {
        this.strongBoxDepot = strongBoxDepot;
    }

    public Map<ResourceType, Integer> getSpecialDepot() {
        return specialDepot;
    }
    public void setSpecialDepot(Map<ResourceType, Integer> specialDepot) {
        this.specialDepot = specialDepot;
    }

    public Map<ResourceType, Integer> getProductionPowerInputBoard() {
        return productionPower.getProductionPowerInput();
    }
    public void setProductionPowerInputBoard(Map<ResourceType, Integer> productionPowerInput) {
        this.productionPower.setProductionPowerInput(productionPowerInput);
    }

    public Map<ResourceType, Integer> getProductionPowerOutputBoard() {
        return productionPower.getProductionPowerOutput();
    }
    public void setProductionPowerOutputBoard(Map<ResourceType, Integer> productionPowerOutput) {
        this.productionPower.setProductionPowerOutput(productionPowerOutput);
    }


    public ReducedMarketTray getMarketTray() {
        return marketTray;
    }


    public List<ReducedVaticanReportSection> getFlippedVaticanReportSections() {
        return flippedVaticanReportSections;
    }
    public void setFlippedVaticanReportSections(List<ReducedVaticanReportSection> flippedVaticanReportSections) {
        this.flippedVaticanReportSections = flippedVaticanReportSections;
    }


    public ReducedFaithTrack getFaithTrack() {
        return faithTrack;
    }
}
