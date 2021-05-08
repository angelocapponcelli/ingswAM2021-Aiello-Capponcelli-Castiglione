package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FaithTrackTest {

    @Test
    void checkParsing() {
        assertEquals(FaithTrack.getINSTANCE().getTrack().get(0).getVictoryPoints(), 0);
        assertEquals(FaithTrack.getINSTANCE().getTrack().get(1).getVictoryPoints(), 0);
        assertEquals(FaithTrack.getINSTANCE().getTrack().get(2).getVictoryPoints(), 0);
        assertEquals(FaithTrack.getINSTANCE().getTrack().get(3).getVictoryPoints(), 1);
        assertEquals(FaithTrack.getINSTANCE().getTrack().get(4).getVictoryPoints(), 1);
        assertEquals(FaithTrack.getINSTANCE().getTrack().get(5).getVictoryPoints(), 1);
        assertEquals(FaithTrack.getINSTANCE().getTrack().get(6).getVictoryPoints(), 2);

        assertEquals(FaithTrack.getINSTANCE().getTrack().get(8).getVictoryPoints(), 2);
        assertEquals(FaithTrack.getINSTANCE().getTrack().get(8).getClass().getSimpleName(), "PopeSpaceCell");

        assertEquals(FaithTrack.getINSTANCE().getTrack().get(16).getVictoryPoints(), 9);
        assertEquals(FaithTrack.getINSTANCE().getTrack().get(16).getClass().getSimpleName(), "PopeSpaceCell");

        assertEquals(FaithTrack.getINSTANCE().getTrack().get(24).getVictoryPoints(), 20);
        assertEquals(FaithTrack.getINSTANCE().getTrack().get(24).getClass().getSimpleName(), "FinalCell");

    }

}