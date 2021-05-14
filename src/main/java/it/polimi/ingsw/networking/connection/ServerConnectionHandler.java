package it.polimi.ingsw.networking.connection;

import it.polimi.ingsw.networking.messages.*;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.JoinGameMessage;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.controller.GameController;

import java.io.IOException;
import java.net.Socket;
import java.util.Locale;

/**
 * Handles the Input/Output 's stream from the Server's perspective.
 */
public class ServerConnectionHandler extends ConnectionHandler {

    public GameController getGameController() {
        return gameController;
    }

    private GameController gameController;

    public ServerConnectionHandler(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public void run() {

        try {
            while (true) {
                Message receivedMessage = null;
                while (!(socket.isClosed())) {
                    manageReceivedMessages((Message)socketIn.readObject());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            socketIn.close();
            socketOut.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void manageReceivedMessages(Message receivedMessage) throws IOException {
        switch (receivedMessage.getMessageType()){
            case TEXT:
                GenericTextMessage genericTextMessage = (GenericTextMessage)receivedMessage;
                System.out.println("Server received \"" + genericTextMessage.getText() + "\"");
                sendMessage(new GenericTextMessage(genericTextMessage.getText().toUpperCase(Locale.ROOT)));
                break;
            case NEW_GAME:
                gameController = Server.newGame();
                break;
            case JOIN_GAME:
                JoinGameMessage joinGameMessage = (JoinGameMessage) receivedMessage;
                gameController = Server.findGame(joinGameMessage.getGameId());
                break;
        }
    }



}
