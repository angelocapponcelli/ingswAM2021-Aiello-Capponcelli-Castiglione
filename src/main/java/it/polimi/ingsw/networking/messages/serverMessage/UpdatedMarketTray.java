package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class UpdatedMarketTray extends ServerMessage{
    private ResourceType[][] marketTray;
    private ResourceType slide;

    public UpdatedMarketTray(MarketTray marketTray){
        messageType = MessageType.UPDATED_MARKET_TRAY ;
        slide = marketTray.getSlide().getResourceType();
        this.marketTray = new ResourceType[marketTray.getRow(0).size()][marketTray.getColumn(0).size()];
    }

    public ResourceType[][] getMarketTray() {
        return marketTray;
    }

    public ResourceType getSlide() {
        return slide;
    }
}
