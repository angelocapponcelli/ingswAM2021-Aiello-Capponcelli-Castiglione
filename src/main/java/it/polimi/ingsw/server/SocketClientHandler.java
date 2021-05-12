package it.polimi.ingsw.server;

import it.polimi.ingsw.networking.BeforeGameMessage;
import it.polimi.ingsw.networking.ClientMessage;
import it.polimi.ingsw.networking.InsertNicknameMessage;
import it.polimi.ingsw.networking.PlayersNumberMessage;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.server.model.exceptions.ReachedMaxNumberOfPlayersException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientHandler implements Runnable {
    private final Socket client;
    private final SocketServer socketServer;

    private boolean connected;

    private final Object inputLock;
    private final Object outputLock;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    /**
     * Default constructor.
     *
     * @param socketServer the socket of the server.
     * @param client       the client connecting.
     */
    public SocketClientHandler(SocketServer socketServer, Socket client) {
        this.socketServer = socketServer;
        this.client = client;
        this.connected = true;

        this.inputLock = new Object();
        this.outputLock = new Object();

        try {
            this.output = new ObjectOutputStream(client.getOutputStream());
            this.input = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Client connected from " + client.getInetAddress());

            try {
                while (!Thread.currentThread().isInterrupted()) {
                    //synchronized (inputLock) { da sincronizzare
                    //todo: da perfezionare, prima release per provare le interazioni in rete
                    Object message = input.readObject();
                    if (message != null) {
                        if (message instanceof BeforeGameMessage) { //Se è un messaggio BeforeGameMessage
                            try {
                                socketServer.prepareGame((BeforeGameMessage) message);
                            } catch (Exception e) {
                                sendErrorMessage(e);
                            }
                        } else if (message instanceof ClientMessage) { //Se è un messaggio ClientMessage
                                socketServer.onReceiveMessage((ClientMessage) message);
                        }
                    }
                }
            } catch (ClassCastException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
            client.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            //  disconnect(); //todo disconnessione
        }
    }

    private void sendErrorMessage(Exception e) {
        //todo Send different error message to the client, depend of exception
        System.out.println("Error " + e.getClass());
    }
}