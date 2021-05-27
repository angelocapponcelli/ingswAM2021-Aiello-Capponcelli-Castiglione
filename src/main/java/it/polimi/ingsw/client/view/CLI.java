package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedGameModel;
import it.polimi.ingsw.networking.messages.clientMessages.ClientText;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NicknameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.TakeFromMarketMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.JoinGameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NewGameMessage;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.CLIColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class CLI extends View {

    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    public CLI(Client client) {
        super(client);
    }

    @Override
    public void start() {

        clear();
        System.out.println("Welcome to Maestri del Rinascimento");

        waitMilliseconds(1000);
        clear();


        new Thread( () ->{
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            Message message;
            try {
                AskForNickName();
                while (!((userInput = stdIn.readLine()).equals("quit"))) {
                    switch (userInput) {
                        case "newGame": {
                            System.out.println("Insert players number");
                            String tmp = stdIn.readLine();
                            message = new NewGameMessage(Integer.parseInt(tmp));
                            clear();
                            break;
                        }
                        case "selectRow":
                            System.out.println("row(r) or column");
                            String rowOrColumn = stdIn.readLine();
                            System.out.println("Number?");
                            String number = stdIn.readLine();
                            message = new TakeFromMarketMessage(client.getNickName(), rowOrColumn, Integer.parseInt(number));
                            break;
                        case "joinGame": {
                            System.out.println("Insert GameID");
                            String tmp = stdIn.readLine();
                            message = new JoinGameMessage(Integer.parseInt(tmp));
                            clear();
                            break;
                        }
                        default:
                            message = new ClientText(client.getNickName(), userInput);
                            break;
                    }
                    client.sendMessage(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void AskForNickName() {
        System.out.println("Insert a nickName");
        String tmp = null;
        try {
            tmp = stdIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.setNickName(tmp);
        client.sendMessage(new NicknameMessage(tmp));
        clear();

    }

    @Override
    public void refresh() {
        clear();
        marketTrayDraw();
        inHandLeaderCardsDraw();


    }

    @Override
    public void marketTrayDraw() {
        System.out.print(ResourceType.getColor(reducedGameModel.getReducedMarketTray().getSlide()) + "●\n" + CLIColors.getAnsiReset());
        for (int i = 0; i < reducedGameModel.getReducedMarketTray().getMarketTray().length; i++) {
            for (int j = 0; j < reducedGameModel.getReducedMarketTray().getMarketTray()[i].length; j++) {
                System.out.print(ResourceType.getColor(reducedGameModel.getReducedMarketTray().getMarketTray()[i][j]) + "● "  + CLIColors.getAnsiReset() );

            }
            System.out.print("\n");
        }
    }

    @Override
    public void inHandLeaderCardsDraw() {
        System.out.println("LeaderCard:");
        reducedGameModel.getReducedInHandLeaderCards().getInHandLeaderCards().forEach(System.out::println);
    }


    private void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private void waitMilliseconds(int s){
        try {
            TimeUnit.MILLISECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
