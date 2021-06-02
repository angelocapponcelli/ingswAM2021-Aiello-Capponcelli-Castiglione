package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.networking.messages.clientMessages.ChosenInitialResourcesMessage;
import it.polimi.ingsw.networking.messages.clientMessages.DiscardedLeaderCardsMessage;
import it.polimi.ingsw.networking.messages.clientMessages.ReallocateResourceMessage;
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
import java.util.stream.IntStream;

public class SimpleCLI extends View {

    private BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    public SimpleCLI(Client client) {
        super(client);
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
    public boolean askForInitialResources() {
        clear();
        int numOfResources;
        switch (reducedGameModel.getPlayerTurnPosition()) {
            case 2:
            case 3:
                numOfResources = 1;
                break;
            case 4:
                numOfResources = 2;
                break;
            default:
                numOfResources = 0;
                break;
        }

        List<ResourceType> initialResources = new ArrayList<>();
        for (int i = 0; i < numOfResources; i++) {
            System.out.println("Choose a resource :");
            System.out.println("(1)Coin\n(2)Shield\n(3)Stone\n(4)Servant");
            String tmp = null;
            try {
                tmp = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (tmp) {
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
        if (numOfResources > 0) {
            client.sendMessage(new ChosenInitialResourcesMessage(client.getNickName(), initialResources));
            return true;
        }

        return false;
    }

    @Override
    public void moveFromTemporary() {
        clear();
        temporaryDepotDraw();
        wareHouseDraw();

        reducedGameModel.getTemporaryDepot().entrySet()
                .forEach(entry -> IntStream.range(0, entry.getValue())
                        .mapToObj(x -> entry.getKey())
                        .forEach(resourceType -> {
                            System.out.println("Where do you want to put " + resourceType + "? (1)(2)(3)");
                            String shelf;
                            try {
                                shelf = stdIn.readLine();
                                client.sendMessage(new ReallocateResourceMessage(client.getNickName(), entry.getKey(), "Temporary", "WareHouse", -1, Integer.parseInt(shelf) -1));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }));


        /*reducedGameModel.getTemporaryDepot().entrySet().forEach(x -> {
            for (int i = 0; i < x.getValue(); i++) {
                String destination = "wareHouse";
                if (reducedGameModel.getActivatedSpecialAbilities().containsKey(SpecialAbilityType.EXTRADEPOT)) {
                    System.out.println("Where do you want to put " + x.getKey() + "(1)WareHouse\n(2)SpecialDepot");
                    try {
                        destination = stdIn.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (destination.equals("2")) {
                        client.sendMessage(new ReallocateResourceMessage(client.getNickName(), "Temporary", "Special", -1, -1));
                    }

                }
                else{
                    System.out.println("In which shelf do you want to put " + x.getKey() +  "? (1), (2) or (3)");
                    Integer shelf = null;
                    try {
                        shelf = Integer.parseInt(stdIn.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    client.sendMessage(new ReallocateResourceMessage(client.getNickName(),"Temporary", "WareHouse", -1, shelf));

                }

            }

        });*/
    }

    @Override
    public void askForAnyResourceReplacement() {
        System.out.println("Choose a replacement resource");
        System.out.println("1)COIN\n2)SHIELD\n3)STONE\n4)SERVANT");
        try {
            String input = stdIn.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void splashScreen() {
        clear();
        System.out.println("Welcome to Maestri del Rinascimento");
        waitMilliseconds(500);
    }

    @Override
    public void temporaryDepotDraw() {
        clear();
        System.out.println("temporary depot:");
        reducedGameModel.getTemporaryDepot()
                .forEach((key, value) -> System.out.print(key.getColor() + "  ● " + value + CLIColors.getAnsiReset()));
        System.out.println();
    }

    @Override
    public void wareHouseDraw() {
        System.out.println("+++++WareHouse+++++");
        for (int i = 0; i < 3; i++) {
            System.out.print("("+ (i+1) +")");
            int count = getReducedGameModel().getWareHouseDepot().get(i).getCount();
            for (int j = 0; j < 3 - i; j++) System.out.print(" ");
            for (int j = 0; j < i + 1; j++) {
                if (count > 0)
                    System.out.print(getReducedGameModel().getWareHouseDepot().get(i).getResourceType().getColor() + "● " + CLIColors.getAnsiReset());
                else System.out.print("□ ");
                count--;
            }
            System.out.println();
        }
    }

    @Override
    public void askForNickName() {
        clear();
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

            if (input.equals("c")) {
                System.out.println("Insert players number");
                String tmp = stdIn.readLine();
                client.sendMessage(new NewGameMessage(Integer.parseInt(tmp)));
                clear();

            } else if (input.equals("j")) {
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
        System.out.println(client.getNickName() + " Turn Position: " + reducedGameModel.getPlayerTurnPosition());
        marketTrayDraw();
        inHandLeaderCardsDraw();
        wareHouseDraw();


    }

    @Override
    public void marketTrayDraw() {
        System.out.println("+++++WareHouse+++++");
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
