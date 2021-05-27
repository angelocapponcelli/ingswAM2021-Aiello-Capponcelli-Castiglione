package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.resources.ResourceType;

public class ReducedGameModel {
    private ResourceType[][] marketTray;
    private ResourceType slide;


    public void setMarketTray(ResourceType[][] marketTray) {
        this.marketTray = marketTray;
    }

    public void setSlide(ResourceType slide) {
        this.slide = slide;
    }




    public ResourceType[][] getMarketTray() {
        return marketTray;
    }

    public ResourceType getSlide() {
        return slide;
    }
}
