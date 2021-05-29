package it.polimi.ingsw.client.controller;


import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.networking.messages.ErrorMessage;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.serverMessage.TurnPositionMessage;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedInHandLeaderCardMessage;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedMarketTrayMessage;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedTemporaryDepotMessage;
import it.polimi.ingsw.utils.CLIColors;

public class ClientController{
    ClientState currentState;
    View view;

    public ClientController(View view){
        this.view = view;
        currentState = ClientState.START_SCREEN;
    }


    public void manageReceivedMessage(Message message) {
        switch (message.getMessageType()){
            case ERROR:
                ErrorMessage errorMessage = (ErrorMessage) message;
                System.out.println(CLIColors.getAnsiRed() + errorMessage.getErrorMessage() + CLIColors.getAnsiReset());
                break;
            case UPDATED_MARKET_TRAY:
                UpdatedMarketTrayMessage updatedMarketTray = (UpdatedMarketTrayMessage) message;
                view.getReducedGameModel().getMarketTray().setMarketTray(updatedMarketTray.getMarketTray());
                view.getReducedGameModel().getMarketTray().setSlide(updatedMarketTray.getSlide());
                view.marketTrayDraw();
                break;
            case UPDATED_IN_HAND_LEADER_CARD:
                UpdatedInHandLeaderCardMessage updatedInHandLeaderCardMessage = (UpdatedInHandLeaderCardMessage) message;
                view.getReducedGameModel().getReducedInHandLeaderCards().update(updatedInHandLeaderCardMessage.getInHandLeaderCard());
                view.refresh();
                break;
            case DISCARD_LEADER_CARD_REQUEST:
                view.askForLeaderCardsToDiscard();
                break;
            case TURN_POSITION_MESSAGE:
                TurnPositionMessage playerTurnPositionMessage = (TurnPositionMessage) message;
                view.getReducedGameModel().setPlayerTurnPosition(playerTurnPositionMessage.getTurnPosition());
                break;
            case SELECT_INITIAL_RESOURCE_REQUEST:
                view.askForInitialResources();
                break;
            case UPDATED_TEMPORARY_DEPOT:
                UpdatedTemporaryDepotMessage updatedTemporaryDepotMessage = (UpdatedTemporaryDepotMessage) message;
                view.getReducedGameModel().setTemporaryDepot(updatedTemporaryDepotMessage.getTemporaryDepot());
                view.temporaryDepotDraw();

        }
    }

}
