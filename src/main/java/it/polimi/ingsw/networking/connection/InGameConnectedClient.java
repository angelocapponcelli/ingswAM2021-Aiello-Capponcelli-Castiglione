package it.polimi.ingsw.networking.connection;

import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.utils.observer.Observer;

import java.io.IOException;

/**
 * In game connected client
 */
public class InGameConnectedClient implements Observer {
    private final String nickName;
    private final ConnectionIO connectionIO;

    /**
     * Class constructor
     * @param nickName the nickname of the user
     * @param connectionIO the connection of the user
     */
    public InGameConnectedClient(String nickName, ConnectionIO connectionIO) {
        this.nickName = nickName;
        this.connectionIO = connectionIO;
    }

    /**
     *
     * @return nickname
     */
    public String getNickName() {
        return nickName;
    }

    /**
     *
     * @return connection
     */
    public ConnectionIO getConnectionIO() {
        return connectionIO;
    }

    /**
     * Updates the client
     * @param message
     */
    @Override
    public void update(Message message) {
            connectionIO.sendMessage(message);
    }
}
