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
    public void drawMarketTray() {

    }

    @Override
    public void drawInHandLeaderCards() {

    }

    @Override
    public void drawTemporaryDepot() {

    }

    @Override
    public void drawWareHouse() {

    }

    @Override
    public void drawDevCardGrid() {

    }

    @Override
    public void drawPersonalDevelopmentBoard() {

    }

    @Override
    public void drawStrongBox() {

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
    public void activateProduction() {

    }

    @Override
    public void faithTrackDraw() {

    }
}
