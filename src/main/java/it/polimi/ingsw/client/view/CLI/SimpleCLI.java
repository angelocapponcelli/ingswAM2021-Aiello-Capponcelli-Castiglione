package it.polimi.ingsw.client.view.CLI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.controller.MY_TURN;
import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.networking.messages.clientMessages.*;
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
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SimpleCLI extends View {

    private final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

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
            switch (Objects.requireNonNull(tmp)) {
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

        //Todo special ability whiteMarble
        reducedGameModel.getTemporaryDepot().forEach((key, value) -> IntStream.range(0, value)
                .mapToObj(x -> key).filter(resourceType -> !resourceType.equals(ResourceType.ANY))
                .forEach(resourceType -> {
                    System.out.println("Where do you want to put " + resourceType + "? (1)(2)(3)");
                    System.out.println("(d) to discard");
                    String shelf;
                    try {
                        shelf = stdIn.readLine();
                        if (shelf.equals("d")) {
                            client.sendMessage(new DiscardResourceMessage(client.getNickName()));
                        } else {
                            client.sendMessage(new ReallocateResourceMessage(client.getNickName(), key, "Temporary", "WareHouse", -1, Integer.parseInt(shelf) - 1));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));


    }

    public void askForAnyResourceReplacement() {
        System.out.println("Choose a replacement resource");
        System.out.println("1)COIN\n2)SHIELD\n3)STONE\n4)SERVANT\n5)DISCARD");
        try {
            String input = stdIn.readLine();
            switch (input) {
                case "1":
                    client.sendMessage(new SelectResourceReplacementMessage(client.getNickName(), ResourceType.COIN));
                    break;
                case "2":
                    client.sendMessage(new SelectResourceReplacementMessage(client.getNickName(), ResourceType.SHIELD));
                    break;
                case "3":
                    client.sendMessage(new SelectResourceReplacementMessage(client.getNickName(), ResourceType.STONE));
                    break;
                case "4":
                    client.sendMessage(new SelectResourceReplacementMessage(client.getNickName(), ResourceType.SERVANT));
                    break;
                case "5":
                    client.sendMessage(new DiscardAny(client.getNickName()));
            }

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
            System.out.print("(" + (i + 1) + ")");
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
    public void devCardGridDraw() {
        System.out.println("+++++DEV_CARD_GRID+++++");
        for (ReducedDevelopmentCard[] row : reducedGameModel.getDevelopmentCardsGrid()) {
            for (ReducedDevelopmentCard card : row) {
                System.out.printf("%-3d", card.getId());
            }
            System.out.println();
        }
    }

    @Override
    public void personalDevelopmentBoardDraw() {
        System.out.println("+++++PersonalDevelopmentBoard+++++");
        for (int i = 0; i < 3; i++) {
            if (reducedGameModel.getPersonalDevelopmentBoard().get(i) != null) {
                System.out.print("[" + reducedGameModel.getPersonalDevelopmentBoard().get(i).getId() + "]");
            } else System.out.print("[]");
        }
        System.out.println();
    }

    @Override
    public MY_TURN askForMainAction() {
        System.out.println("> (1)TakeFromMarket (2)ActivateProduction (3)BuyDevCard");
        try {
            String mainAction = stdIn.readLine();
            switch (mainAction) {
                case "1":
                    return MY_TURN.TAKE_FROM_MARKET;
                case "2":
                    return MY_TURN.ACTIVATE_PRODUCTION;
                case "3":
                    return MY_TURN.BUY_DEV_CARD;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void takeFromMarket() {
        clear();
        wareHouseDraw();
        marketTrayDraw();
        System.out.println("(r)Row (c)Column");
        try {
            String rowOrColumn = stdIn.readLine();
            rowOrColumn = rowOrColumn.equals("r") ? "row" : "column";
            System.out.println("Number?");
            String number = stdIn.readLine();
            client.sendMessage(new TakeFromMarketMessage(client.getNickName(), rowOrColumn, Integer.parseInt(number)));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void buyDevCard() {
        clear();
        devCardGridDraw();
        personalDevelopmentBoardDraw();
        System.out.println("Choose a development card to buy");
        try {
            String toBuy = stdIn.readLine();
            client.sendMessage(new BuyDevCardMessage(client.getNickName(), Integer.parseInt(toBuy)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void faithTrackDraw() {
        reducedGameModel.getPlayers()
                .forEach(reducedPlayer -> System.out.println(reducedPlayer.getNickName() + " :" + reducedPlayer.getFaithPosition()));
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
        faithTrackDraw();
        marketTrayDraw();
        inHandLeaderCardsDraw();
        wareHouseDraw();
        devCardGridDraw();
        personalDevelopmentBoardDraw();


    }

    @Override
    public void marketTrayDraw() {
        System.out.println("+++++MarketTray+++++");
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
        reducedGameModel.getReducedInHandLeaderCards().getInHandLeaderCards().forEach(x -> System.out.print(x.getId() + " "));
        System.out.println();
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
