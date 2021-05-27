package it.polimi.ingsw.networking.connection;

import it.polimi.ingsw.networking.messages.Message;

import java.io.*;
import java.net.Socket;

/**
 * Handles the Input/Output's streams
 */
public class ConnectionIO{
    protected final Socket socket;
    protected final ObjectInputStream socketIn;
    protected final ObjectOutputStream socketOut;
    protected BufferedReader stdIn;

    public ConnectionIO(Socket socket) throws IOException {
        this.socket = socket;
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        socketOut = new ObjectOutputStream(socket.getOutputStream());
        socketIn = new ObjectInputStream(socket.getInputStream());
    }


    public void sendMessage(Message message) throws IOException {
        socketOut.writeObject(message);
    }

    public Message receiveMessage() throws IOException, ClassNotFoundException {
        return (Message) socketIn.readObject();
    }

}