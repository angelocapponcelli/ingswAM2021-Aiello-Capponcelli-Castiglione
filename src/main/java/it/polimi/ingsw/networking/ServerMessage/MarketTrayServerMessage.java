package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.misc.Colors;

public class MarketTrayServerMessage extends ServerMessage{
    private Colors slide;
    private MarketTray marketTray;

    public MarketTrayServerMessage(Colors slide, MarketTray marketTray){
        this.slide= slide;
        this.marketTray= marketTray;
    }
}
