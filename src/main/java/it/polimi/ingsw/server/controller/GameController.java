package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.networking.connection.ServerConnectionHandler;
import it.polimi.ingsw.server.controller.gameStates.GameState;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.exceptions.DepotException;
import it.polimi.ingsw.server.model.interfaces.Takeable;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private Integer gameID;
    private Game game;
    private Integer maxPlayersNumber;
    private GameState currentGameState;
    private final List<Client> clientList = new ArrayList<>();
    private final List<ServerConnectionHandler> connectionHandlers = new ArrayList<>();

    public GameController() {
        currentGameState = GameState.LOGIN;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setMaxPlayersNumber(int maxPlayersNumber) {
        this.maxPlayersNumber = maxPlayersNumber;
    }

    public void addClient(Client client) {
        clientList.add(client);
    }

    public Game getGame() {
        return game;
    }

    public void insertNickname(String nickname) throws Exception {
        game.addPlayer(new RealPlayer(nickname));
    }

    public void moveFromMarket(ResourceType resourceType, Integer numberOfContainer, String depot) throws DepotException {
        RealPlayer realPlayer = (RealPlayer) game.getCurrentPlayer();
        try {
            if (depot.equals("Warehouse")) {
                realPlayer.getPersonalBoard().getWareHouseDepot().addResource(resourceType, 1, numberOfContainer);
            }
            if (depot.equals("Special")) {
                realPlayer.getPersonalBoard().getSpecialDepots().addResources(resourceType, 1);
            }
        } catch (DepotException e) {
            throw e;
        }
        try {
            realPlayer.getPersonalBoard().getTemporaryDepotForMarket().removeResources(resourceType, 1);
        } catch (DepotException e) {
            //before throw exception reset depots
            if (depot.equals("Warehouse")) {
                realPlayer.getPersonalBoard().getWareHouseDepot().removeResources(resourceType, 1);
            }
            if (depot.equals("Special")) {
                realPlayer.getPersonalBoard().getSpecialDepots().removeResources(resourceType, 1);
            }
            throw e;
        }

    }

    public void reallocateResourceMessage(ResourceType resourceType, Integer numberOfContainer, String depotSource, String depotDestination) throws DepotException {
        RealPlayer realPlayer = (RealPlayer) game.getCurrentPlayer();
        if (depotSource.equals("Warehouse")) {
            int resourceCount = realPlayer.getPersonalBoard().getWareHouseDepot().getSpecificResourceCount(resourceType);
            if (resourceCount != 0) {
                realPlayer.getPersonalBoard().getSpecialDepots().addResources(resourceType, resourceCount);
                realPlayer.getPersonalBoard().getWareHouseDepot().removeResources(resourceType, resourceCount);
            }
        } else if (depotSource.equals("Special")) {
            int resourceCount = realPlayer.getPersonalBoard().getSpecialDepots().getSpecificResourceCount(resourceType);
            if (resourceCount != 0) {
                realPlayer.getPersonalBoard().getWareHouseDepot().addResource(resourceType, resourceCount, numberOfContainer);
                realPlayer.getPersonalBoard().getSpecialDepots().removeResources(resourceType, resourceCount);
            }
        }
    }

    public DevelopmentCard selectDeckFromGrid(int row, int column) {
        return game.getGlobalBoard().getDevelopmentCardGrid().getDeck(row, column).getTopCard();
    }

    public LeaderCard selectLeaderCardByCardId(int id) {
        RealPlayer realPlayer = (RealPlayer) game.getCurrentPlayer();
        for (LeaderCard leaderCard : realPlayer.getPersonalBoard().getInHandLeaderCards().getCards()) {
            if (leaderCard.getId() == id) return leaderCard;
        }
        return null;
    }

    public DevelopmentCard selectDevelopmentCardByCardId(int id) {
        RealPlayer realPlayer = (RealPlayer) game.getCurrentPlayer();
        for (DevelopmentCard developmentCard : realPlayer.getPersonalBoard().getPersonalDevelopmentBoard().getALlCards()) {
            if (developmentCard.getId() == id) return developmentCard;
        }
        return null;
    }

    private void updateGameState(GameState nextState) {
        currentGameState = nextState;
    }

    public void swapShelves(Integer number1, Integer number2) {
        RealPlayer realPlayer;
        realPlayer = (RealPlayer) game.getCurrentPlayer();
        try {
            realPlayer.getPersonalBoard().getWareHouseDepot().swap(number1, number2);
        } catch (DepotException e) {
            e.printStackTrace();
        }
    }

    public List<Takeable> takeFromMarket(String rOrC, Integer number) {
        List<Takeable> tmpDepotForMarket = new ArrayList<>();
        if (rOrC.equals("row")) {
            tmpDepotForMarket = game.getGlobalBoard().getMarketTray().selectRow(number);
            return tmpDepotForMarket;
        } else {
            game.getGlobalBoard().getMarketTray().selectColumn(number);
            return tmpDepotForMarket;
        }
    }

    public void playLeaderCard(Integer id) {
        RealPlayer realPlayer;
        realPlayer = (RealPlayer) game.getCurrentPlayer();
        for (LeaderCard leaderCard : realPlayer.getPersonalBoard().getInHandLeaderCards().getCards()) {
            if (leaderCard.getId().equals(id)) {
                leaderCard.playCard(realPlayer);
            }
        }
    }

    public void discardLeaderCard(Integer id) {
        RealPlayer realPlayer;
        realPlayer = (RealPlayer) game.getCurrentPlayer();
        for (LeaderCard leaderCard : realPlayer.getPersonalBoard().getInHandLeaderCards().getCards()) {
            if (leaderCard.getId().equals(id)) {
                realPlayer.getPersonalBoard().getInHandLeaderCards().discard(leaderCard, realPlayer);
            }
        }
    }


}
