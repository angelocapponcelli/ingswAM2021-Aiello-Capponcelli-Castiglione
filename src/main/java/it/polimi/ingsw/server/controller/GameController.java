package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedPlayer;
import it.polimi.ingsw.networking.connection.InGameConnectedClient;
import it.polimi.ingsw.networking.connection.ServerToClientHandler;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.clientMessages.*;
import it.polimi.ingsw.networking.messages.serverMessage.ActionEndedMessage;
import it.polimi.ingsw.networking.messages.serverMessage.GamePhaseUpdateMessage;
import it.polimi.ingsw.networking.messages.serverMessage.ItIsMyTurnMessage;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.InitViewMessage;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.controller.gameStates.GameState;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.game.Game;
import it.polimi.ingsw.server.model.game.MultiplayerGame;
import it.polimi.ingsw.server.model.game.SinglePlayerGame;
import it.polimi.ingsw.server.model.personalBoard.PersonalBoard;
import it.polimi.ingsw.server.model.player.Lorenzo;
import it.polimi.ingsw.server.model.player.Player;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;
import it.polimi.ingsw.utils.parsers.SettingsParser;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Game controller
 */
public class GameController {

    private final Integer gameID;
    private final Game gameModel;
    private final Integer maxPlayersNumber;
    private final List<InGameConnectedClient> inGameConnectedClients = new ArrayList<>();
    private final List<Player> playerList = new ArrayList<>();
    private GameState currentGameState = GameState.IN_GAME;
    private int currentPlayer = 0;

    /**
     * Class constructor
     *
     * @param playersNumber the number of players that can play the game
     * @param gameID        the unique value that identifies the game
     */
    public GameController(Integer playersNumber, Integer gameID) {
        this.gameID = gameID;
        currentGameState = GameState.LOGIN;
        this.maxPlayersNumber = playersNumber;

        if (playersNumber == 1) {
            gameModel = new SinglePlayerGame();
            playerList.add(new Lorenzo(this));

        } else gameModel = new MultiplayerGame(playersNumber);
        gameModel.addGameController(this);

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

    public List<Player> getPlayerList() {
        return playerList;
    }

    //+++++++++++++++++++++

    /**
     * Adds a player to the game
     *
     * @param inGameConnectedClient
     */
    public void addConnectedClient(InGameConnectedClient inGameConnectedClient) {
        inGameConnectedClients.add(inGameConnectedClient);
        RealPlayer realPlayer = new RealPlayer(inGameConnectedClient.getNickName(), this);
        realPlayer.addObserver(inGameConnectedClient);
        playerList.add(realPlayer);
        gameModel.addObserver(inGameConnectedClient);
        if ((gameModel instanceof MultiplayerGame && inGameConnectedClients.size() == maxPlayersNumber) || (gameModel instanceof SinglePlayerGame && playerList.size() == 2)) {
            updateGameState(GameState.IN_GAME);
            initializeGame();
            sendBroadCastMessage(new GamePhaseUpdateMessage(MessageType.ALL_PLAYERS_JOINED));

        }
    }

    /**
     * Removes a player
     *
     * @param inGameConnectedClient
     */
    public void removeConnectedClient(InGameConnectedClient inGameConnectedClient) {
        gameModel.removeObserver(inGameConnectedClient);
        playerList.remove(playerList.stream().filter(x -> x.getNickName().equals(inGameConnectedClient.getNickName())).findFirst().orElse(null));
        inGameConnectedClients.remove(inGameConnectedClient);
    }

    /**
     * Initializates the game model so that the game can start
     */
    private void initializeGame() {

        Collections.shuffle(playerList);
        if (playerList.get(0).getNickName().equals("Lorenzo")) Collections.swap(playerList, 0, 1);
        IntStream.range(0, playerList.size()).forEach(i -> playerList.get(i).setTurnPosition(i + 1));
        if (playerList.size() > 2) {
            playerList.get(2).setFaithPosition(1);
            if (playerList.size() == 4) {
                playerList.get(3).setFaithPosition(1);
            }
        }

        List<ReducedPlayer> reducedPlayers = new ArrayList<>();
        for (Player player : playerList) {
            reducedPlayers.add(new ReducedPlayer(player));
        }

        sendBroadCastMessage(new InitViewMessage(
                        reducedPlayers,
                        gameModel.getGlobalBoard().getMarketTray(),
                        gameModel.getGlobalBoard().getDevelopmentCardGrid().toReduced(),
                        gameModel.getGlobalBoard().getBasicProductionPower().toReduced(),
                        gameModel.getGlobalBoard().getFaithTrack().toReduced()
                )
        );


        List<LeaderCard> leaderCardsDeck = SettingsParser.getInstance().getLeaderCards();
        Collections.shuffle(leaderCardsDeck);
        IntStream.range(0, playerList.size())
                .filter(i -> playerList.get(i) instanceof RealPlayer)
                .forEach(i -> {
                    RealPlayer realPlayer = (RealPlayer) playerList.get(i);
                    realPlayer
                            .getPersonalBoard()
                            .getInHandLeaderCards()
                            .addLeaderCard(leaderCardsDeck.subList(i * 4, i * 4 + 4));
                });


    }

    /**
     * Manages the incoming messages from the client
     *
     * @param message the message sent
     */
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
            case ACTIVATE_BASIC_PRODUCTION:
                ActivateBasicProductionMessage activateBasicProductionMessage = (ActivateBasicProductionMessage) message;
                activateBasicProduction(activateBasicProductionMessage);
                break;
            case DISCARDED_LEADER_CARD:
                DiscardedLeaderCardsMessage discardLeaderCardsMessage = (DiscardedLeaderCardsMessage) message;
                discardInitialLeaderCards(discardLeaderCardsMessage);
                break;
            case CHOSEN_INITIAL_RESOURCES:
                ChosenInitialResourcesMessage chosenInitialResourcesMessage = (ChosenInitialResourcesMessage) message;
                distributeInitialResources(chosenInitialResourcesMessage);
                break;
            case REALLOCATE_RESOURCE:
                ReallocateResourceMessage reallocateResourceMessage = (ReallocateResourceMessage) message;
                reallocateResource(reallocateResourceMessage);
                break;
            case SELECT_RESOURCE_REPLACEMENT:
                SelectResourceReplacementMessage selectResourceReplacementMessage = (SelectResourceReplacementMessage) message;
                selectResourceReplacement(selectResourceReplacementMessage);
                break;
            case DISCARD_RESOURCE:
                DiscardResourceMessage discardResourceMessage = (DiscardResourceMessage) message;
                discardResource(discardResourceMessage);
                break;
            case ACTIVATE_DEV_CARD_PRODUCTION:
                ActivateDevelopmentCardProductionMessage activateDevelopmentCardProductionMessage = (ActivateDevelopmentCardProductionMessage) message;
                activateDevelopmentCardProductionMessage(activateDevelopmentCardProductionMessage);
            case ACTIVATE_LEADER_CARD:
                ActivateLeaderCardMessage activateLeaderCardMessage = (ActivateLeaderCardMessage) message;
                activateLeaderCard(activateLeaderCardMessage);
                break;
            case ACTIVATE_PRODUCTION:
                ActivateProductionMessage activateProductionMessage = (ActivateProductionMessage) message;
                activateProduction(activateProductionMessage);

        }
    }

    /**
     * Activates and plays the leader card selected
     *
     * @param activateLeaderCardMessage the message where it is specified which leader card to play
     */
    private void activateLeaderCard(ActivateLeaderCardMessage activateLeaderCardMessage) {
        RealPlayer realPlayer = (RealPlayer) playerList.stream().filter(player -> player.getNickName().equals(activateLeaderCardMessage.getNickname()))
                .findFirst().orElse(null);
        realPlayer.getPersonalBoard().getInHandLeaderCards().playCard(activateLeaderCardMessage.getId(), realPlayer);
        sendPrivateMessage(activateLeaderCardMessage.getNickname(), new ActionEndedMessage());
    }

    /**
     * Activates the production from the development card selected
     *
     * @param activateDevelopmentCardProductionMessage the message where it is specified which card to use to activate the production
     */
    private void activateDevelopmentCardProductionMessage(ActivateDevelopmentCardProductionMessage activateDevelopmentCardProductionMessage) {
        DevelopmentCard card = getRealPlayer(activateDevelopmentCardProductionMessage)
                .getPersonalBoard()
                .getPersonalDevelopmentBoard()
                .getTopCard(activateDevelopmentCardProductionMessage.getDevelopmentCardID());

        try {
            card.getProductionPower().getProductionInput().pay(getRealPlayer(activateDevelopmentCardProductionMessage));
        } catch (DepotException e) {
            e.printStackTrace();
        }
        card.getProductionPower().getProductionOutput().onActivation(getRealPlayer(activateDevelopmentCardProductionMessage));
        //sendPrivateMessage(activateDevelopmentCardProductionMessage.getNickname(), new ActionEndedMessage());
        if (!activateDevelopmentCardProductionMessage.isBaseProductionActivated()) nextPlayerTurn();
    }

    /**
     * Discards the leader card selected
     *
     * @param discardedLeaderCardsMessage the message where it is specified which leader card to discard
     */
    private void discardInitialLeaderCards(DiscardedLeaderCardsMessage discardedLeaderCardsMessage) {
        Objects.requireNonNull(playerList.stream()
                .filter(player -> player
                        .getNickName()
                        .equals(discardedLeaderCardsMessage.getNickname()))
                .filter(player -> player instanceof RealPlayer)
                .map(player -> (RealPlayer) player)
                .findFirst().orElse(null))
                .getPersonalBoard().getInHandLeaderCards().remove(discardedLeaderCardsMessage.getIDsToDiscard());

        sendPrivateMessage(discardedLeaderCardsMessage.getNickname(), new ActionEndedMessage());
    }

    /**
     * Selects and replaces the resource
     *
     * @param selectResourceReplacementMessage the message where it is specified to which resource change it
     */
    private void selectResourceReplacement(SelectResourceReplacementMessage selectResourceReplacementMessage) {
        try {
            ((RealPlayer) Objects.requireNonNull(playerList.stream().filter(player -> player.getNickName().equals(selectResourceReplacementMessage.getNickname()))
                    .findFirst().orElse(null))).getPersonalBoard().getTemporaryDepot().removeResources(ResourceType.ANY, 1);
        } catch (DepotException e) {
            e.printStackTrace();
        }
        ((RealPlayer) Objects.requireNonNull(playerList.stream().filter(player -> player.getNickName().equals(selectResourceReplacementMessage.getNickname()))
                .findFirst().orElse(null))).getPersonalBoard().getTemporaryDepot().addResource(Collections.singletonList(selectResourceReplacementMessage.getResourceType()));

    }

    /**
     * Reallocates the resources selected in a different shelf or depot
     *
     * @param reallocateResourceMessage the message where it is specified where to take the resource and where to put it
     */
    private void reallocateResource(ReallocateResourceMessage reallocateResourceMessage) {
        RealPlayer realPlayer = (RealPlayer) playerList.stream().filter(player -> player.getNickName().equals(reallocateResourceMessage.getNickname()))
                .findFirst().orElse(null);
        PersonalBoard personalBoard = Objects.requireNonNull(realPlayer).getPersonalBoard();
        ResourceType resourceType = reallocateResourceMessage.getResourceType();
        switch (reallocateResourceMessage.getDestinationDepot()) {
            case "WareHouse":
                try {
                    personalBoard.getWareHouseDepot().addResource(resourceType, 1, reallocateResourceMessage.getDestinationShelf());
                } catch (DepotException e) {
                    e.printStackTrace();
                }
                break;
            case "Special":
                try {
                    personalBoard.getSpecialDepots().addResources(resourceType, 1);
                } catch (DepotException e) {
                    e.printStackTrace();
                }
                break;
        }
        try {
            personalBoard.getTemporaryDepot().removeResources(resourceType, 1);
        } catch (DepotException e) {
            e.printStackTrace();
        }

        if (personalBoard.getTemporaryDepot().getAllResourceCountNoAny() == 0)
            sendPrivateMessage(reallocateResourceMessage.getNickname(), new ActionEndedMessage());
    }

    /**
     * Discards the resource selected and gives other player a faith resource which means they can advance of one
     * position in the faith track
     *
     * @param discardResourceMessage the message where it is specified which resource to discard
     */
    private void discardResource(DiscardResourceMessage discardResourceMessage) {
        RealPlayer realPlayer = (RealPlayer) playerList.stream().filter(player -> player.getNickName().equals(discardResourceMessage.getNickname()))
                .findFirst().orElse(null);
        PersonalBoard personalBoard = Objects.requireNonNull(realPlayer).getPersonalBoard();
        ResourceType resourceType = discardResourceMessage.getResourceType();

        try {
            personalBoard.getTemporaryDepot().removeResources(resourceType, 1);
        } catch (DepotException e) {
            e.printStackTrace();
        }

        playerList.stream().filter(player -> !player.getNickName().equals(discardResourceMessage.getNickname())).forEach(Player::increaseFaithPosition);

        if (personalBoard.getTemporaryDepot().getAllResourceCountNoAny() == 0)
            sendPrivateMessage(discardResourceMessage.getNickname(), new ActionEndedMessage());
    }

    /**
     * Gives the player the resources he selected
     *
     * @param chosenInitialResourcesMessage the message where it is specified the selected resource
     */
    private void distributeInitialResources(ChosenInitialResourcesMessage chosenInitialResourcesMessage) {

        RealPlayer realPlayer = (RealPlayer) playerList.stream().filter(player -> player.getNickName().equals(chosenInitialResourcesMessage.getNickname())).findFirst().orElse(null);
        Objects.requireNonNull(realPlayer).getPersonalBoard().getTemporaryDepot().addResource(chosenInitialResourcesMessage.getChosenResource());
        sendPrivateMessage(chosenInitialResourcesMessage.getNickname(), new ActionEndedMessage());


    }

    //****** MAIN ACTION ***********

    /**
     * Buys the card from the grid
     *
     * @param buyDevCardMessage the message where it is specified which card to take from the grid
     */
    private void buyDevCard(BuyDevCardMessage buyDevCardMessage) {
        int cardId = buyDevCardMessage.getCardID();
        DevelopmentCard developmentCard;
        RealPlayer realPlayer = getRealPlayer(buyDevCardMessage);

        for (int i = 0; i < gameModel.getGlobalBoard().getDevelopmentCardGrid().getDeckGrid().length; i++) {
            for (int j = 0; j < gameModel.getGlobalBoard().getDevelopmentCardGrid().getDeckGrid()[0].length; j++) {
                DevelopmentCard temp = gameModel.getGlobalBoard().getDevelopmentCardGrid().getDeckGrid()[i][j].peek();
                if (temp.getId() == cardId) {
                    developmentCard = temp;
                    if (developmentCard.getCost().check(realPlayer)) {
                        try {
                            gameModel.getGlobalBoard().getDevelopmentCardGrid().pop(i, j);
                            developmentCard.getCost().pay(realPlayer);
                        } catch (DepotException e) {
                            e.printStackTrace();
                        }

                        developmentCard.onTaking(realPlayer);
                    }
                    break;
                }
            }
        }

        sendPrivateMessage(buyDevCardMessage.getNickname(), new ActionEndedMessage());
        nextPlayerTurn();

    }

    /**
     * Takes the resources from the market
     *
     * @param takeFromMarketMessage the message that specifies which rowor column to take from the market
     */
    private void takeFromMarket(TakeFromMarketMessage takeFromMarketMessage) {

        String rowOrColumn = takeFromMarketMessage.getRowOrColumn();
        Integer number = takeFromMarketMessage.getNumber();
        RealPlayer realPlayer = getRealPlayer(takeFromMarketMessage);
        assert realPlayer != null;
        realPlayer.getPersonalBoard().getTemporaryDepot().clear();

        if (rowOrColumn.equals("row")) {
            gameModel.getGlobalBoard().getMarketTray().selectRow(number).forEach(marble -> marble.onTaking(realPlayer));
        } else if (rowOrColumn.equals("column")) {
            gameModel.getGlobalBoard().getMarketTray().selectColumn(number).forEach(marble -> marble.onTaking(realPlayer));
        } else System.out.println("Error");
        sendPrivateMessage(takeFromMarketMessage.getNickname(), new ActionEndedMessage());
        //if (currentGameState != GameState.END) nextPlayerTurn();
        if(currentGameState == GameState.END && currentPlayer==playerList.size()-1){
            System.out.println("a fessa");
        }
        else nextPlayerTurn();
    }

    private void activateProduction(ActivateProductionMessage activateProductionMessage) {
        RealPlayer realPlayer = getRealPlayer(activateProductionMessage);
        try {
            gameModel.getGlobalBoard().getBasicProductionPower().getProductionInput().pay(realPlayer);
        } catch (DepotException e) {
            e.printStackTrace();
        }
        gameModel.getGlobalBoard().getBasicProductionPower().getProductionOutput().onActivation(realPlayer);
        //nextPlayerTurn();
    }

    /**
     * Activates the production using the basic production power
     *
     * @param activateBasicProductionMessage the message that specifies the resources in input and the ones in output
     */
    private void activateBasicProduction(ActivateBasicProductionMessage activateBasicProductionMessage) {
        if (activateBasicProductionMessage.getInput() != null) {
            RealPlayer realPlayer = getRealPlayer(activateBasicProductionMessage);

            ProductionPowerInput input = new ProductionPowerInput(activateBasicProductionMessage.getInput());
            ProductionPowerOutput output = new ProductionPowerOutput(activateBasicProductionMessage.getOutput());

            try {
                input.pay(realPlayer);
            } catch (DepotException e) {
                e.printStackTrace();
            }
            output.onActivation(realPlayer);
        }
        sendPrivateMessage(activateBasicProductionMessage.getNickname(), new ActionEndedMessage());
        nextPlayerTurn();

    }
    //*******************

    /**
     * Updates the state of the game
     *
     * @param nextState the next state in which the game will be
     */
    private void updateGameState(GameState nextState) {
        currentGameState = nextState;
    }

    /**
     * Sends a message to all the players
     *
     * @param message the message to send
     */
    public void sendBroadCastMessage(Message message) {

        inGameConnectedClients.forEach(inGameConnectedClient -> {
                inGameConnectedClient.getConnectionIO().sendMessage(message);
        });
    }

    /**
     * Sends a message to the single client.
     *
     * @param nickName The recipient of the message.
     * @param message  The message to be sent.
     */
    public void sendPrivateMessage(String nickName, Message message) {
        ServerToClientHandler serverToClientHandler = Server.getConnectedClient().stream().filter(x -> x.getNickName().equals(nickName)).findFirst().orElse(null);
        assert serverToClientHandler != null;
        serverToClientHandler.sendMessage(message);
    }


    private void nextPlayerTurn() {
        if (gameModel instanceof MultiplayerGame) {
            if (currentPlayer + 1 == playerList.size()) {
                currentPlayer = 0;
            } else currentPlayer++;
        } else lorenzoTurn();

        Boolean playLeaderCard = false;
        RealPlayer realPlayer = (RealPlayer) playerList.stream().filter(player -> player.getNickName().equals(playerList.get(currentPlayer).getNickName()))
                .findFirst().orElse(null);
        for (LeaderCard leaderCard : realPlayer.getPersonalBoard().getInHandLeaderCards().getInHandLeaderCards()) {
            if (!leaderCard.isPlayed() && leaderCard.getRequirements().check(realPlayer)) {
                playLeaderCard = true;
                break;
            }
        }
        sendPrivateMessage(playerList.get(currentPlayer).getNickName(), new ItIsMyTurnMessage(playLeaderCard));
    }


    /**
     * Gets the real player from the nickname contained in the message
     *
     * @param message the message that specifies the nickname
     * @return the real player which has the same nickname as the one contained in the message
     */
    private RealPlayer getRealPlayer(ClientMessage message) {

        return (RealPlayer) playerList.stream()
                .filter(player -> player.getNickName().equals(message.getNickname()))
                .findFirst().orElse(null);
    }

    /**
     * Performs lorenzo's turn
     */
    private void lorenzoTurn() {
        playerList.stream()
                .filter(player -> player.getNickName().equals("Lorenzo"))
                .findFirst().
                map(x -> (Lorenzo) x)
                .get()
                .reveal();


    }


    public void getWinner(){
        playerList.stream().map(player -> (RealPlayer) player).forEach(RealPlayer::calculateVictoryPoint);
        RealPlayer winner = playerList.stream().map(player -> (RealPlayer) player).max(Comparator.comparing(RealPlayer::getVictoryPoint)).get();

    }

    public void setCurrentGameState(GameState gameState){
        this.currentGameState = gameState;
    }

}
