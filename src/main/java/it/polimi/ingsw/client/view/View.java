package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.controller.MY_TURN;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedGameModel;

import java.io.IOException;

public abstract class View {
    protected Client client;
    protected ReducedGameModel reducedGameModel;

    public View(Client client) {
        this.client = client;
        reducedGameModel = new ReducedGameModel();
    }

    //public abstract void start();

    public ReducedGameModel getReducedGameModel() {
        return reducedGameModel;
    }


    public abstract void splashScreen();
    public abstract void askForNickName();
    public abstract void askForCreateOrJoinGame();
    public abstract void askForLeaderCardsToDiscard();
    public abstract boolean askForInitialResources();
    public abstract void moveFromTemporary();
    public abstract void askForAnyResourceReplacement();


    public abstract void refresh();

    public abstract void drawMarketTray();
    public abstract void drawInHandLeaderCards();
    public abstract void drawTemporaryDepot();
    public abstract void drawWareHouse();
    public abstract void drawDevCardGrid();
    public abstract void drawPersonalDevelopmentBoard();
    public abstract void drawStrongBox();
    public abstract void drawFaithTrack();


    public abstract void askForMainAction();
    public abstract void takeFromMarket();
    public abstract void buyDevCard();
    public abstract void activateProduction();

    public abstract void faithTrackDraw();




}
