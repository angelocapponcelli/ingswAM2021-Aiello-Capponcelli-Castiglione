package it.polimi.ingsw.utils.observer;

import it.polimi.ingsw.networking.messages.Message;

public interface Observer {
    void update(Message message);
}