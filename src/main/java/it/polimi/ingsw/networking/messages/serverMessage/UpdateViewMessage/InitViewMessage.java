package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;
import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.List;

public class InitViewMessage extends ServerMessage {
    private final ResourceType[][] marketTray;
    private final ResourceType slide;
    private final ReducedDevelopmentCard[][] developmentCardGrid;

    public InitViewMessage(MarketTray marketTray, ReducedDevelopmentCard[][] developmentCardGrid) {
        messageType= MessageType.INIT_VIEW;
        this.marketTray = marketTray.toReduced();
        this.slide = marketTray.getSlide().getResourceType();
        this.developmentCardGrid = developmentCardGrid;
    }

    public ResourceType[][] getMarketTray() {
        return marketTray;
    }

    public ResourceType getSlide() {
        return slide;
    }

    public ReducedDevelopmentCard[][] getDevelopmentCardGrid() {
        return developmentCardGrid;
    }
}
