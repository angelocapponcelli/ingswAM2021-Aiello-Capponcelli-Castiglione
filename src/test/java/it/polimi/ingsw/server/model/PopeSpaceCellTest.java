package it.polimi.ingsw.server.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import it.polimi.ingsw.server.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class PopeSpaceCellTest {

    PopeSpaceCell popeSpaceCell;
    Game game;
    int victoryPoints;

    @BeforeEach
    void setPopeSpaceCellInitialization(){
        Game game = new MultiplayerGame(1,4);
        Random r = new Random();
        victoryPoints = r.nextInt(20);
        popeSpaceCell = new PopeSpaceCell(victoryPoints);
    }

    @Test
    void getVictoryPointsTest(){
        assertEquals(victoryPoints, popeSpaceCell.getVictoryPoints());
    }








}