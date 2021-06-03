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
        MOVE_FROM_TEMPORARY
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
        IN_GAME inGameState = IN_GAME.NO_MY_TURN;

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
                                view.askForLeaderCardsToDiscard();
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                initState = INIT.CHOOSE_RESOURCES;
                                break;
                            case CHOOSE_RESOURCES:
                                if(view.askForInitialResources()){
                                    try {
                                        wait();
                                        //currentState = ClientState.IN_GAME.MOVE_FROM_TEMPORARY;
                                        currentState = ClientState.MOVE_FROM_TEMPORARY;
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }else {
                                    currentState = ClientState.IN_GAME;
                                    inGameState = IN_GAME.MY_TURN;
                                }
                                break;

                        }
                        break;

                    case IN_GAME:
                        switch (inGameState){
                            case MY_TURN:
                                view.refresh();
                                view.askForMainAction();
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                inGameState = IN_GAME.NO_MY_TURN;
                                break;
                            case NO_MY_TURN:
                                view.refresh();
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
                view.getReducedGameModel().getMarketTray().setMarketTray(initViewMessage.getMarketTray());
                view.getReducedGameModel().getMarketTray().setSlide(initViewMessage.getSlide());
                view.getReducedGameModel().setDevelopmentCardsGrid(initViewMessage.getDevelopmentCardGrid());
                //view.refresh();
                break;
            case UPDATED_MARKET_TRAY:
                UpdatedMarketTrayMessage updatedMarketTray = (UpdatedMarketTrayMessage) message;
                view.getReducedGameModel().getMarketTray().setMarketTray(updatedMarketTray.getMarketTray());
                view.getReducedGameModel().getMarketTray().setSlide(updatedMarketTray.getSlide());
                view.refresh();
                //view.marketTrayDraw();
                break;
            case UPDATED_IN_HAND_LEADER_CARD:
                UpdatedInHandLeaderCardMessage updatedInHandLeaderCardMessage = (UpdatedInHandLeaderCardMessage) message;
                view.getReducedGameModel().getReducedInHandLeaderCards().update(updatedInHandLeaderCardMessage.getInHandLeaderCard());
                System.out.println("cwjndj");
                view.refresh();
                break;
            case TURN_POSITION_MESSAGE:
                TurnPositionMessage playerTurnPositionMessage = (TurnPositionMessage) message;
                view.getReducedGameModel().setPlayerTurnPosition(playerTurnPositionMessage.getTurnPosition());
                break;
            case UPDATED_TEMPORARY_DEPOT:
                UpdatedTemporaryDepotMessage updatedTemporaryDepotMessage = (UpdatedTemporaryDepotMessage) message;
                view.getReducedGameModel().setTemporaryDepot(updatedTemporaryDepotMessage.getTemporaryDepot());
                currentState = ClientState.MOVE_FROM_TEMPORARY;
                //view.moveFromTemporary();
                //notifyAll();
                break;
            case ALL_PLAYERS_JOINED:
                currentState = ClientState.INIT;
                notifyAll();
                break;
            case UPDATED_WAREHOUSE:
                UpdatedWareHouseMessage updatedWareHouseMessage = (UpdatedWareHouseMessage) message;
                view.getReducedGameModel().setWareHouseDepot(updatedWareHouseMessage.getWareHouse());
                //notifyAll();
                view.refresh();
                break;
            case UPDATED_DEV_CARD_GRID:
                UpdatedDevelopmentCardGridMessage updatedDevelopmentCardGridMessage = (UpdatedDevelopmentCardGridMessage) message;
                view.getReducedGameModel().setDevelopmentCardsGrid(updatedDevelopmentCardGridMessage.getDevelopmentCardGrid());
                view.refresh();
                break;
            case ACTION_ENDED:
                System.out.println("action ENDED");
                notifyAll();
                break;

        }
    }

}
