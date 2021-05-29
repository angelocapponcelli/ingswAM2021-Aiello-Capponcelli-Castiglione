package it.polimi.ingsw.server.controller.state;

import it.polimi.ingsw.networking.messages.Message;

public class LoginState extends State{

    @Override
    public void enter() {
        System.out.println("wait for players");
    }

    @Override
    public void update(Message message) {

    }
}
