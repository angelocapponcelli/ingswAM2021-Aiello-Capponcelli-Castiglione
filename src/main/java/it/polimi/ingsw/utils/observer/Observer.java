package it.polimi.ingsw.utils.observer;

import it.polimi.ingsw.networking.messages.Message;

import java.io.IOException;

public interface Observer {
    void update(Message message);
}