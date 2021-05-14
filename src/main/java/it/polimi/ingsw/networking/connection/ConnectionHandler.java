package it.polimi.ingsw.networking.connection;

import it.polimi.ingsw.networking.messages.Message;

import java.io.*;
import java.net.Socket;

/**
 * Handles the Input/Output's streams
 */
public abstract class ConnectionHandler implements Runnable {
    protected final Socket socket;
    protected final ObjectInputStream socketIn;
    protected final ObjectOutputStream socketOut;
    protected BufferedReader stdIn;

    public ConnectionHandler(Socket socket) throws IOException {
        this.socket = socket;
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        socketOut = new ObjectOutputStream(socket.getOutputStream());
        socketIn = new ObjectInputStream(socket.getInputStream());
    }

    protected abstract void manageReceivedMessages(Message receivedMessage) throws IOException;

    public void sendMessage(Message message) throws IOException {
        socketOut.writeObject(message);
    }

}
