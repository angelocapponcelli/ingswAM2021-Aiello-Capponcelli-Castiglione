package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.game.Game;
import it.polimi.ingsw.server.model.game.MultiplayerGame;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.PopeSpaceCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PopeSpaceCellTest {

    PopeSpaceCell popeSpaceCell;
    Game game;
    int victoryPoints;

    @BeforeEach
    void setPopeSpaceCellInitialization() {
        Game game = new MultiplayerGame(4);
        Random r = new Random();
        victoryPoints = r.nextInt(20);
        popeSpaceCell = new PopeSpaceCell(victoryPoints,8);
    }

    @Test
    void getVictoryPointsTest() {
        Random random= new Random();
        int victory= random.nextInt(15);
        PopeSpaceCell popeSpaceCell1= new PopeSpaceCell(victory,8);
        assertEquals(victoryPoints, popeSpaceCell.getVictoryPoints());
        assertEquals(victory, popeSpaceCell1.getVictoryPoints());

        victory=random.nextInt(35);
        PopeSpaceCell popeSpaceCell2= new PopeSpaceCell(victory,8);
        assertEquals(victory,popeSpaceCell2.getVictoryPoints());

    }

    @Test
    void occupy(){
        assertEquals(false, popeSpaceCell.getAlreadyOccupied());

    }




}