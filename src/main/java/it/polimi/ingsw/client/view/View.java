package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedGameModel;

public abstract class View {
    Client client;
    ReducedGameModel reducedGameModel;

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

    public abstract void marketTrayDraw();
    public abstract void inHandLeaderCardsDraw();
    public abstract void temporaryDepotDraw();
    public abstract void wareHouseDraw();
    public abstract void devCardGridDraw();




}
