package it.polimi.ingsw.utils.parsers;

import com.google.gson.Gson;
import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MarketTrayParser {

    public static MarketTray getMarketTray() throws FileNotFoundException {
        Gson gson = new Gson();
        tempJson tmp = gson.fromJson(new FileReader("src/main/resources/MarketTray.json"), tempJson.class);

        List<ResourceType> marblesList = new ArrayList<>();
        ResourceType slide;

        tmp.marbles.forEach((type, multiplicity) -> {
            for (int i = 0; i < multiplicity; i++) {
                marblesList.add(type);
            }
        });

        Collections.shuffle(marblesList);


        Resource[][] tmpMarketTray = new Resource[tmp.nRows][tmp.nCols];
        for (int i = 0; i < tmp.nRows; i++) {
            for (int j = 0; j < tmp.nCols; j++) {
                tmpMarketTray[i][j] = ResourceType.getResourceClass(marblesList.get(i + j + ((tmp.nCols - 1) * i)));
            }
        }
        slide = marblesList.get(marblesList.size() - 1);

        return new MarketTray(tmpMarketTray, ResourceType.getResourceClass(slide));


    }

    private static class tempJson {
        Integer nRows;
        Integer nCols;
        Map<ResourceType, Integer> marbles;

        public tempJson(Integer nRow, Integer nCols, Map<ResourceType, Integer> marbles) {
            this.nRows = nRow;
            this.nCols = nCols;
            this.marbles = marbles;
        }
    }
}
