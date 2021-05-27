package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.networking.connection.InGameConnectedClient;
import it.polimi.ingsw.networking.connection.ServerClientHandler;
import it.polimi.ingsw.networking.messages.clientMessages.ActivateProductionMessage;
import it.polimi.ingsw.networking.messages.clientMessages.BuyDevCardMessage;
import it.polimi.ingsw.networking.messages.clientMessages.ClientText;
import it.polimi.ingsw.networking.messages.clientMessages.TakeFromMarketMessage;
import it.polimi.ingsw.networking.messages.serverMessage.ServerText;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.controller.gameStates.GameState;
import it.polimi.ingsw.server.model.game.Game;
import it.polimi.ingsw.server.model.game.MultiplayerGame;
import it.polimi.ingsw.server.model.game.SinglePlayerGame;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.utils.exceptions.DepotException;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.io.IOException;
import java.util.*;

public class GameController{

    private final Integer gameID;
    private final Game gameModel;
    private final Integer maxPlayersNumber;
    private GameState currentGameState;
    private final List<InGameConnectedClient> inGameConnectedClients = new ArrayList<>();
    private RealPlayer currentPlayer;

    public GameController(Integer maxPlayersNumber, Integer gameID) {
        this.gameID = gameID;
        currentGameState = GameState.LOGIN;
        this.maxPlayersNumber = maxPlayersNumber;
        gameModel = maxPlayersNumber == 1? new SinglePlayerGame(): new MultiplayerGame(maxPlayersNumber);
        if(maxPlayersNumber == 1) currentGameState = GameState.INIT ;
    }

    //+++++++ Getter +++++++
    public Integer getGameID() {
        return gameID;
    }

    public Integer getMaxPlayersNumber() {
        return maxPlayersNumber;
    }

    public List<InGameConnectedClient> getInGameConnectedClients() {
        return inGameConnectedClients;
    }

    public Game getGameModel() {
        return gameModel;
    }
    //+++++++++++++++++++++


    public void addConnectedClient(InGameConnectedClient inGameConnectedClient){
        inGameConnectedClients.add(inGameConnectedClient);
        gameModel.addObserver(inGameConnectedClient);
        if(inGameConnectedClients.size() == maxPlayersNumber){
            currentGameState = GameState.INIT;
        }
    }

    public void removeConnectedClient(InGameConnectedClient inGameConnectedClient){
        gameModel.removeObserver(inGameConnectedClient);
        inGameConnectedClients.remove(inGameConnectedClient);
    }

    public void manageReceivedMessage(Message receivedMessage){

        switch (receivedMessage.getMessageType()){

            case TEXT:
                ClientText clientText = (ClientText) receivedMessage;
                System.out.println("Server (controller) received \"" + clientText.getText() + "\"");
                sendPrivateMessage(clientText.getNickname(), new ServerText(clientText.getText().toUpperCase(Locale.ROOT)));
                sendBroadCastMessage(new ServerText("il server ha inviato un messaggio"));
                break;
            case TAKE_FROM_MARKET:
                TakeFromMarketMessage takeFromMarketMessage = (TakeFromMarketMessage) receivedMessage;
                takeFromMarket(takeFromMarketMessage);
                break;
            case BUY_DEV_CARD:
                BuyDevCardMessage buyDevCardMessage = (BuyDevCardMessage) receivedMessage;
                buyDevCard(buyDevCardMessage);
                break;
            case ACTIVATE_PRODUCTION:
                ActivateProductionMessage activateProductionMessage = (ActivateProductionMessage) receivedMessage;
                activateProduction(activateProductionMessage);




        }

    }


    //****** MAIN ACTION ***********
    private void buyDevCard(BuyDevCardMessage buyDevCardMessage){
        int cardId = buyDevCardMessage.getCardID();
        DevelopmentCard developmentCard = null;

        for(int i = 0; i < gameModel.getGlobalBoard().getDevelopmentCardGrid().getDeckGrid().length; i++){
            for (int j = 0; j < gameModel.getGlobalBoard().getDevelopmentCardGrid().getDeckGrid()[0].length; j++){
                DevelopmentCard temp = gameModel.getGlobalBoard().getDevelopmentCardGrid().getDeckGrid()[i][j].pop();
                if (temp.getId()== cardId){
                    developmentCard = temp;
                    break;
                }
            }
        }

        if (developmentCard != null){
            if (developmentCard.getCost().check(currentPlayer)){
                try {
                    developmentCard.getCost().pay(currentPlayer);
                } catch (DepotException e) {
                    e.printStackTrace();
                }
                developmentCard.onTaking(currentPlayer);

            }
            //sendBroadCastMessage(new UpdateDevelopmentCardGridMessage());
            //sendPrivateMessage(buyDevCardMessage.getNickname(), new UpdatedPersonalDevelopmentBoard();
        }

    }

    private void takeFromMarket(TakeFromMarketMessage takeFromMarketMessage) {

        String rowOrColumn = takeFromMarketMessage.getRowOrColumn();
        Integer number = takeFromMarketMessage.getNumber();

        if(rowOrColumn.equals("row")){
            gameModel.getGlobalBoard().getMarketTray().selectRow(number).forEach(marble -> marble.onTaking(currentPlayer));
        }
        else if (rowOrColumn.equals("column")){
            gameModel.getGlobalBoard().getMarketTray().selectColumn(number).forEach(marble -> marble.onTaking(currentPlayer));
        }
        else System.out.println("Error");

        //sendBroadCastMessage(new UpdatedMarketTray(gameModel.getGlobalBoard().getMarketTray()));
    }

    private void activateProduction(ActivateProductionMessage activateProductionMessage){
        try {
            gameModel.getGlobalBoard().getBasicProductionPower().getProductionInput().pay(currentPlayer);
        } catch (DepotException e) {
            e.printStackTrace();
        }
        gameModel.getGlobalBoard().getBasicProductionPower().getProductionOutput().onActivation(currentPlayer);
    }
    //*******************


    public void moveFromMarket(ResourceType resourceType, Integer numberOfContainer, String depot) throws DepotException {
        RealPlayer realPlayer = (RealPlayer) gameModel.getCurrentPlayer();
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
        RealPlayer realPlayer = (RealPlayer) gameModel.getCurrentPlayer();
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
        return gameModel.getGlobalBoard().getDevelopmentCardGrid().getDeck(row, column).pop();
    }

    public LeaderCard selectLeaderCardByCardId(int id) {
        RealPlayer realPlayer = (RealPlayer) gameModel.getCurrentPlayer();
        for (LeaderCard leaderCard : realPlayer.getPersonalBoard().getInHandLeaderCards().getCards()) {
            if (leaderCard.getId() == id) return leaderCard;
        }
        return null;
    }

    public DevelopmentCard selectDevelopmentCardByCardId(int id) {
        RealPlayer realPlayer = (RealPlayer) gameModel.getCurrentPlayer();
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
        realPlayer = (RealPlayer) gameModel.getCurrentPlayer();
        try {
            realPlayer.getPersonalBoard().getWareHouseDepot().swap(number1, number2);
        } catch (DepotException e) {
            e.printStackTrace();
        }
    }


    public void playLeaderCard(Integer id) {
        RealPlayer realPlayer;
        realPlayer = (RealPlayer) gameModel.getCurrentPlayer();
        for (LeaderCard leaderCard : realPlayer.getPersonalBoard().getInHandLeaderCards().getCards()) {
            if (leaderCard.getId().equals(id)) {
                leaderCard.playCard(realPlayer);
            }
        }
    }

    public void discardLeaderCard(Integer id) {
        RealPlayer realPlayer;
        realPlayer = (RealPlayer) gameModel.getCurrentPlayer();
        for (LeaderCard leaderCard : realPlayer.getPersonalBoard().getInHandLeaderCards().getCards()) {
            if (leaderCard.getId().equals(id)) {
                realPlayer.getPersonalBoard().getInHandLeaderCards().discard(leaderCard, realPlayer);
            }
        }
    }




    public void sendBroadCastMessage(Message message){

        inGameConnectedClients.forEach(inGameConnectedClient -> {
            try {
                inGameConnectedClient.getConnectionIO().sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    /**
     * Sends a message to the single client.
     *
     * @param nickName The recipient of the message.
     * @param message The message to be sent.
     */
    public void sendPrivateMessage(String nickName, Message message){
        ServerClientHandler serverClientHandler = Server.getConnectedClient().stream().filter(x -> x.getNickName().equals(nickName)).findFirst().orElse(null);
        //ServerClientHandler serverClientHandler = Server.getNicknameWithConnection().entrySet().stream().filter(x-> x.getKey().equals(nickName)).map(Map.Entry::getValue).findFirst().orElse(null);
        assert serverClientHandler != null;
        serverClientHandler.sendMessage(message);
    }

}
