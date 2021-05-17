package it.polimi.ingsw.networking.connection;

import it.polimi.ingsw.networking.messages.GenericTextMessage;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.JoinGameMessage;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.utils.CLIColors;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Locale;

/**
 * Handles the Input/Output 's stream from the Server's perspective.
 */
public class ServerConnectionHandler extends ConnectionHandler {

    private GameController gameController;

    public ServerConnectionHandler(Socket socket) throws IOException {
        super(socket);
    }

    public GameController getGameController() {
        return gameController;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Message receivedMessage = null;
                while (!(socket.isClosed())) {
                    manageReceivedMessages((Message) socketIn.readObject());
                }
            }
        } catch (EOFException e) {
            System.out.println(CLIColors.getAnsiRed() + "Client disconnected" + CLIColors.getAnsiReset());
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
        switch (receivedMessage.getMessageType()) {
            case TEXT:
                GenericTextMessage genericTextMessage = (GenericTextMessage) receivedMessage;
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
