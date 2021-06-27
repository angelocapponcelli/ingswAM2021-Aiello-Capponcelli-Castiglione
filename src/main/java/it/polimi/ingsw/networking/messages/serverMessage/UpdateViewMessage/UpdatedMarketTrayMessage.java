package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;
import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class UpdatedMarketTrayMessage extends ServerMessage {
    private final ResourceType[][] marketTray;
    private final ResourceType slide;


    public UpdatedMarketTrayMessage(MarketTray marketTray) {
        messageType = MessageType.UPDATED_MARKET_TRAY;

        slide = marketTray.getSlide().getResourceType();
        int row = marketTray.getMarketTray().length;
        int col = marketTray.getMarketTray()[0].length;
        this.marketTray = new ResourceType[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.marketTray[i][j] = marketTray.getMarketTray()[i][j].getResourceType();
            }
        }
    }

    public ResourceType[][] getMarketTray() {
        return marketTray;
    }

    public ResourceType getSlide() {
        return slide;
    }
}
