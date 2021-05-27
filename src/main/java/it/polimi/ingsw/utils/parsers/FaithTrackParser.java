package it.polimi.ingsw.utils.parsers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FaithTrackParser {

    public static FaithTrack getFaithTrack() throws FileNotFoundException {
        List<Cell> track = new ArrayList<>();
        List<VaticanReportSection> reportSections = new ArrayList<>();

        JsonObject jsonObject = JsonParser.parseReader(new FileReader("src/main/resources/FaithTrack.json")).getAsJsonObject();
        JsonArray tmpTrack = jsonObject.getAsJsonArray("track");
        JsonArray tmpReport = jsonObject.getAsJsonArray("vaticanReportSections");

        for (int i = 0; i < tmpTrack.size(); i++) {
            String type = tmpTrack.get(i).getAsJsonObject().get("type").getAsString();
            switch (type) {
                case "NORMAL":
                    track.add(new Cell(tmpTrack.get(i).getAsJsonObject().get("victoryPoints").getAsInt()));
                    break;
                case "POPE":
                    track.add(new PopeSpaceCell(tmpTrack.get(i).getAsJsonObject().get("victoryPoints").getAsInt()));
                    break;
                case "FINAL":
                    track.add(new FinalCell(tmpTrack.get(i).getAsJsonObject().get("victoryPoints").getAsInt()));
                    break;
            }
        }

        for (int i = 0; i < tmpReport.size(); i++) {
            List<Cell> tempCells = new ArrayList<>();
            for (int j = 0; j < tmpReport.get(i).getAsJsonObject().get("cells").getAsJsonArray().size(); j++) {
                tempCells.add(track.get(tmpReport.get(i).getAsJsonObject().get("cells").getAsJsonArray().get(j).getAsInt()));

            }
            reportSections.add(new VaticanReportSection(tempCells, tmpReport.get(i).getAsJsonObject().get("victoryPoints").getAsInt()));
        }

        return new FaithTrack(track, reportSections);

    }
}
