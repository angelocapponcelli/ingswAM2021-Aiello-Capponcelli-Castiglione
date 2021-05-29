package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.networking.connection.InGameConnectedClient;
import it.polimi.ingsw.networking.connection.ServerClientHandler;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.clientMessages.*;
import it.polimi.ingsw.networking.messages.serverMessage.RequestMessage;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedMarketTrayMessage;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.controller.gameStates.GameState;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.game.Game;
import it.polimi.ingsw.server.model.game.MultiplayerGame;
import it.polimi.ingsw.server.model.game.SinglePlayerGame;
import it.polimi.ingsw.server.model.player.Player;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;
import it.polimi.ingsw.utils.parsers.LeaderCardParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class GameController /*implements Runnable*/ {

    private final Integer gameID;
    private final Game gameModel;
    private final Integer maxPlayersNumber;
    private GameState currentGameState;
    private final List<InGameConnectedClient> inGameConnectedClients = new ArrayList<>();

    private final List<Player> playerList = new ArrayList<>();
    //private final List<RealPlayer> playerList = new ArrayList<>();
    private RealPlayer currentPlayer;
    //private final BlockingQueue<Message> messagesQueue = new LinkedBlockingQueue<>();

    public GameController(Integer maxPlayersNumber, Integer gameID) {
        this.gameID = gameID;
        currentGameState = GameState.LOGIN;
        this.maxPlayersNumber = maxPlayersNumber;
        gameModel = maxPlayersNumber == 1 ? new SinglePlayerGame() : new MultiplayerGame(maxPlayersNumber);
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

    public void addConnectedClient(InGameConnectedClient inGameConnectedClient) {
        inGameConnectedClients.add(inGameConnectedClient);
        RealPlayer realPlayer = new RealPlayer(inGameConnectedClient.getNickName(), this);
        realPlayer.addObserver(inGameConnectedClient);
        playerList.add(realPlayer);
        gameModel.addObserver(inGameConnectedClient);
        if (inGameConnectedClients.size() == maxPlayersNumber) {
            updateGameState(GameState.IN_GAME);
            initializeGame();
            sendBroadCastMessage(new RequestMessage(MessageType.DISCARD_LEADER_CARD_REQUEST));
            sendBroadCastMessage(new RequestMessage(MessageType.SELECT_INITIAL_RESOURCE_REQUEST));

        }
    }

    public void removeConnectedClient(InGameConnectedClient inGameConnectedClient) {
        gameModel.removeObserver(inGameConnectedClient);
        playerList.remove(playerList.stream().filter(x -> x.getNickName().equals(inGameConnectedClient.getNickName())).findFirst().orElse(null));
        inGameConnectedClients.remove(inGameConnectedClient);
    }


    private void initializeGame() {
        Collections.shuffle(playerList);
        IntStream.range(0, playerList.size()).forEach(i -> playerList.get(i).setTurnPosition(i + 1));

        sendBroadCastMessage(new UpdatedMarketTrayMessage(gameModel.getGlobalBoard().getMarketTray()));

        List<LeaderCard> leaderCardsDeck = LeaderCardParser.getLeaderCards();
        IntStream.range(0, playerList.size())
                .filter(i -> playerList.get(i) instanceof RealPlayer)
                .forEach(i -> {
                        RealPlayer realPlayer = (RealPlayer) playerList.get(i);
                        realPlayer
                                .getPersonalBoard()
                                .getInHandLeaderCards()
                                .addLeaderCard(leaderCardsDeck.subList(i * 4, i * 4 + 4));
        });

        //sendBroadCastMessage(new RequestMessage(MessageType.SELECT_INITIAL_RESOURCE_REQUEST));

    }


    private void discardInitialLeaderCards(DiscardedLeaderCardsMessage discardedLeaderCardsMessage) {
        playerList.stream()
                .filter(player -> player
                        .getNickName()
                        .equals(discardedLeaderCardsMessage.getNickname()))
                .filter(player -> player instanceof RealPlayer)
                .map(player -> (RealPlayer) player)
                .findFirst().orElse(null)
                .getPersonalBoard().getInHandLeaderCards().remove(discardedLeaderCardsMessage.getIDsToDiscard());
    }



    public void manageReceivedMessage(Message message) {
        switch (message.getMessageType()) {
            case TAKE_FROM_MARKET:
                TakeFromMarketMessage takeFromMarketMessage = (TakeFromMarketMessage) message;
                takeFromMarket(takeFromMarketMessage);
                break;
            case BUY_DEV_CARD:
                BuyDevCardMessage buyDevCardMessage = (BuyDevCardMessage) message;
                buyDevCard(buyDevCardMessage);
                break;
            case ACTIVATE_PRODUCTION:
                ActivateProductionMessage activateProductionMessage = (ActivateProductionMessage) message;
                activateProduction(activateProductionMessage);
                break;
            case DISCARDED_LEADER_CARD:
                DiscardedLeaderCardsMessage discardLeaderCardsMessage = (DiscardedLeaderCardsMessage) message;
                discardInitialLeaderCards(discardLeaderCardsMessage);
                //sendBroadCastMessage(new RequestMessage(MessageType.SELECT_INITIAL_RESOURCE_REQUEST));
                break;
            case CHOSEN_INITIAL_RESOURCES:
                ChosenInitialResourcesMessage chosenInitialResourcesMessage = (ChosenInitialResourcesMessage) message;
                distributeInitialResources(chosenInitialResourcesMessage);

        }
    }
    private void distributeInitialResources(ChosenInitialResourcesMessage chosenInitialResourcesMessage) {
        chosenInitialResourcesMessage.getChosenResource().forEach(resourceType -> ResourceType.getResourceClass(resourceType).onTaking((RealPlayer) playerList.stream()
                .filter(player -> player.getNickName().equals(chosenInitialResourcesMessage.getNickname()))
                .findFirst().orElse(null)));
    }

    //****** MAIN ACTION ***********
    private void buyDevCard(BuyDevCardMessage buyDevCardMessage) {
        int cardId = buyDevCardMessage.getCardID();
        DevelopmentCard developmentCard = null;

        for (int i = 0; i < gameModel.getGlobalBoard().getDevelopmentCardGrid().getDeckGrid().length; i++) {
            for (int j = 0; j < gameModel.getGlobalBoard().getDevelopmentCardGrid().getDeckGrid()[0].length; j++) {
                DevelopmentCard temp = gameModel.getGlobalBoard().getDevelopmentCardGrid().getDeckGrid()[i][j].pop();
                if (temp.getId() == cardId) {
                    developmentCard = temp;
                    break;
                }
            }
        }

        if (developmentCard != null) {
            if (developmentCard.getCost().check(currentPlayer)) {
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

        if (rowOrColumn.equals("row")) {
            gameModel.getGlobalBoard().getMarketTray().selectRow(number).forEach(marble -> marble.onTaking(currentPlayer));
        } else if (rowOrColumn.equals("column")) {
            gameModel.getGlobalBoard().getMarketTray().selectColumn(number).forEach(marble -> marble.onTaking(currentPlayer));
        } else System.out.println("Error");

        //sendBroadCastMessage(new UpdatedMarketTray(gameModel.getGlobalBoard().getMarketTray()));
    }

    private void activateProduction(ActivateProductionMessage activateProductionMessage) {
        try {
            gameModel.getGlobalBoard().getBasicProductionPower().getProductionInput().pay(currentPlayer);
        } catch (DepotException e) {
            e.printStackTrace();
        }
        gameModel.getGlobalBoard().getBasicProductionPower().getProductionOutput().onActivation(currentPlayer);
    }
    //*******************




    private void updateGameState(GameState nextState) {
        currentGameState = nextState;
    }
    public void sendBroadCastMessage(Message message) {

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
     * @param message  The message to be sent.
     */
    public void sendPrivateMessage(String nickName, Message message) {
        ServerClientHandler serverClientHandler = Server.getConnectedClient().stream().filter(x -> x.getNickName().equals(nickName)).findFirst().orElse(null);
        //ServerClientHandler serverClientHandler = Server.getNicknameWithConnection().entrySet().stream().filter(x-> x.getKey().equals(nickName)).map(Map.Entry::getValue).findFirst().orElse(null);
        assert serverClientHandler != null;
        serverClientHandler.sendMessage(message);
    }

}
