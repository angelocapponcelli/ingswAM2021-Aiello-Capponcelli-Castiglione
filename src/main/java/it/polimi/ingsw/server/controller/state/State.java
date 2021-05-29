package it.polimi.ingsw.server.controller.state;

import it.polimi.ingsw.networking.messages.Message;

public abstract class State {

    static State Login,Init,In_game;

    public abstract void enter();
    public abstract void update(Message message);
}
