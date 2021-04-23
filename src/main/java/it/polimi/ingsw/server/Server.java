package it.polimi.ingsw.server;

import it.polimi.ingsw.server.model.Game;

/**
 * Draft of the server class
 */
public class Server {
    //private static List<Game> onGoingGames;
    private static Game onGoingGame;

    public static Game getOnGoingGame() {
        return onGoingGame;
    }


}
