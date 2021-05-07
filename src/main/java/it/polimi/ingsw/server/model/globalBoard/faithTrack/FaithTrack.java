package it.polimi.ingsw.server.model.globalBoard.faithTrack;

import it.polimi.ingsw.server.utils.parsers.FaithTrackParser;

import java.util.List;


public class FaithTrack {

    private static final FaithTrack INSTANCE = new FaithTrack();
    private final List<Cell> track;
    private final List<VaticanReportSection> vaticanReportSectionList;


    private FaithTrack() {
        this.track = FaithTrackParser.getINSTANCE().getTrack();
        this.vaticanReportSectionList = FaithTrackParser.getINSTANCE().getReportSections();
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
