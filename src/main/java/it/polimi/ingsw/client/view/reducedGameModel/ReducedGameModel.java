package it.polimi.ingsw.client.view.reducedGameModel;

public class ReducedGameModel {
    ReducedMarketTray reducedMarketTray = new ReducedMarketTray();
    ReducedInHandLeaderCards reducedInHandLeaderCards = new ReducedInHandLeaderCards();


    public ReducedMarketTray getReducedMarketTray() {
        return reducedMarketTray;
    }

    public ReducedInHandLeaderCards getReducedInHandLeaderCards() {
        return reducedInHandLeaderCards;
    }
}
