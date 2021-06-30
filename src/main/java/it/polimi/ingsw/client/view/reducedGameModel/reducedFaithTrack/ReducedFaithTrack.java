package it.polimi.ingsw.client.view.reducedGameModel.reducedFaithTrack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReducedFaithTrack implements Serializable {
    private List<ReducedFaithCell> track = new ArrayList<>();
    private List<ReducedVaticanReportSection> vaticanReportSections = new ArrayList<>();


    public List<ReducedFaithCell> getTrack() {
        return track;
    }

    public void setTrack(List<ReducedFaithCell> track) {
        this.track = track;
    }

    public List<ReducedVaticanReportSection> getVaticanReportSections() {
        return vaticanReportSections;
    }

    public void setVaticanReportSections(List<ReducedVaticanReportSection> vaticanReportSections) {
        this.vaticanReportSections = vaticanReportSections;
    }
}
