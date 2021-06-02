package it.polimi.ingsw.client;

import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.GUI;
import it.polimi.ingsw.client.view.SimpleCLI;
import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.networking.connection.ConnectionIO;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.utils.CLIColors;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private final View view;
    private final ClientController clientController;
    private String nickName;
    private int playerPosition;
    private ConnectionIO connectionIO;
    private Socket clientSocket;

    public Client(boolean GUI) {

        if (GUI) {
            view = new GUI(this);
        } else {
            view = new SimpleCLI(this);
        }
        clientController = new ClientController(view);
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public View getView() {
        return view;
    }

    public ConnectionIO getConnectionIO() {
        return connectionIO;
    }

    public ClientController getClientController() {
        return clientController;
    }


    public void start(String hostName, int portNumber) {
        try {
            clientSocket = new Socket(hostName, portNumber);
            System.out.println("Client connected!");
            connectionIO = new ConnectionIO(clientSocket);
            receiveMessage();
            clientController.run();
            //view.start();
        } catch (IOException e) {
            System.out.println(CLIColors.getAnsiRed() + "Unable to connect to the server." + CLIColors.getAnsiReset());
        }
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Start a new Thread to handle the reception of messages
     */
    private void receiveMessage() {
        new Thread(() -> {
            while (true) {
                try {
                    clientController.manageReceivedMessage(connectionIO.receiveMessage());
                } catch (IOException | ClassNotFoundException e) {
                    //e.printStackTrace();
                    System.out.println("Server closed!");
                    System.exit(-1);
                }
            }
        }
        ).start();
    }


    /**
     * Sent a message to the server
     *
     * @param message the message to be sent
     */
    public void sendMessage(Message message) {
        try {
            connectionIO.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}