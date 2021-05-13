package it.polimi.ingsw.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * This class represents a socket client implementation.
 */
public class Client {

    private final Socket socket;

    private final ObjectOutputStream outputStm;

    private static final int SOCKET_TIMEOUT = 10000;

    public Client(String address, int port) throws IOException {
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(address, port), SOCKET_TIMEOUT);
        this.outputStm = new ObjectOutputStream(socket.getOutputStream());
    }

    public void sendMessage(Serializable message) {
        try {
            outputStm.writeObject(message);
            outputStm.reset();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}