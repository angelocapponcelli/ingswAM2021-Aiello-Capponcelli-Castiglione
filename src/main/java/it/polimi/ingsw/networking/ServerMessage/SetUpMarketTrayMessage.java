package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.misc.Colors;

public class SetUpMarketTrayMessage extends ServerMessage{
    private Colors slide;
    private MarketTray marketTray;

    public SetUpMarketTrayMessage(Colors slide, MarketTray marketTray){
        this.slide= slide;
        this.marketTray= marketTray;
    }

    public Colors getSlide() {
        return slide;
    }

    public MarketTray getMarketTray() {
        return marketTray;
    }
}
