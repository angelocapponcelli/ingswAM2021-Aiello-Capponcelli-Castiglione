package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the faith track
 */
class FaithTrackTest {
    private FaithTrack faithTrack;

    /**
     * Initializes the faith track with the parser
     */
    @BeforeEach
    void init() {
        faithTrack = new FaithTrack();
    }

    /**
     * Checks if the construction of the track was corrected
     */
    @Test
    void checkParsing() {
        assertEquals(faithTrack.getTrack().get(0).getVictoryPoints(), 0);
        assertEquals(faithTrack.getTrack().get(1).getVictoryPoints(), 0);
        assertEquals(faithTrack.getTrack().get(2).getVictoryPoints(), 0);
        assertEquals(faithTrack.getTrack().get(3).getVictoryPoints(), 1);
        assertEquals(faithTrack.getTrack().get(4).getVictoryPoints(), 1);
        assertEquals(faithTrack.getTrack().get(5).getVictoryPoints(), 1);
        assertEquals(faithTrack.getTrack().get(6).getVictoryPoints(), 2);

        assertEquals(faithTrack.getTrack().get(8).getVictoryPoints(), 2);
        assertEquals(faithTrack.getTrack().get(8).getClass().getSimpleName(), "PopeSpaceCell");

        assertEquals(faithTrack.getTrack().get(16).getVictoryPoints(), 9);
        assertEquals(faithTrack.getTrack().get(16).getClass().getSimpleName(), "PopeSpaceCell");


        assertEquals(faithTrack.getTrack().get(24).getVictoryPoints(), 20);
        assertEquals(faithTrack.getTrack().get(24).getClass().getSimpleName(), "FinalCell");

    }

    /**
     * Checks getVaticanReportFromCell method
     */
    @Test
    void getVaticanReportSectionFromCell(){
        VaticanReportSection vc= faithTrack.getVaticanReportSectionFromCell(faithTrack.getTrack().get(8));
        assertEquals(2, vc.getVictoryPoints());
        assertEquals(4, vc.getCells().size());
    }

    /**
     * Checks get track method
     */
    @Test
    void getTrack(){
        List<Cell> cells= faithTrack.getTrack();
        assertEquals(25, cells.size());
        PopeSpaceCell popeSpaceCell= new PopeSpaceCell(5,8);
        assertEquals(popeSpaceCell.getClass(), cells.get(8).getClass());
        FinalCell finalCell= new FinalCell(7,24);
        assertEquals(finalCell.getClass(), faithTrack.getTrack().get(24).getClass());
    }

}