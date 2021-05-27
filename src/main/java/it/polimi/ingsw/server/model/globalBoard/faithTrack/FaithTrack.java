package it.polimi.ingsw.server.model.globalBoard.faithTrack;

import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.parsers.FaithTrackParser;

import java.io.FileNotFoundException;
import java.util.List;


public class FaithTrack  extends Observable {
    private final List<Cell> track;
    private final List<VaticanReportSection> vaticanReportSectionList;


    public FaithTrack() throws FileNotFoundException {
        FaithTrack temp = FaithTrackParser.getFaithTrack();
        this.track = temp.getTrack();
        this.vaticanReportSectionList = temp.getVaticanReportSectionList();
    }

    /**
     * This constructor is use by the FaithTrackParser to create a new faithTrack.
     *
     * @param track the list of cells.
     * @param vaticanReportSectionList the list of vatican report sections.
     */
    public FaithTrack(List<Cell> track, List<VaticanReportSection> vaticanReportSectionList){
        this.track = track;
        this.vaticanReportSectionList = vaticanReportSectionList;
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
