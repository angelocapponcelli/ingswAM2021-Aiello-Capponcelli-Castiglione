package it.polimi.ingsw.server.model.player;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedVaticanReportSection;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedVaticanReportMessage;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.VaticanReportSection;
import it.polimi.ingsw.utils.observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VaticanReportStatus extends Observable {
    private final List<VaticanReportSection> flippedVaticanReportSections;

    public VaticanReportStatus() {
        this.flippedVaticanReportSections = new ArrayList<>();
    }

    /**
     * Performs the flipping of the Pope's favor tile by adding the Vatican Report section in the flippedVaticanReportSections list.
     *
     * @param vaticanReportSection The Vatican Report Section involved in the flipping of the Pope's favor tile
     */
    public void flip(VaticanReportSection vaticanReportSection) {
        flippedVaticanReportSections.add(vaticanReportSection);
        notifyObserver(new UpdatedVaticanReportMessage(toReduced()));
    }

    /**
     * @return The sum of the victory points of all the flipped Vatican Report Sections.
     */
    public Integer getVictoryPoint() {
        Integer tmpVictoryPoints = 0;
        for (VaticanReportSection vaticanReportSection : flippedVaticanReportSections) {
            tmpVictoryPoints += vaticanReportSection.getVictoryPoints();
        }
        return tmpVictoryPoints;

    }

    public List<ReducedVaticanReportSection> toReduced(){
        return flippedVaticanReportSections.stream().map(ReducedVaticanReportSection::new).collect(Collectors.toList());
    }

}
