package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FaithTrackTest {
    private FaithTrack faithTrack;

    @BeforeEach
    void init() {
        try {
            faithTrack = new FaithTrack();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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

}