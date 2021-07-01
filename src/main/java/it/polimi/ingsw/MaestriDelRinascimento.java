package it.polimi.ingsw;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.client.view.CLI.CLIColors;
import it.polimi.ingsw.editor.Editor;
import it.polimi.ingsw.utils.parsers.SettingsParser;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MaestriDelRinascimento {


    private static final AtomicBoolean GUI = new AtomicBoolean(true);
    private static final AtomicReference<String> hostName = new AtomicReference<>("127.0.0.1");
    private static final AtomicInteger portNumber = new AtomicInteger(1234);
    private static final AtomicReference<String> settings = new AtomicReference<>("src/main/resources/JSONs/settings.json");
    public static void main(String[] args) {

        switch (args[0]){
            case "--client":
                    Arrays.stream(args).filter(arg -> arg.equals("--cli") || arg.equals("-c")).findFirst().ifPresent(x -> GUI.set(false));
                    parsePort(args);
                    Arrays.stream(args).filter(arg -> arg.contains("--address=") || arg.contains("-a=")).findFirst().ifPresent(x -> hostName.set(x.substring(x.indexOf("=") + 1))
                    );
                    new Client(GUI.get()).start(hostName.get(), portNumber.get());
                break;

            case "--server":
                Arrays.stream(args).filter(arg -> arg.contains("--settings=") || arg.contains("-s=")).findFirst().ifPresent(x-> {
                            System.out.println(x.substring(x.indexOf("=") + 1));
                            settings.set(x.substring(x.indexOf("=") + 1));
                        }
                );
                parsePort(args);
                SettingsParser.getInstance().setJson(settings.get());
                Server.start(portNumber.get());
                break;
            case "--editor":
                new Thread(Editor::main).start();
                break;
            default:
                System.out.println(CLIColors.getAnsiRed() + "INVALID ARGUMENTS" + CLIColors.getAnsiReset());
                System.out.println("[--client]: Start client in GUI mode" +
                        "    \n[-c, --cli]: Start client in CLI mode");
                System.out.println(CLIColors.ANSI_BRIGHT_BLACK +
                        "[-a=, --address=]: Specify the address"+
                        "\n[-p=, --port=]: Specify the port"+
                        CLIColors.ANSI_RESET);

                System.out.println("\n[--server]: Start server");
                System.out.println(CLIColors.ANSI_BRIGHT_BLACK +
                        "[-s=, --settings=]: Specify the path of a custom settings.json file" +
                        "\n[-p=, --port=]: Specify the port on which the server will listen on" +
                        CLIColors.ANSI_RESET);
                System.out.println("\n[--editor]: Start the parameters' editor");


        }

    }

    private static void parsePort(String[] args){
        Arrays.stream(args).filter(arg -> arg.contains("--port=") || arg.contains("--p=")).findFirst().ifPresent(x-> portNumber.set( Integer.parseInt( x.substring( x.indexOf('=') + 1 ) )  )
        );
    }
}