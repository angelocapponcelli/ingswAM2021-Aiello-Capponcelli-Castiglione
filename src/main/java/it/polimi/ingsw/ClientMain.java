package it.polimi.ingsw;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.networking.ClientMessage.InsertNicknameMessage;
import it.polimi.ingsw.networking.ClientMessage.BeforeGameMessage.PlayersNumberMessage;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) {
        Client c = null;
        try {
            c = new Client("127.0.0.1", 16847);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Invio il numero di giocatori: 3");
        c.sendMessage(new PlayersNumberMessage(1));
        System.out.println("Invio il primo nickname");
        c.sendMessage(new InsertNicknameMessage("Player 1"));
        System.out.println("Invio il secondo nickname");
        c.sendMessage(new InsertNicknameMessage("Player 2"));
        System.out.println("Invio il terzo nickname");
        c.sendMessage(new InsertNicknameMessage("Player 3"));
        try {
            System.in.read(); //to close
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

