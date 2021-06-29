package it.polimi.ingsw.server.model.globalBoard.faithTrack;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedFaithCell;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.observer.Observer;
import it.polimi.ingsw.utils.parsers.FaithTrackParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Faith Track class. It is composed by a list of Cells and a list of Vatican Report Sections
 */

public class FaithTrack extends Observable {
    private final List<Cell> track;
    private final List<VaticanReportSection> vaticanReportSectionList;

    /**
     * Class constructor. Instantiates a new FaithTrack.
     * @throws FileNotFoundException if the JSON file is not found
     */
    public FaithTrack() throws FileNotFoundException {
        FaithTrack temp = FaithTrackParser.getFaithTrack();
        this.track = temp.getTrack();
        this.vaticanReportSectionList = temp.getVaticanReportSectionList();
    }

    /**
     * This constructor is use by the FaithTrackParser to create a new faithTrack.
     *
     * @param track                    the list of cells.
     * @param vaticanReportSectionList the list of vatican report sections.
     */
    public FaithTrack(List<Cell> track, List<VaticanReportSection> vaticanReportSectionList) {
        this.track = track;
        this.vaticanReportSectionList = vaticanReportSectionList;
    }


    /**
     * Gets the Vatican report sections that contains the cell. If the cell isn't in any Vatican Report Section it returns null.
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

    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        for(Cell cell: track){
            cell.addObserver(obs);
        }
    }

    @Override
    public void removeObserver(Observer obs) {
        super.removeObserver(obs);
        for(Cell cell: track){
            cell.removeObserver(obs);
        }
    }

    /**
     * Gets the reduced version of the faith track
     * @return a list of a reduced version of the cells of the track
     */

    public List<ReducedFaithCell> toReduced(){
        List<ReducedFaithCell> reduced = new ArrayList<>();

        track.stream()
                .forEach(cell -> reduced.add(new ReducedFaithCell(cell.getVictoryPoints(), getVaticanReportSectionFromCell(cell) != null ? getVaticanReportSectionFromCell(cell).getVictoryPoints():0)));
        return reduced;
    }

}
