package it.polimi.ingsw.networking.connection;

import it.polimi.ingsw.networking.messages.ErrorMessage;
import it.polimi.ingsw.networking.messages.ErrorType;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.JoinGameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NewGameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NicknameMessage;
import it.polimi.ingsw.networking.messages.serverMessage.ActionEndedMessage;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.client.view.CLI.CLIColors;
import it.polimi.ingsw.utils.exceptions.GameIsFullException;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * Handles the interactions from the Server to a single Client
 */
public class ServerToClientHandler implements Runnable {
    private String nickName;
    private GameController gameController;
    private final ConnectionIO connectionIO;
    private final Socket socket;

    /**
     * Class constructor
     * @param socket the socket
     * @throws IOException
     */
    public ServerToClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        connectionIO = new ConnectionIO(socket);
    }

    /**
     *
     * @return nickname
     */
    public String getNickName() {
        return nickName;
    }


    @Override
    public void run() {
        try {
            while (!(socket.isClosed())) {
                manageReceivedMessage(connectionIO.receiveMessage());
            }
        } catch (EOFException | SocketException e) {
            System.out.println(CLIColors.getAnsiRed() + "Client disconnected" + CLIColors.getAnsiReset());
            if (gameController != null) {
                gameController.removeConnectedClient(gameController.getInGameConnectedClients().stream()
                        .filter(inGameConnectedClient -> inGameConnectedClient.getNickName().equals(nickName))
                        .findFirst()
                        .orElse(null));
                gameController.sendBroadCastMessage(new ErrorMessage(ErrorType.PLAYER_DISCONNECTED));
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
     * @param message The message to be managed.
     */
    public void manageReceivedMessage(Message message) {

        if (gameController == null) {
            switch (message.getMessageType()) {

                case NICKNAME:
                    NicknameMessage nicknameMessage = (NicknameMessage) message;
                    if(Server.getConnectedClient().stream().noneMatch(x -> x.getNickName().equals(nicknameMessage.getNickname())) && !nicknameMessage.getNickname().equalsIgnoreCase("LORENZO")) {
                        nickName = nicknameMessage.getNickname();
                        Server.getConnectedClient().add(this);
                        sendMessage(new ActionEndedMessage());
                        System.out.println("Added new Connected Client: " + nickName);
                    }
                    else sendMessage(new ErrorMessage(ErrorType.NICKNAME_ALREADY_TAKEN));
                    break;

                case NEW_GAME:
                    System.out.println("Received newGame");
                    NewGameMessage newGameMessage = (NewGameMessage) message;
                    gameController = Server.newGame(newGameMessage.getPlayersNumber());
                    gameController.addConnectedClient(new InGameConnectedClient(nickName, connectionIO));
                    System.out.println(nickName + " created new Game. ID:" + gameController.getGameID() + " Players number:" + newGameMessage.getPlayersNumber());
                    break;

                case JOIN_GAME:
                    JoinGameMessage joinGameMessage = (JoinGameMessage) message;
                    try {
                        gameController = Server.findGame(joinGameMessage.getGameId());
                        gameController.addConnectedClient(new InGameConnectedClient(nickName, connectionIO));
                        System.out.println("Client " + nickName + " joined game: " + gameController.getGameID());
                    } catch (GameIsFullException e) {
                        sendMessage(new ErrorMessage(ErrorType.GAME_IS_FULL));

                    } catch (ClassNotFoundException e) {
                        sendMessage(new ErrorMessage(ErrorType.ID_NOT_EXISTS));
                    }
                    break;


            }
        } else gameController.manageReceivedMessage(message);

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
