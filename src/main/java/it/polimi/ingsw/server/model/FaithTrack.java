package it.polimi.ingsw.server.model;

import java.util.List;


public class FaithTrack {

    private static final FaithTrack INSTANCE = new FaithTrack();
    private List<Cell> track;
    private List<VaticanReportSection> vaticanReportSectionList;

    private FaithTrack() {
    }

    private FaithTrack(List<Cell> track, List<VaticanReportSection> vaticanReportSectionList) {
        this.track = track;
        this.vaticanReportSectionList = vaticanReportSectionList;
    }

    public static FaithTrack getINSTANCE() {
        return INSTANCE;
    }

    /**
     * @param cell The cell contained in the VaticanReportSection.
     * @return Vatican report section that contains the cell.
     */
    public VaticanReportSection getVaticanReportSectionFromCell(Cell cell) {
        VaticanReportSection vc = null;
        for (VaticanReportSection vaticanReportSection : vaticanReportSectionList) {
            if (vaticanReportSection.getCells().contains(cell)) {
                vc = vaticanReportSection;
            }
        }
        return vc;

    }

    /**
     * Gets track.
     *
     * @return the track
     */
    public List<Cell> getTrack() {
        return track;
    }

    /**
     * Gets vatican report section list.
     *
     * @return the vatican report section list
     */
    public List<VaticanReportSection> getVaticanReportSectionList() {
        return vaticanReportSectionList;
    }

}
