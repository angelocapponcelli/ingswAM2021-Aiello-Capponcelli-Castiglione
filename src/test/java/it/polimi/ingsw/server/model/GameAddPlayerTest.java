package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.exceptions.PlayerWithSameNameException;
import it.polimi.ingsw.server.model.exceptions.ReachedMaxNumberOfPlayersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GameAddPlayerTest {

    Game game;

    @BeforeEach
    void initializeGamePlayers(){
        game = new MultiplayerGame(1,4);

        Player player1 = new RealPlayer("Vincent");
        Player player2 = new RealPlayer("Jules");
        Player player3 = new RealPlayer("Mia");
        try {
            game.addPlayer(player1);
            game.addPlayer(player2);
            game.addPlayer(player3);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    void checkPlayersList(){
        List<String> playersName = new ArrayList<>(Arrays.asList("Vincent","Jules","Mia"));
        assertEquals(game.getPlayers().stream().map(Player::getNickName).collect(Collectors.toList()), playersName);
    }

    @Test
    void maxNumberOfPlayerReached(){
        try {
            game.addPlayer(new RealPlayer("Marcellus"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(ReachedMaxNumberOfPlayersException.class, () -> game.addPlayer(new RealPlayer("Butch")));
    }
    @Test
    void sameNameTest(){

        assertThrows(PlayerWithSameNameException.class, () -> game.addPlayer(new RealPlayer("Vincent")));

    }

}