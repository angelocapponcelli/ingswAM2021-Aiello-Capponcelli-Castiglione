package it.polimi.ingsw;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.client.view.CLI.CLIColors;

public class MaestriDelRinascimento {

    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int portNumber = 1234;


        if (args[0].equals("--client")) {
            Client client;
            if (args.length == 2) {
                if (args[1].equals("cli")) {
                    System.out.println("Starting Client in CLI mode...");
                    client = new Client(false);
                    client.start(hostName, portNumber);
                }
                else if (args[1].equals("gui")) {
                    System.out.println("Starting Client in GUI mode...");
                    client = new Client(true);
                    client.start(hostName,portNumber);

                }
            }

        } else if (args[0].equals("--server")) {
            System.out.println("Starting server on port " + portNumber);
            Server.start(portNumber);
        } else {
            System.out.println(CLIColors.getAnsiRed() + "INVALID ARGUMENTS" + CLIColors.getAnsiReset());
            System.out.println("[--client] to start client(default in gui mode)\n" +
                    "\t[cli] to start client in CLI mode\n" +
                    "\t[gui] to start client in GUI mode");
            System.out.println("[--server] to start server");

        }


    }
}