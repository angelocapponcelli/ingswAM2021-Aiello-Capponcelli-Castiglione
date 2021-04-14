package it.polimi.ingsw.server.model;

import java.util.List;


public class FaithTrack {
    private List<Cell> track;
    private List<VaticanReportSection> vaticanReportSectionList;

    /** check if this constructor is okay*/
    public FaithTrack(List<Cell> track, List<VaticanReportSection> vaticanReportSectionList){
        this.track = track;
        this.vaticanReportSectionList = vaticanReportSectionList;
    }

    /** methods**/

    public List<Cell> getTrack() {
        return track;
    }

    public List<VaticanReportSection> getVaticanReportSectionList() {
        return vaticanReportSectionList;
    }

}
