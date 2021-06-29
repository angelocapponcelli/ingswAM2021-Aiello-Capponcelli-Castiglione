package it.polimi.ingsw.server.model.globalBoard.faithTrack;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedFaithCell;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.observer.Observer;
import it.polimi.ingsw.utils.parsers.SettingsParser;
import java.util.ArrayList;
import java.util.List;

/**
 * Faith track. The track where the players have their position. It is composed by a list of cells and a list of
 * vatican report section
 */
public class FaithTrack extends Observable {
    private final List<Cell> track;
    private final List<VaticanReportSection> vaticanReportSectionList;

    /**
     * Class constructor. Instantiates a new FaithTrack
     */
    public FaithTrack(){
        FaithTrack temp = SettingsParser.getInstance().getFaithTrack();
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
     * Gets a reduced version of the faithtrack
     * @return reduced version of faith track
     */
    public List<ReducedFaithCell> toReduced(){
        List<ReducedFaithCell> reduced = new ArrayList<>();

        track.forEach(cell -> {
            String cellType = "NORMAL";
            if(cell instanceof PopeSpaceCell) cellType = "POPE";
            if (cell instanceof FinalCell) cellType = "FINAL";

            reduced.add(new ReducedFaithCell(cell.getVictoryPoints(),
                    getVaticanReportSectionFromCell(cell) != null ? getVaticanReportSectionFromCell(cell).getVictoryPoints():0,
                    cellType
                    ));
        });
        return reduced;
    }

}
