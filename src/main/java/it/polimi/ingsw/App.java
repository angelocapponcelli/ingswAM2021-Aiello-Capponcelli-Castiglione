package it.polimi.ingsw;

import it.polimi.ingsw.server.model.MarketTray;
import it.polimi.ingsw.server.model.interfaces.Takeable;
import it.polimi.ingsw.server.utils.DevelopmentCardParser;

import java.io.FileNotFoundException;


public class App {
    public static void main(String[] args) throws FileNotFoundException {

        DevelopmentCardParser.getCards();

        MarketTray marketTray = new MarketTray();
        Takeable[][] tray = marketTray.getMarketTray();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%s ", tray[i][j]);
            }
        }

    }
}
