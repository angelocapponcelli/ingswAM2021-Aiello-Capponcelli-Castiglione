package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.networking.messages.clientMessages.ChosenInitialResourcesMessage;
import it.polimi.ingsw.networking.messages.clientMessages.DiscardedLeaderCardsMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.JoinGameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NewGameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NicknameMessage;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.CLIColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimpleCLI extends View {

    private BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    public SimpleCLI(Client client) {
        super(client);
    }

    @Override
    public void start() {

        new Thread(() -> {

            clear();
            System.out.println("Welcome to Maestri del Rinascimento");
            waitMilliseconds(100);
            askForNickName();
            askForCreateOrJoinGame();

        }).start();
    }


    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private void waitMilliseconds(int s) {
        try {
            TimeUnit.MILLISECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void askForInitialResources(){
        clear();
        int numOfResources;
        switch (reducedGameModel.getPlayerTurnPosition()){
            case 2:
                numOfResources=1;
                break;
            case 3:
                numOfResources=1;
                break;
            case 4:
                numOfResources=2;
                break;
            default:
                numOfResources=0;
                break;

        }

        List<ResourceType> initialResources = new ArrayList<>();
        for(int i = 0; i < numOfResources; i++){
            System.out.println("Choose a resource :");
            System.out.println("(1)Coin\n(2)Shield\n(3)Stone\n(4)Servant");
            String tmp = null;
            try {
                tmp = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (tmp){
                case "1":
                    initialResources.add(ResourceType.COIN);
                    break;
                case "2":
                    initialResources.add(ResourceType.SHIELD);
                    break;
                case "3":
                    initialResources.add(ResourceType.STONE);
                    break;
                case "4":
                    initialResources.add(ResourceType.SERVANT);
                    break;
            }
        }

        client.sendMessage(new ChosenInitialResourcesMessage(client.getNickName(), initialResources));


    }

    @Override
    public void temporaryDepotDraw(){
        clear();
        System.out.println("temporary depot:");
        reducedGameModel.getTemporaryDepot()
                .forEach((key, value) -> System.out.print(key.getColor() + "  ● " + value + CLIColors.getAnsiReset()));
        System.out.println();
    }

    @Override
    public void askForNickName() {
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
    public void askForCreateOrJoinGame() {
        System.out.println("Do you want to create(c) or join(j) a game?");
        String input;
        try {
            input = stdIn.readLine();

            if (input.equals("c")){
                System.out.println("Insert players number");
                String tmp = stdIn.readLine();
                client.sendMessage(new NewGameMessage(Integer.parseInt(tmp)));
                clear();

            } else if (input.equals("j") ){
                System.out.println("Insert GameID");
                String tmp = stdIn.readLine();
                client.sendMessage(new JoinGameMessage(Integer.parseInt(tmp)));
                clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void refresh() {
        clear();
        System.out.println(client.getNickName() + " position: " + reducedGameModel.getPlayerTurnPosition());
        marketTrayDraw();
        inHandLeaderCardsDraw();


    }

    @Override
    public void marketTrayDraw() {
        System.out.print(reducedGameModel.getMarketTray().getSlide().getColor() + "●\n" + CLIColors.getAnsiReset());
        for (int i = 0; i < reducedGameModel.getMarketTray().getMarketTray().length; i++) {
            for (int j = 0; j < reducedGameModel.getMarketTray().getMarketTray()[i].length; j++) {
                System.out.print(reducedGameModel.getMarketTray().getMarketTray()[i][j].getColor() + "● " + CLIColors.getAnsiReset());

            }
            System.out.print("\n");
        }
    }

    @Override
    public void inHandLeaderCardsDraw() {
        System.out.println("++++++++++LeaderCard++++++++++");
        reducedGameModel.getReducedInHandLeaderCards().getInHandLeaderCards().forEach(x -> System.out.println(x.getId()));
    }


    @Override
    public void askForLeaderCardsToDiscard() {
        System.out.println("Choose the ID of the first Leader Cards to discard");
        int id1 = 0;
        try {
            id1 = Integer.parseInt(stdIn.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Choose the ID of the second Leader Cards to discard");
        int id2 = 0;
        try {
            id2 = Integer.parseInt(stdIn.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.sendMessage(new DiscardedLeaderCardsMessage(client.getNickName(), id1, id2));
    }

}
