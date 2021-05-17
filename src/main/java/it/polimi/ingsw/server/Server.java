package it.polimi.ingsw.server;

import it.polimi.ingsw.networking.connection.ServerConnectionHandler;
import it.polimi.ingsw.server.controller.GameController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    /**
     * Games that are currently on
     */
    private static final List<GameController> onGoingGames = new ArrayList<>();

    /**
     * Starts the Server creating a pool of threads and waiting for the clients to connect
     *
     * @param portNumber the port on which the server will listen
     */
    public static void start(int portNumber) {

        ExecutorService executor = Executors.newCachedThreadPool();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server started!");

        while (true) {
            try {
                System.out.println("Accepting...");
                Socket clientSocket = serverSocket.accept();
                executor.submit(new ServerConnectionHandler(clientSocket));
                System.out.println("Client Accepted");
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        executor.shutdown();

    }

    /**
     * Create a new game.
     *
     * @return the new game.
     */
    public static GameController newGame() {
        GameController gameController = new GameController();
        onGoingGames.add(gameController);
        return gameController;

    }

    /**
     * Find a game by its ID number.
     *
     * @param gameID The ID of the requested game.
     * @return the requested game.
     */
    public static GameController findGame(int gameID) {
        return onGoingGames.stream().filter(x -> x.getGameID() == gameID).findFirst().orElseThrow(NoSuchElementException::new);
    }


}


