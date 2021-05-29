package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.resources.ResourceType;

public class ReducedMarketTray {

    private ResourceType[][] marketTray;
    private ResourceType slide;

    public ResourceType[][] getMarketTray() {
        return marketTray;
    }

    public void setMarketTray(ResourceType[][] marketTray) {
        this.marketTray = marketTray;
    }

    public ResourceType getSlide() {
        return slide;
    }

    public void setSlide(ResourceType slide) {
        this.slide = slide;
    }
}
