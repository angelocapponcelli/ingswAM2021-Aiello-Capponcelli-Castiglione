package it.polimi.ingsw.server;

import it.polimi.ingsw.networking.ClientMessage.BeforeGameMessage.BeforeGameMessage;
import it.polimi.ingsw.networking.ClientMessage.ClientMessage;
import it.polimi.ingsw.networking.ClientMessage.BeforeGameMessage.PlayersNumberMessage;
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

    private SocketServer socketServer = new SocketServer(this, 16847);

    public static Game getOnGoingGame() {
        return onGoingGame;
    }

    public void start(){
        Thread thread = new Thread(socketServer, "socketserver_"); //socketServer si occupa di 'accogliere' le connessioni (.accept)
        thread.start();
    }

    public void onReceiveMessage(ClientMessage message) throws Exception {
        gameController.receiveMessage(message);
    }

    public void prepareGame(BeforeGameMessage beforeGameMessage) throws Exception {
        if (beforeGameMessage.check()) {
            //Create game controller, and the game with player number specified
            if (beforeGameMessage instanceof PlayersNumberMessage) {
                gameController = new GameController(0, ((PlayersNumberMessage) beforeGameMessage).getPlayerNumber());
            }
        } else {
            System.out.println("Throw exception"); //todo Se i check dei messaggi falliscono
        }
    }
}
