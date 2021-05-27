package it.polimi.ingsw.networking.connection;

import it.polimi.ingsw.networking.messages.ErrorMessage;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NicknameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.JoinGameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NewGameMessage;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.utils.CLIColors;
import it.polimi.ingsw.utils.exceptions.GameIsFullException;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * Handles the interactions from the Server to a single Client
 */
public class ServerClientHandler implements Runnable{
    private String nickName;
    private GameController gameController;
    private ConnectionIO connectionIO;
    private Socket socket;

    public ServerClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        connectionIO = new ConnectionIO(socket);
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public void run() {
        try {
            while (!(socket.isClosed())) {
                manageReceivedMessage(connectionIO.receiveMessage());
            }
        } catch (EOFException |SocketException e) {
            System.out.println(CLIColors.getAnsiRed() + "Client disconnected" + CLIColors.getAnsiReset());
            if (gameController != null) {
                gameController.removeConnectedClient(gameController.getInGameConnectedClients().stream()
                        .filter(inGameConnectedClient -> inGameConnectedClient.getNickName().equals(nickName))
                        .findFirst()
                        .orElse(null));
            }
            System.out.println(CLIColors.getAnsiRed() + "Client Removed" + CLIColors.getAnsiReset());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Manages the received messages by the Server.
     * If the received message is a "Pre-Game" then it will managed directly
     * else it will be managed by the game controller.
     *
     * @param receivedMessage The message to be managed.

     */
    public void manageReceivedMessage(Message receivedMessage) {


        if (gameController == null) {
            switch (receivedMessage.getMessageType()) {
                /*case TEXT:
                    ClientText clientText = (ClientText) receivedMessage;
                    System.out.println("Server received \"" + clientText.getText() + "\"");
                    sendMessage(new ServerText(clientText.getText().toUpperCase(Locale.ROOT)));
                    break;*/

                case NICKNAME:
                    NicknameMessage nicknameMessage = (NicknameMessage) receivedMessage;
                    nickName = nicknameMessage.getNickname();
                    Server.getConnectedClient().add(this);
                    System.out.println("Added new Connected Client: " + nickName );
                    break;

                case NEW_GAME:
                    System.out.println("Received newGame");
                    NewGameMessage newGameMessage = (NewGameMessage) receivedMessage;
                    gameController = Server.newGame(newGameMessage.getPlayersNumber());
                    gameController.addConnectedClient(new InGameConnectedClient(nickName, connectionIO));
                    System.out.println(nickName + " created new Game. ID:" + gameController.getGameID() + " Players number:" + newGameMessage.getPlayersNumber());
                    break;

                case JOIN_GAME:
                    JoinGameMessage joinGameMessage = (JoinGameMessage) receivedMessage;
                    try {
                        gameController = Server.findGame(joinGameMessage.getGameId());
                        gameController.addConnectedClient(new InGameConnectedClient(nickName, connectionIO));
                        System.out.println("Client " + nickName + " joined game: " + gameController.getGameID());
                    } catch (GameIsFullException e) {
                        sendMessage(new ErrorMessage("This game is full"));
                    } catch (ClassNotFoundException e) {
                        sendMessage(new ErrorMessage("The Specified Game does not exists"));
                    }
                    break;


            }
        } else gameController.manageReceivedMessage(receivedMessage);

    }

    /**
     * Sent a message to the single client.
     *
     * @param message The message to be sent.
     */
    public void sendMessage(Message message) {
        try {
            connectionIO.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
