package it.polimi.ingsw.server.model.globalBoard.faithTrack;

import it.polimi.ingsw.client.view.reducedGameModel.reducedFaithTrack.ReducedFaithCell;
import it.polimi.ingsw.client.view.reducedGameModel.reducedFaithTrack.ReducedFaithTrack;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.observer.Observer;
import it.polimi.ingsw.utils.parsers.SettingsParser;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * Gets a reduced version of the faith track
     * @return reduced version of faith track
     */
    public ReducedFaithTrack toReduced(){
        List<ReducedFaithCell> reducedTrack = new ArrayList<>();
        ReducedFaithTrack reducedFaithTrack = new ReducedFaithTrack();

        track.forEach(cell -> {
            String cellType = "NORMAL";
            if(cell instanceof PopeSpaceCell) cellType = "POPE";
            if (cell instanceof FinalCell) cellType = "FINAL";

            reducedTrack.add(new ReducedFaithCell(cell.getVictoryPoints(),
                    getVaticanReportSectionFromCell(cell) != null ? getVaticanReportSectionFromCell(cell).getVictoryPoints():0,
                    cellType
                    ));

        });

        reducedFaithTrack.setTrack(reducedTrack);
        reducedFaithTrack.setVaticanReportSections(vaticanReportSectionList.stream().map(VaticanReportSection::toReduced).collect(Collectors.toList()));

        return reducedFaithTrack;
    }

}
