package it.polimi.ingsw.client;

import it.polimi.ingsw.networking.ClientMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * This class represents a socket client implementation.
 */
public class Client {

    private final Socket socket;

    private final ObjectOutputStream outputStm;
    private final ObjectInputStream inputStm;
    private final ExecutorService readExecutionQueue;

    private static final int SOCKET_TIMEOUT = 10000;

    public Client(String address, int port) throws IOException {
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(address, port), SOCKET_TIMEOUT);
        this.outputStm = new ObjectOutputStream(socket.getOutputStream());
        this.inputStm = new ObjectInputStream(socket.getInputStream());
        this.readExecutionQueue = Executors.newSingleThreadExecutor();
    }

    /**
     * Asynchronously reads a message from the server via socket and notifies the ClientController.
     */
    public void readMessage() {
        readExecutionQueue.execute(() -> {

            while (!readExecutionQueue.isShutdown()) {
                ClientMessage message;
                try {
                    message = (ClientMessage) inputStm.readObject();
                    System.out.println("Received: " + message);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
    }

    /**
     * Sends a message to the server via socket.
     *
     * @param message the message to be sent.
     */
    public void sendMessage(Serializable message) {
        try {
            outputStm.writeObject(message);
            outputStm.reset();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}