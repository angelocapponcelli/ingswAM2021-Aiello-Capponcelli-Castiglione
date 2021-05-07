package it.polimi.ingsw.server.utils.parsers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.Cell;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.FinalCell;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.PopeSpaceCell;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.VaticanReportSection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FaithTrackParser {
    private static final FaithTrackParser INSTANCE = new FaithTrackParser();
    private final List<Cell> track = new ArrayList<>();
    private final List<VaticanReportSection> reportSections = new ArrayList<>();

    private FaithTrackParser() {
        try {
            parser();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static FaithTrackParser getINSTANCE() {
        return INSTANCE;
    }

    private void parser() throws FileNotFoundException {
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
    }

    public List<Cell> getTrack() {
        return track;
    }

    public List<VaticanReportSection> getReportSections() {
        return reportSections;
    }
}
