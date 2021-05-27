package it.polimi.ingsw.networking.connection;

import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.utils.observer.Observer;

import java.io.IOException;

public class InGameConnectedClient implements Observer {
    private final String nickName;
    private final ConnectionIO connectionIO;


    public InGameConnectedClient(String nickName, ConnectionIO connectionIO){
        this.nickName = nickName;
        this.connectionIO = connectionIO;
    }

    public String getNickName() {
        return nickName;
    }

    public ConnectionIO getConnectionIO() {
        return connectionIO;
    }


    @Override
    public void update(Message message){
        try {
            connectionIO.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
