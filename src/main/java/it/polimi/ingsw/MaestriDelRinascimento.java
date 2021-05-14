package it.polimi.ingsw;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.Server;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Option;


@Command(name = "Maestri del Rinascimento", version = "Maestri del Rinascimento 1.0", mixinStandardHelpOptions = true)
public class MaestriDelRinascimento implements Runnable {

    static class ClientArgs {
        @Option(names = "--client", required = true, description = "Start Client in GUI mode")
        boolean clientMode;

        @Option(names = {"-c", "--cli"}, description = "Start Client in CLI mode")
        boolean cli;

        @Option(names = {"--ip"}, description = "Server's host name")
        String hostName = "127.0.0.1";
    }

    static class ServerArgs {
        @Option(names = "--server", required = true, description = "Start Server")
        boolean serverMode;
    }

    static class Args {
        @ArgGroup(exclusive = false, multiplicity = "1", heading = "CLIENT mode args%n")
        ClientArgs clientArgs;

        @ArgGroup(exclusive = false, multiplicity = "1", heading = "SERVER mode args%n")
        ServerArgs serverArgs;
    }


    @Option(names = {"-p", "--port"}, description = "portNumber")
    int portNumber = 1234;

    @ArgGroup(exclusive = true, multiplicity = "1")
    Args args;


    @Override
    public void run() {
        if (args.clientArgs != null) {
            System.out.printf("Starting Client ");
            if ((args.clientArgs.cli)) {
                System.out.println("in CLI mode...");
            } else {
                System.out.println("in GUI mode...");
            }
            Client client = new Client();
            client.start(args.clientArgs.hostName, portNumber);



        } else {
            System.out.println("Starting server on port " + portNumber);
            Server.start(portNumber);
        }
    }

    public static void main(String[] args) {
        new CommandLine(new MaestriDelRinascimento()).execute(args);
    }
}