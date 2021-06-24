package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.game.Game;
import it.polimi.ingsw.server.model.game.MultiplayerGame;
import it.polimi.ingsw.server.model.globalBoard.DevelopmentCardGrid;
import it.polimi.ingsw.server.model.globalBoard.GlobalBoard;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.player.Player;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.PlayerWithSameNameException;
import it.polimi.ingsw.utils.exceptions.ReachedMaxNumberOfPlayersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameAddPlayerTest {

    Game game;

    @BeforeEach
    void initializeGamePlayers() {
        game = new MultiplayerGame(4);

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
    void checkPlayersList() {
        List<String> playersName = new ArrayList<>(Arrays.asList("Vincent", "Jules", "Mia"));
        assertEquals(game.getPlayers().stream().map(Player::getNickName).collect(Collectors.toList()), playersName);
    }

    @Test
    void maxNumberOfPlayerReached() {
        try {
            game.addPlayer(new RealPlayer("Marcellus"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(ReachedMaxNumberOfPlayersException.class, () -> game.addPlayer(new RealPlayer("Butch")));
    }

    @Test
    void sameNameTest() {

        assertThrows(PlayerWithSameNameException.class, () -> game.addPlayer(new RealPlayer("Vincent")));

    }

    @Test
    void getPlayers(){
        List<Player> players= game.getPlayers();
        assertEquals(3, players.size());
        assertEquals("Jules", players.get(1).getNickName());
    }
    @Test
    void currentPlayer(){
        game.setCurrentPlayer(game.getPlayers().get(1));
        Player player= game.getCurrentPlayer();
        assertEquals("Jules", player.getNickName());
        game.setCurrentPlayer(game.getPlayers().get(2));
        player= game.getCurrentPlayer();
        assertEquals("Mia", player.getNickName());
    }

    @Test
    void getGlobalBoard(){
        GlobalBoard globalBoard= game.getGlobalBoard();
        DevelopmentCardGrid grid= globalBoard.getDevelopmentCardGrid();
        assertEquals(0,globalBoard.getFaithTrack().getTrack().get(0).getVictoryPoints());
        assertEquals(Colors.GREEN,grid.getDeck(0,0).getDeck().get(0).getTypeLevel().getType() );
    }

    @Test
    void winner(){
        assertEquals(null, game.getWinner());
        game.setWinner(game.getPlayers().get(2));
        assertEquals("Mia", game.getWinner().getNickName());
    }

}