package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.controller.MY_TURN;
import it.polimi.ingsw.client.view.View;
public class GUI extends View {

    public GUI(Client client) {
        super(client);
    }

    @Override
    public void splashScreen() {

    }

    @Override
    public void askForNickName() {
    }

    @Override
    public void askForCreateOrJoinGame() {
    }

    @Override
    public void askForLeaderCardsToDiscard() {

    }

    @Override
    public boolean askForInitialResources() {
        return false;
    }

    @Override
    public void moveFromTemporary() {

    }

    @Override
    public void askForAnyResourceReplacement() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void marketTrayDraw() {

    }

    @Override
    public void inHandLeaderCardsDraw() {

    }

    @Override
    public void temporaryDepotDraw() {

    }

    @Override
    public void wareHouseDraw() {

    }

    @Override
    public void devCardGridDraw() {

    }

    @Override
    public void personalDevelopmentBoardDraw() {

    }

    @Override
    public MY_TURN askForMainAction() {
        return null;
    }

    @Override
    public void takeFromMarket() {

    }

    @Override
    public void buyDevCard() {

    }

    @Override
    public void faithTrackDraw() {

    }
}
