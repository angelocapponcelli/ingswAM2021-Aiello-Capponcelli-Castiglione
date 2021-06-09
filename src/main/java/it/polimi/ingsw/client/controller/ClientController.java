package it.polimi.ingsw.client.controller;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.networking.messages.ErrorMessage;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.serverMessage.TurnPositionMessage;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.*;
import it.polimi.ingsw.utils.CLIColors;

import java.util.Objects;


public class ClientController implements Runnable {
    private Client client;
    ClientState currentState;
    INIT initState = INIT.DISCARD_LEADER;
    IN_GAME inGameState = IN_GAME.NO_MY_TURN;
    LOGIN loginState = LOGIN.NICKNAME;
    MY_TURN myTurnState;

    View view;

    public ClientController(View view) {
        this.view = view;
        currentState = ClientState.LOGIN;
    }

    public ClientController(View view, Client client) {
        this.view = view;
        currentState = ClientState.LOGIN;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (this) {
                switch (currentState) {
                    case LOGIN:
                        //view.splashScreen();
                        switch (loginState) {
                            case NICKNAME:
                                view.askForNickName();
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                loginState = LOGIN.CREATE_OR_JOIN;
                                break;
                            case CREATE_OR_JOIN:
                                view.askForCreateOrJoinGame();
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                        break;
                    case INIT:
                        switch (initState) {
                            case DISCARD_LEADER:
                                view.inHandLeaderCardsDraw();
                                view.askForLeaderCardsToDiscard();
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                initState = INIT.CHOOSE_RESOURCES;
                                break;
                            case CHOOSE_RESOURCES:
                                if (view.askForInitialResources()) {
                                    try {
                                        wait();
                                        currentState = ClientState.MOVE_FROM_TEMPORARY;
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    currentState = ClientState.IN_GAME;
                                    inGameState = IN_GAME.MY_TURN;
                                }
                                break;

                        }
                        break;

                    case IN_GAME:
                        switch (inGameState) {
                            case MY_TURN:
                                System.out.println("your turn");
                                view.refresh();
                                myTurnState = view.askForMainAction();
                                switch (myTurnState) {
                                    case TAKE_FROM_MARKET:
                                        view.takeFromMarket();
                                        try {
                                            wait();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        inGameState = IN_GAME.NO_MY_TURN;
                                        break;

                                    case ACTIVATE_PRODUCTION:
                                        view.refresh();
                                        try {
                                            wait();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        inGameState = IN_GAME.NO_MY_TURN;
                                        break;

                                    case BUY_DEV_CARD:
                                        view.buyDevCard();
                                        try {
                                            wait();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        inGameState = IN_GAME.NO_MY_TURN;
                                        break;
                                }
                                break;

                            case NO_MY_TURN:
                                view.refresh();
                                System.out.println("not your turn");
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                        break;

                    case MOVE_FROM_TEMPORARY:
                        view.moveFromTemporary();
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        currentState = ClientState.IN_GAME;
                        break;

                    case RESOURCE_REPLACEMENT:
                        view.askForAnyResourceReplacement();
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        currentState = ClientState.MOVE_FROM_TEMPORARY;
                        break;
                }
            }
        }
    }

    public synchronized void manageReceivedMessage(Message message) {
        switch (message.getMessageType()) {
            case ERROR:
                ErrorMessage errorMessage = (ErrorMessage) message;
                System.out.println(CLIColors.getAnsiRed() + errorMessage.getErrorMessage() + CLIColors.getAnsiReset());
                break;
            case INIT_VIEW:
                InitViewMessage initViewMessage = (InitViewMessage) message;
                view.getReducedGameModel().setPlayers(initViewMessage.getReducedPlayers());
                view.getReducedGameModel().getMarketTray().setMarketTray(initViewMessage.getMarketTray());
                view.getReducedGameModel().getMarketTray().setSlide(initViewMessage.getSlide());
                view.getReducedGameModel().setDevelopmentCardsGrid(initViewMessage.getDevelopmentCardGrid());
                break;
            case UPDATED_MARKET_TRAY:
                UpdatedMarketTrayMessage updatedMarketTray = (UpdatedMarketTrayMessage) message;
                view.getReducedGameModel().getMarketTray().setMarketTray(updatedMarketTray.getMarketTray());
                view.getReducedGameModel().getMarketTray().setSlide(updatedMarketTray.getSlide());
                //view.refresh();
                break;
            case UPDATED_IN_HAND_LEADER_CARD:
                UpdatedInHandLeaderCardMessage updatedInHandLeaderCardMessage = (UpdatedInHandLeaderCardMessage) message;
                view.getReducedGameModel().getReducedInHandLeaderCards().update(updatedInHandLeaderCardMessage.getInHandLeaderCard());
                //view.refresh();
                break;
            case TURN_POSITION_MESSAGE:
                TurnPositionMessage playerTurnPositionMessage = (TurnPositionMessage) message;
                view.getReducedGameModel().setPlayerTurnPosition(playerTurnPositionMessage.getTurnPosition());
                break;
            case UPDATED_TEMPORARY_DEPOT:
                UpdatedTemporaryDepotMessage updatedTemporaryDepotMessage = (UpdatedTemporaryDepotMessage) message;
                view.getReducedGameModel().setTemporaryDepot(updatedTemporaryDepotMessage.getTemporaryDepot());
                currentState = ClientState.MOVE_FROM_TEMPORARY;
                break;
            case ALL_PLAYERS_JOINED:
                currentState = ClientState.INIT;
                notifyAll();
                break;
            case UPDATED_WAREHOUSE:
                UpdatedWareHouseMessage updatedWareHouseMessage = (UpdatedWareHouseMessage) message;
                view.getReducedGameModel().setWareHouseDepot(updatedWareHouseMessage.getWareHouse());
                //view.refresh();
                break;
            case UPDATED_DEV_CARD_GRID:
                UpdatedDevelopmentCardGridMessage updatedDevelopmentCardGridMessage = (UpdatedDevelopmentCardGridMessage) message;
                view.getReducedGameModel().setDevelopmentCardsGrid(updatedDevelopmentCardGridMessage.getDevelopmentCardGrid());
                //view.refresh();
                break;
            case UPDATED_PERSONAL_DEVELOPMENT_BOARD:
                UpdatedPersonalDevelopmentBoardMessage updatedPersonalDevelopmentBoardMessage = (UpdatedPersonalDevelopmentBoardMessage) message;
                view.getReducedGameModel().setPersonalDevelopmentBoard(updatedPersonalDevelopmentBoardMessage.getDevelopmentCards());
                //view.refresh();
                break;
            case MY_TURN_MESSAGE:
                inGameState = IN_GAME.MY_TURN;
                notifyAll();
                break;
            case ACTION_ENDED:
                notifyAll();
                break;
            case UPDATED_FAITH_POSITION:
                UpdatedFaithPositionMessage updatedFaithPositionMessage = (UpdatedFaithPositionMessage) message;
                Objects.requireNonNull(view.getReducedGameModel().getPlayers().stream()
                        .filter(reducedPlayer -> reducedPlayer.getNickName().equals(updatedFaithPositionMessage.getNickname()))
                        .findFirst().orElse(null)).setFaithPosition(updatedFaithPositionMessage.getFaithPosition());

        }
    }

    public enum ClientState {
        LOGIN,
        INIT,
        IN_GAME,
        MOVE_FROM_TEMPORARY,
        RESOURCE_REPLACEMENT
    }

    public enum INIT {
        DISCARD_LEADER,
        CHOOSE_RESOURCES,
        MOVE_FROM_TEMPORARY
    }
    /*public enum MY_TURN{
        ACTIVATE_PRODUCTION,
        BUY_DEV_CARD,
        TAKE_FROM_MARKET,
        DISCARD_LEADER_CARD,
        ACTIVATE_LEADER_CARD
    }*/


    public enum IN_GAME {
        NO_MY_TURN,
        MY_TURN,
    }


    public enum LOGIN {
        NICKNAME,
        CREATE_OR_JOIN
    }

}
