package it.polimi.ingsw.server;

import it.polimi.ingsw.networking.connection.ServerToClientHandler;
import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.utils.exceptions.GameIsFullException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    private static final List<GameController> onGoingGames = new ArrayList<>();
    private static final List<ServerToClientHandler> connectedClient = new ArrayList<>();
    private static Integer nextGameID = 1;


    /**
     * Starts the Server by creating a pool of threads and waiting for the clients to connect
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
                Socket clientSocket = Objects.requireNonNull(serverSocket).accept();
                executor.submit(new ServerToClientHandler(clientSocket));
                System.out.println("Client Accepted");
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        executor.shutdown();
    }


    /**
     * @return the list of connected clients
     */
    public static List<ServerToClientHandler> getConnectedClient() {
        return connectedClient;
    }


    /**
     * @return The games that are currently on.
     */
    public static List<GameController> getOnGoingGames() {
        return onGoingGames;
    }


    /**
     * Create a new game.
     *
     * @return the new game.
     */
    public static GameController newGame(Integer maxPlayersNumber) {
        synchronized (onGoingGames) {
            GameController gameController = new GameController(maxPlayersNumber, nextGameID);
            onGoingGames.add(gameController);
            nextGameID++;
            return gameController;
        }
    }


    /**
     * Find a game by its ID.
     *
     * @param gameID The ID of the requested game.
     * @return the requested game.
     * @throws GameIsFullException when the game is full
     */
    public static GameController findGame(int gameID) throws GameIsFullException, ClassNotFoundException {
        synchronized (onGoingGames) {
            GameController found = onGoingGames.stream().filter(x -> x.getGameID() == gameID).findFirst().orElseThrow(ClassNotFoundException::new);
            if (found.getInGameConnectedClients().size() == found.getMaxPlayersNumber()) {
                throw new GameIsFullException();
            } else {
                return found;

            }
        }
    }

}


