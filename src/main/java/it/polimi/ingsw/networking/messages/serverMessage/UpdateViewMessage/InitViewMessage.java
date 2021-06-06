package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedPlayer;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;
import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.List;

public class InitViewMessage extends ServerMessage {
    private final List<ReducedPlayer> reducedPlayers;
    private final ResourceType[][] marketTray;
    private final ResourceType slide;
    private final ReducedDevelopmentCard[][] developmentCardGrid;

    public InitViewMessage(List<ReducedPlayer> players, MarketTray marketTray, ReducedDevelopmentCard[][] developmentCardGrid) {
        this.reducedPlayers = players;
        messageType= MessageType.INIT_VIEW;
        this.marketTray = marketTray.toReduced();
        this.slide = marketTray.getSlide().getResourceType();
        this.developmentCardGrid = developmentCardGrid;
    }



    public List<ReducedPlayer> getReducedPlayers() {
        return reducedPlayers;
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
