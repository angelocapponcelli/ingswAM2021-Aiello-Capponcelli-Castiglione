package it.polimi.ingsw.client.controller;


import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.networking.messages.ErrorMessage;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.serverMessage.ServerText;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedInHandLeaderCardMessage;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedMarketTrayMessage;
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
            case TEXT:
                ServerText genericTextMessage= (ServerText)message;
                System.out.println(genericTextMessage.getText());
                break;
            case ERROR:
                ErrorMessage errorMessage = (ErrorMessage) message;
                System.out.println(CLIColors.getAnsiRed() + errorMessage.getErrorMessage() + CLIColors.getAnsiReset());
                break;
            case UPDATED_MARKET_TRAY:
                UpdatedMarketTrayMessage updatedMarketTray = (UpdatedMarketTrayMessage) message;
                view.getReducedGameModel().setReducedMarketTray(updatedMarketTray.getMarketTray());
                view.getReducedGameModel().setSlide(updatedMarketTray.getSlide());
                view.marketTrayDraw();
                break;
            case UPDATED_IN_HAND_LEADER_CARD:
                UpdatedInHandLeaderCardMessage updatedInHandLeaderCardMessage = (UpdatedInHandLeaderCardMessage) message;
                view.getReducedGameModel().setReducedInHandLeaderCard(updatedInHandLeaderCardMessage.getInHandLeaderCard());
                view.inHandLeaderCardsDraw();
                break;

        }
    }
}
