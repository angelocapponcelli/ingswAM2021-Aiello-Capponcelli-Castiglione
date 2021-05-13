package it.polimi.ingsw.server;

import it.polimi.ingsw.networking.ClientMessage.BeforeGameMessage.BeforeGameMessage;
import it.polimi.ingsw.networking.ClientMessage.ClientMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket server that handles all the new socket connection.
 */
public class SocketServer implements Runnable {
    private final Server server;
    private final int port;
    ServerSocket serverSocket;

    public SocketServer(Server server, int port) {
        this.server = server;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Socket server started on port " + port + ".");
        } catch (IOException e) {
            System.err.println(e.getMessage()); //in caso di errori, porta non disponibile
            return;
        }

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket client = serverSocket.accept();

               // client.setSoTimeout(5000);

                SocketClientHandler clientHandler = new SocketClientHandler(this, client);
                Thread thread = new Thread(clientHandler, "ss_handler" + client.getInetAddress());
                thread.start();
            } catch (IOException e) {
                System.err.println(e.getMessage()); // Entrerei qui se serverSocket venisse chiuso
            }
        }
    }

    public void prepareGame(BeforeGameMessage message) throws Exception {
        server.prepareGame(message);
    }

    public void onReceiveMessage(ClientMessage message) throws Exception {
        server.onReceiveMessage(message);
    }
}
