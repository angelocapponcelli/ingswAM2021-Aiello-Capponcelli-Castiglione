package it.polimi.ingsw;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.CLI;
import it.polimi.ingsw.client.view.GUI;
import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.utils.CLIColors;

public class MaestriDelRinascimento {

    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int portNumber = 1234;

        if (args[0].equals("client")) {
            View view = new GUI();
            if (args.length == 2) {
                if (args[1].equals("cli")) {
                    System.out.println("Starting Client in CLI mode...");
                    view = new CLI();
                }
                if (args[1].equals("gui")) {
                    System.out.println("Starting Client in GUI mode...");
                    view = new GUI();
                }
            }
            Client client = new Client(view);
            client.start(hostName, portNumber);

        } else if (args[0].equals("server")) {
            System.out.println("Starting server on port " + portNumber);
            Server.start(portNumber);
        } else {
            System.out.println(CLIColors.getAnsiRed() + "INVALID ARGUMENTS" + CLIColors.getAnsiReset());
        }


    }
}