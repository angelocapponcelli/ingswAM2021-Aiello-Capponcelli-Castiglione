package it.polimi.ingsw.client.controller;

import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.networking.messages.ErrorMessage;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.serverMessage.TurnPositionMessage;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.*;
import it.polimi.ingsw.utils.CLIColors;


public class ClientController implements Runnable{
    ClientState currentState;
    View view;

    public ClientController(View view) {
        this.view = view;
        currentState = ClientState.LOGIN;
    }

    public enum ClientState {
        LOGIN,
        INIT,
        IN_GAME,
    }

    public enum INIT{
        DISCARD_LEADER,
        CHOOSE_RESOURCES,
        MOVE_FROM_TEMPORARY
    }
    public enum IN_GAME{
        NO_MY_TURN,
        MY_TURN
    }
    public enum MY_TURN{
        ACTIVATE_PRODUCTION,
        BUY_DEV_CARD,
        TAKE_FROM_MARKET,
        DISCARD_LEADER_CARD,
        ACTIVATE_LEADER_CARD
    }


    @Override
    public void run() {
        INIT initState = INIT.DISCARD_LEADER;

        while (true) {
            synchronized (this) {
                switch (currentState) {
                    case LOGIN:
                        view.splashScreen();
                        view.askForNickName();
                        view.askForCreateOrJoinGame();
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case INIT:
                        switch (initState) {
                            case DISCARD_LEADER:
                                view.inHandLeaderCardsDraw();
                                view.askForLeaderCardsToDiscard();
                                initState = INIT.CHOOSE_RESOURCES;
                                break;
                            case CHOOSE_RESOURCES:
                                if(view.askForInitialResources()){
                                    try {
                                        wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                currentState =ClientState.IN_GAME;
                                break;
                            case MOVE_FROM_TEMPORARY:
                                view.moveFromTemporary();
                                currentState = ClientState.IN_GAME;
                                break;
                        }
                        break;

                    case IN_GAME:
                        view.refresh();
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
            case UPDATED_MARKET_TRAY:
                UpdatedMarketTrayMessage updatedMarketTray = (UpdatedMarketTrayMessage) message;
                view.getReducedGameModel().getMarketTray().setMarketTray(updatedMarketTray.getMarketTray());
                view.getReducedGameModel().getMarketTray().setSlide(updatedMarketTray.getSlide());
                //view.marketTrayDraw();
                break;
            case UPDATED_IN_HAND_LEADER_CARD:
                UpdatedInHandLeaderCardMessage updatedInHandLeaderCardMessage = (UpdatedInHandLeaderCardMessage) message;
                view.getReducedGameModel().getReducedInHandLeaderCards().update(updatedInHandLeaderCardMessage.getInHandLeaderCard());
                break;
            case TURN_POSITION_MESSAGE:
                TurnPositionMessage playerTurnPositionMessage = (TurnPositionMessage) message;
                view.getReducedGameModel().setPlayerTurnPosition(playerTurnPositionMessage.getTurnPosition());
                break;
            case UPDATED_TEMPORARY_DEPOT:
                UpdatedTemporaryDepotMessage updatedTemporaryDepotMessage = (UpdatedTemporaryDepotMessage) message;
                view.getReducedGameModel().setTemporaryDepot(updatedTemporaryDepotMessage.getTemporaryDepot());
                view.moveFromTemporary();
                notifyAll();
                break;
            case ALL_PLAYERS_JOINED:
                currentState = ClientState.INIT;
                notifyAll();
                break;
            case UPDATED_WAREHOUSE:
                UpdatedWareHouseMessage updatedWareHouseMessage = (UpdatedWareHouseMessage) message;
                view.getReducedGameModel().setWareHouseDepot(updatedWareHouseMessage.getWareHouse());
                view.refresh();
                break;
            case UPDATED_DEV_CARD_GRID:
                UpdatedDevelopmentCardGridMessage updatedDevelopmentCardGridMessage = (UpdatedDevelopmentCardGridMessage) message;
                view.getReducedGameModel().setDevelopmentCardsGrid(updatedDevelopmentCardGridMessage.getDevelopmentCardGrid());

        }
    }

}
