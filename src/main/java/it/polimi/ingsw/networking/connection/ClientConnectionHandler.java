package it.polimi.ingsw.networking.connection;

import it.polimi.ingsw.networking.messages.GenericTextMessage;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NewGameMessage;

import java.io.IOException;
import java.net.Socket;


/**
 * Handles the Input/Output 's stream from the Client's perspective.
 */
public class ClientConnectionHandler extends ConnectionHandler {


    public ClientConnectionHandler(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public void run() {
        String userInput;
        try {
            while (!((userInput = stdIn.readLine()).equals("quit"))) {
                if (userInput.equals("newGame")) {
                    sendMessage(new NewGameMessage());
                }
                sendMessage(new GenericTextMessage(userInput));
                manageReceivedMessages((Message) socketIn.readObject());

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void manageReceivedMessages(Message receivedMessage) throws IOException {
        switch (receivedMessage.getMessageType()) {
            case TEXT:
                GenericTextMessage genericTextMessage = (GenericTextMessage) receivedMessage;
                System.out.println(genericTextMessage.getText());
                break;
        }
    }


}

