package it.polimi.ingsw.client.controller;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.networking.messages.ErrorMessage;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.serverMessage.ItIsMyTurnMessage;
import it.polimi.ingsw.networking.messages.serverMessage.TurnPositionMessage;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.*;
import it.polimi.ingsw.client.view.CLI.CLIColors;

import java.util.Objects;


public class ClientController implements Runnable {
    private ClientState currentState = ClientState.LOGIN;
    private LOGIN loginState = LOGIN.NICKNAME;
    private INIT initState = INIT.DISCARD_LEADER;
    private IN_GAME inGameState = IN_GAME.NO_MY_TURN;
    private MY_TURN myTurnState;
    private final View view;

    public void setMyTurnState(MY_TURN myTurnState) {
        this.myTurnState = myTurnState;
    }

    public synchronized void update(){
        notifyAll();
    }

    public synchronized void pause(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public ClientController(View view) {
        this.view = view;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                switch (currentState) {
                    case LOGIN:
                        switch (loginState) {
                            case SPLASH:
                                view.splashScreen();
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                loginState = LOGIN.NICKNAME;
                            case NICKNAME:
                                view.askForNickName();
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
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
                                view.askForLeaderCardsToDiscard();
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
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
                                int inHandLeaderCardNumber = (int) view.getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().stream().filter( reducedLeaderCard -> reducedLeaderCard.getPlayed()==false ).count();
                                System.out.println("your turn");
                                view.refresh();
                                view.activateLeaderCard();
                                view.askForMainAction();
                                switch (myTurnState) {
                                    case TAKE_FROM_MARKET:
                                        System.out.println("takeFrom");
                                        view.takeFromMarket();
                                        try {
                                            wait();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if (inHandLeaderCardNumber != (int) view.getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().stream().filter( reducedLeaderCard -> reducedLeaderCard.getPlayed()==false ).count()) view.activateLeaderCard();
                                        inGameState = IN_GAME.NO_MY_TURN;
                                        break;

                                    case ACTIVATE_PRODUCTION:
                                        System.out.println("Activate");
                                        view.activateProduction();
                                        try {
                                            wait();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if (inHandLeaderCardNumber != (int) view.getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().stream().filter( reducedLeaderCard -> reducedLeaderCard.getPlayed()==false ).count()) view.activateLeaderCard();
                                        inGameState = IN_GAME.NO_MY_TURN;
                                        break;

                                    case BUY_DEV_CARD:
                                        System.out.println("buydev");
                                        view.buyDevCard();
                                        try {
                                            wait();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if (inHandLeaderCardNumber != (int) view.getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().stream().filter( reducedLeaderCard -> reducedLeaderCard.getPlayed()==false ).count()) view.activateLeaderCard();
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
                errorHandler(errorMessage);
                break;
            case INIT_VIEW:
                InitViewMessage initViewMessage = (InitViewMessage) message;
                view.getReducedGameModel().setPlayers(initViewMessage.getReducedPlayers());
                view.getReducedGameModel().getMarketTray().setMarketTray(initViewMessage.getMarketTray());
                view.getReducedGameModel().getMarketTray().setSlide(initViewMessage.getSlide());
                view.getReducedGameModel().setDevelopmentCardsGrid(initViewMessage.getDevelopmentCardGrid());
                view.getReducedGameModel().setProductionPowerInputBoard(initViewMessage.getProductionPower().getProductionPowerInput());
                view.getReducedGameModel().setProductionPowerOutputBoard(initViewMessage.getProductionPower().getProductionPowerOutput());
                view.getReducedGameModel().getFaithTrack().setTrack(initViewMessage.getFaithTrack().getTrack());
                view.getReducedGameModel().getFaithTrack().setVaticanReportSections(initViewMessage.getFaithTrack().getVaticanReportSections());
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
            case UPDATED_VATICAN_REPORT_SECTION:
                UpdatedVaticanReportMessage updatedVaticanReportMessage = (UpdatedVaticanReportMessage) message;
                view.getReducedGameModel().setFlippedVaticanReportSections(updatedVaticanReportMessage.getUpdatedVaticanReportSections());
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
            case UPDATED_SPECIALDEPOT:
                UpdatedSpecialDepotMessage updatedSpecialDepotMessage = (UpdatedSpecialDepotMessage) message;
                view.getReducedGameModel().setSpecialDepot(updatedSpecialDepotMessage.getSepcialDepot());
                //view.refresh();
                break;
            case UPDATED_STRONG_BOX:
                UpdatedStrongBoxMessage updatedStrongBoxMessage = (UpdatedStrongBoxMessage) message;
                view.getReducedGameModel().setStrongBoxDepot(updatedStrongBoxMessage.getStrongBox());
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
                ItIsMyTurnMessage itIsMyTurnMessage = (ItIsMyTurnMessage) message;
                inGameState = IN_GAME.MY_TURN;
                if (itIsMyTurnMessage.getCanPlayLeaderCard())
                    myTurnState = MY_TURN.ACTIVATE_LEADER_CARD;
                else myTurnState = null;
                /* If player is not moving resources to not stop him in his action
                 * He will wake up by the end of the action
                 */
                if (currentState != ClientState.MOVE_FROM_TEMPORARY) notifyAll();
                break;
            case ACTION_ENDED:
                evolve();
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
        SPLASH,
        NICKNAME,
        CREATE_OR_JOIN
    }


    private void errorHandler(ErrorMessage errorMessage){
        /*it should use "view.errorPopUp() instead of println()"*/
        switch (errorMessage.getErrorType()){
            case GAME_IS_FULL:
                System.out.println(CLIColors.getAnsiRed() + "This Game is Full!" + CLIColors.getAnsiReset());
                notifyAll();
                break;
            case ID_NOT_EXISTS:
                System.out.println(CLIColors.getAnsiRed() + "This ID do not exists!" + CLIColors.getAnsiReset());
                notifyAll();
                break;
            case NICKNAME_ALREADY_TAKEN:
                System.out.println(CLIColors.getAnsiRed() + "NickName already taken" + CLIColors.getAnsiReset());
                notifyAll();
                break;

        }
    }


    public void evolve(){
        switch (currentState){
            case LOGIN:
                switch (loginState){
                    case SPLASH:
                        loginState = LOGIN.NICKNAME;
                        break;
                    case NICKNAME:
                        loginState = LOGIN.CREATE_OR_JOIN;
                        break;
                }
                break;
            case INIT:
                switch (initState){
                    case DISCARD_LEADER:
                        initState = INIT.CHOOSE_RESOURCES;
                        break;

                }
                break;
            case IN_GAME:
                break;
            case MOVE_FROM_TEMPORARY:
                break;
            case RESOURCE_REPLACEMENT:
                break;
        }
    }

}
