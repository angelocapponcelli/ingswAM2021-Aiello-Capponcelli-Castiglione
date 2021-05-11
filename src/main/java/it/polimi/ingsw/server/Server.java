package it.polimi.ingsw.server;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.Game;
/**
 * Draft of the server class
 */
public class Server {

    //private final ServerSocket serverSocket;

    //private static List<Game> onGoingGames;
    private static Game onGoingGame;

    private GameController gameController;

    public static Game getOnGoingGame() {
        return onGoingGame;
    }

}
