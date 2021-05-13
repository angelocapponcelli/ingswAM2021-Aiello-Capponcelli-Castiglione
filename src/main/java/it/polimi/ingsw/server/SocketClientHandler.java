package it.polimi.ingsw.server;

import it.polimi.ingsw.networking.ClientMessage.BeforeGameMessage.BeforeGameMessage;
import it.polimi.ingsw.networking.ClientMessage.ClientMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketClientHandler implements Runnable {
    private final Socket client;
    private final SocketServer socketServer;
    private ObjectInputStream input;

    public SocketClientHandler(SocketServer socketServer, Socket client) {
        this.socketServer = socketServer;
        this.client = client;

        try {
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
                            try {
                                socketServer.onReceiveMessage((ClientMessage) message);
                            } catch (Exception e) {
                                sendErrorMessage(e);
                            }
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