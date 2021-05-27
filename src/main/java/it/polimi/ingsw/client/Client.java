package it.polimi.ingsw.client;

import it.polimi.ingsw.client.controller.ClientController;
import it.polimi.ingsw.client.view.CLI;
import it.polimi.ingsw.client.view.GUI;
import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.networking.connection.ConnectionIO;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.utils.CLIColors;


import java.io.IOException;
import java.net.Socket;

public class Client {
    private String nickName;

    private View view;

    private ConnectionIO connectionIO;

    private Socket clientSocket;

    private ClientController clientController;

    public Client(boolean GUI) {
        if(GUI){
            view = new GUI(this);
        }
        else {
            view = new CLI(this);
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
                view.start();
            } catch (IOException e) {
                System.out.println(CLIColors.getAnsiRed() + "Unable to connect to the server." + CLIColors.getAnsiReset());
            }
    }


    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    /**
     * Start a new Thread to handle the reception of messages
     */
    private void receiveMessage() {
        new Thread(() -> {
            while (true) {
                try {
                    Message receivedMessage = connectionIO.receiveMessage();
                    clientController.manageReceivedMessage(receivedMessage);
                } catch (IOException | ClassNotFoundException e) {
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
    public void sendMessage(Message message){
        try {
            connectionIO.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}