package it.polimi.ingsw.client;

import it.polimi.ingsw.networking.connection.ClientConnectionHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {

    private Socket clientSocket;

    public Socket getClientSocket() {
        return clientSocket;
    }




    public void start(String hostName, int portNumber){

        boolean connected = false;
        while (!connected) {
            try {
                clientSocket = new Socket(hostName, portNumber);
                System.out.println("Client connected!");
                new ClientConnectionHandler(clientSocket).run();
                connected = true;
            } catch (IOException e) {
                System.out.println("Waiting for the Server");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }
}