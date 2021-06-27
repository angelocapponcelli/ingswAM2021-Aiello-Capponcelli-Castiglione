package it.polimi.ingsw.client.view.CLI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.controller.MY_TURN;
import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedLeaderCard;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedPlayer;
import it.polimi.ingsw.networking.messages.clientMessages.*;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.JoinGameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NewGameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NicknameMessage;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CLI extends View {

    private final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    public CLI(Client client) {
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
        drawTemporaryDepot();
        drawWareHouse();

        //Todo special ability whiteMarble
        reducedGameModel.getTemporaryDepot().forEach((key, value) -> IntStream.range(0, value)
                .mapToObj(x -> key).filter(resourceType -> !resourceType.equals(ResourceType.ANY))
                .forEach(resourceType -> {
                    String shelf = null;
                    List<String> validInput = Arrays.asList("1","2","3","d");

                    do {
                        System.out.println("Where do you want to put " + resourceType + "? (1)(2)(3)\n(d) to discard");

                        try {
                            shelf = stdIn.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }while (!validInput.contains(shelf));

                    if (Objects.equals(shelf, "d")) {
                        client.sendMessage(new DiscardResourceMessage(client.getNickName(), key));
                    } else {
                        client.sendMessage(new ReallocateResourceMessage(client.getNickName(), key, "Temporary", "WareHouse", -1, Integer.parseInt(Objects.requireNonNull(shelf)) - 1));
                    }

                }));


    }

    public void askForAnyResourceReplacement() {
        System.out.println("Choose a replacement resource");
        System.out.println("1)COIN\n2)SHIELD\n3)STONE\n4)SERVANT\nd)DISCARD");
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
                case "d":
                    client.sendMessage(new DiscardAny(client.getNickName()));
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void splashScreen() {
        clear();
        System.out.println("\n" +
                "███╗░░░███╗░█████╗░███████╗░██████╗████████╗██████╗░██╗        ██████╗░███████╗██╗░░░░░\n" +
                "████╗░████║██╔══██╗██╔════╝██╔════╝╚══██╔══╝██╔══██╗██║        ██╔══██╗██╔════╝██║░░░░░\n" +
                "██╔████╔██║███████║█████╗░░╚█████╗░░░░██║░░░██████╔╝██║        ██║░░██║█████╗░░██║░░░░░\n" +
                "██║╚██╔╝██║██╔══██║██╔══╝░░░╚═══██╗░░░██║░░░██╔══██╗██║        ██║░░██║██╔══╝░░██║░░░░░\n" +
                "██║░╚═╝░██║██║░░██║███████╗██████╔╝░░░██║░░░██║░░██║██║        ██████╔╝███████╗███████╗\n" +
                "╚═╝░░░░░╚═╝╚═╝░░╚═╝╚══════╝╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝╚═╝        ╚═════╝░╚══════╝╚══════╝\n" +
                "\n" +
                "██████╗░██╗███╗░░██╗░█████╗░░██████╗░█████╗░██╗███╗░░░███╗███████╗███╗░░██╗████████╗░█████╗░\n" +
                "██╔══██╗██║████╗░██║██╔══██╗██╔════╝██╔══██╗██║████╗░████║██╔════╝████╗░██║╚══██╔══╝██╔══██╗\n" +
                "██████╔╝██║██╔██╗██║███████║╚█████╗░██║░░╚═╝██║██╔████╔██║█████╗░░██╔██╗██║░░░██║░░░██║░░██║\n" +
                "██╔══██╗██║██║╚████║██╔══██║░╚═══██╗██║░░██╗██║██║╚██╔╝██║██╔══╝░░██║╚████║░░░██║░░░██║░░██║\n" +
                "██║░░██║██║██║░╚███║██║░░██║██████╔╝╚█████╔╝██║██║░╚═╝░██║███████╗██║░╚███║░░░██║░░░╚█████╔╝\n" +
                "╚═╝░░╚═╝╚═╝╚═╝░░╚══╝╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚══╝░░░╚═╝░░░░╚════╝░");

    }

    @Override
    public void drawTemporaryDepot() {
        clear();
        System.out.println("temporary depot:");
        reducedGameModel.getTemporaryDepot()
                .forEach((key, value) -> System.out.print(key.getColor() + "  ● " + value + CLIColors.getAnsiReset()));
        System.out.println();
    }

    @Override
    public void drawWareHouse() {
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
    public void drawDevCardGrid() {
        System.out.println("+++++DEV_CARD_GRID+++++");
        for (ReducedDevelopmentCard[] row : reducedGameModel.getDevelopmentCardsGrid()) {
            drawDevelopmentCardRow(Arrays.asList(row));
        }
    }

    @Override
    public void drawPersonalDevelopmentBoard() {
        System.out.println("+++++PersonalDevelopmentBoard+++++");
        for (int i = 0; i < 3; i++) {
            if (reducedGameModel.getPersonalDevelopmentBoard().get(i) != null) {
                System.out.print("[" + reducedGameModel.getPersonalDevelopmentBoard().get(i).getId() + "]");
            } else System.out.print("[]");
        }
        System.out.println();
    }

    private void drawDevelopmentCardRow(List<ReducedDevelopmentCard> developmentCards){
        int width = 18;
        developmentCards.forEach(x -> {
            System.out.print("╔");
            IntStream.range(0,width).forEach(y -> System.out.print("═"));
            System.out.print("╗");
        });
        System.out.println();

        developmentCards.forEach(x -> System.out.print("║"+Utils.getAlignedToCenter(" |"+x.getId()+"|",width)+"║"));
        System.out.println();

        developmentCards.forEach(x -> System.out.print("║"+Utils.getAlignedToCenter(x.costToCLI(),width)+"║"));
        System.out.println();

        developmentCards.forEach(x -> System.out.print("║"+Utils.getAlignedToBothSide(x.getTypeLevel().toCLI(),width)+"║"));
        System.out.println();

        developmentCards.forEach(x -> System.out.print("║"+Utils.getAlignedToCenter(" ",width)+"║"));
        System.out.println();

        developmentCards.forEach(x -> System.out.print("║"+Utils.getAlignedToCenter(x.productionPowerInputToCli()+" → "+x.productionPowerOutputToCli(),width)+"║"));
        System.out.println();

        developmentCards.forEach(x -> System.out.print("║"+Utils.getAlignedToCenter(CLIColors.getAnsiYellowBackground()+"{"+x.getVictoryPoints()+"}"+CLIColors.getAnsiReset(),width)+"║"));
        System.out.println();


        developmentCards.forEach(x -> {
            System.out.print("╚");
            IntStream.range(0,width).forEach(y -> System.out.print("═"));
            System.out.print("╝");
        });
        System.out.println();

    }


    @Override
    public void drawStrongBox() {
        System.out.println("+++++STRONG_BOX+++++");
        reducedGameModel.getStrongBoxDepot()
                .forEach(reducedContainer -> System.out.print(reducedContainer.getResourceType().getColor()+" ● " + reducedContainer.getCount() + CLIColors.getAnsiReset()));
        System.out.println();
    }

    @Override
    public void drawFaithTrack() {

        for(int i = 0; i< reducedGameModel.getFaithTrack().size(); i++){
            if(i> 0 && reducedGameModel.getFaithTrack().get(i).getVictoryPoints() != reducedGameModel.getFaithTrack().get(i-1).getVictoryPoints()){
                System.out.print(CLIColors.ANSI_YELLOW_BACKGROUND+ "["+reducedGameModel.getFaithTrack().get(i).getVictoryPoints()+"]"+ CLIColors.getAnsiReset());
            }
            else System.out.print("[ ]"+CLIColors.getAnsiReset());
        }
        System.out.println();
        for(ReducedPlayer player: getReducedGameModel().getPlayers()){

            for(int i = 0; i< player.getFaithPosition(); i++){
                System.out.print("   ");
            }
            System.out.println(" ↑"+player.getNickName());
        }

    }


    @Override
    public void askForMainAction() {
        List<String> validInputs = Arrays.asList("1","2","3");
        String mainAction = null;

        do {
            System.out.println("> (1)TakeFromMarket (2)ActivateProduction (3)BuyDevCard");

            try {
                mainAction = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while (!validInputs.contains(mainAction));

        switch (Objects.requireNonNull(mainAction)) {
            case "1":
                client.getClientController().setMyTurnState(MY_TURN.TAKE_FROM_MARKET);
                break;
            case "2":
                client.getClientController().setMyTurnState(MY_TURN.ACTIVATE_PRODUCTION);
                break;
            case "3":
                client.getClientController().setMyTurnState(MY_TURN.BUY_DEV_CARD);
                break;
        }

    }

    public void takeFromMarket() {
        clear();
        drawWareHouse();
        drawMarketTray();

        List<String> validInput = Arrays.asList("r","c");
        List<String> validRow = Arrays.asList("1","2","3");
        List<String> validColumn = Arrays.asList("1","2","3","4");
        String rowOrColumn = null;
        String number = null;

        do {
            System.out.println("(r)Row (c)Column");
            try {
                rowOrColumn = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (!validInput.contains(rowOrColumn));

        rowOrColumn = Objects.equals(rowOrColumn, "r") ? "row" : "column";

        do {

            System.out.println("Number?");
            try {
                number = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while( !((rowOrColumn.equals("row") && validRow.contains(number)) || (rowOrColumn.equals("column") && validColumn.contains(number))) );

        client.sendMessage(new TakeFromMarketMessage(client.getNickName(), rowOrColumn, Integer.parseInt(Objects.requireNonNull(number)) - 1));


    }

    @Override
    public void buyDevCard() {
        clear();
        drawDevCardGrid();
        drawPersonalDevelopmentBoard();
        System.out.println("Choose a development card to buy");
        try {
            String toBuy = stdIn.readLine();
            client.sendMessage(new BuyDevCardMessage(client.getNickName(), Integer.parseInt(toBuy)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Todo: TO COMPLETE
    @Override
    public void activateProduction() {
        clear();
        drawWareHouse();
        boolean inputOK = false;
        Map<ResourceType,Integer> input = new HashMap<>();
        Map<ResourceType, Integer> output = new HashMap<>();

        try {
            do {

                System.out.println("1)Basic Board's Production 2)Development Card's Production");
                String productionType = stdIn.readLine();

                if(productionType.equals("1")){
                    inputOK = true;

                    /*Input choice*/
                    reducedGameModel.getProductionPowerInputBoard().forEach((key,value) ->{
                        if(key.equals(ResourceType.ANY)){
                            IntStream.range(0,value)
                                    .mapToObj(x -> key)
                                    .forEach( any -> {
                                        System.out.println("Choose an input Resource");
                                        ResourceType resourceReplacement = anyResourceReplacement();
                                        if (input.containsKey(resourceReplacement)){
                                            input.put(resourceReplacement, input.get(resourceReplacement)+1);
                                        } else input.put(resourceReplacement,1);
                            });
                        }
                        else {
                            input.put(key,value);
                        }
                    });

                    /*Output choice*/
                    reducedGameModel.getProductionPowerOutputBoard().forEach((key,value) ->{
                        if(key.equals(ResourceType.ANY)){
                            IntStream.range(0,value)
                                    .mapToObj(x -> key)
                                    .forEach( any -> {
                                        System.out.println("Choose an output Resource");
                                        ResourceType resourceReplacement = anyResourceReplacement();
                                        if (output.containsKey(resourceReplacement)){
                                            output.put(resourceReplacement, output.get(resourceReplacement)+1);
                                        } else output.put(resourceReplacement,1);
                                    });
                        }
                        else {
                            output.put(key,value);
                        }
                    });



                    client.sendMessage(new ActivateBasicProductionMessage(client.getNickName(),input,output));


                }else if(productionType.equals("2")){
                    inputOK = true;

                }


            }while (!inputOK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void askForNickName() {
        splashScreen();
        System.out.println("Insert a nickName");
        String nickName = null;
        try {
            nickName = stdIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.setNickName(nickName);
        client.sendMessage(new NicknameMessage(nickName));

    }


    private ResourceType anyResourceReplacement(){
        System.out.println("1)COIN\n2)STONE\n3)SHIELD\n4)SERVANT");
        try {
            String resourceString = stdIn.readLine();
            switch (resourceString){
                case "1":
                    return ResourceType.COIN;
                case "2":
                    return ResourceType.STONE;
                case "3":
                    return ResourceType.SHIELD;
                case "4":
                    return ResourceType.SERVANT;
                default:
                    throw new IllegalStateException("Unexpected value: " + resourceString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void askForCreateOrJoinGame() {
        clear();
        List<String> validInput = Arrays.asList("c", "j");
        String input = null;
        do {
            System.out.println("Do you want to create(c) or join(j) a game?");
            try {
                input = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while (!validInput.contains(input));

            if (Objects.equals(input, "c")) {
                List<String> validPlayersNumber = Arrays.asList("1","2","3","4");
                String playersNumber = null;
                do {
                    System.out.println("Insert players number");
                    try {
                        playersNumber = stdIn.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }while (!validPlayersNumber.contains(playersNumber));

                client.sendMessage(new NewGameMessage(Integer.parseInt(Objects.requireNonNull(playersNumber))));
            } else if (Objects.equals(input, "j")) {
                System.out.println("Insert GameID");
                String tmp = null;
                try {
                    tmp = stdIn.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                client.sendMessage(new JoinGameMessage(Integer.parseInt(Objects.requireNonNull(tmp))));
            }
            clear();
            System.out.println("Waiting for all players to join...");

    }

    @Override
    public void refresh() {
        clear();
        drawFaithTrack();
        drawMarketTray();
        drawInHandLeaderCards();
        drawWareHouse();
        drawStrongBox();
        drawDevCardGrid();
        drawPersonalDevelopmentBoard();


    }

    @Override
    public void drawMarketTray() {
        System.out.println("+++++MarketTray+++++");
        System.out.print(reducedGameModel.getMarketTray().getSlide().getColor() + "●\n" + CLIColors.getAnsiReset());
        System.out.println("   (1)(2)(3)(4)");
        for (int i = 0; i < reducedGameModel.getMarketTray().getMarketTray().length; i++) {
            System.out.print("("+(i+1)+")");
            for (int j = 0; j < reducedGameModel.getMarketTray().getMarketTray()[i].length; j++) {
                System.out.print(reducedGameModel.getMarketTray().getMarketTray()[i][j].getColor() + " ● " + CLIColors.getAnsiReset());

            }
            System.out.print("\n");
        }
    }

    @Override
    public void drawInHandLeaderCards() {
        int width = 14;
        System.out.println("++++++++++LeaderCard++++++++++");
        List<ReducedLeaderCard> inHandLeaderCards = reducedGameModel.getReducedInHandLeaderCards().getInHandLeaderCards();

        inHandLeaderCards.forEach(x -> {
            System.out.print("╔");
            IntStream.range(0,width).forEach(y -> System.out.print("═"));
            System.out.print("╗");
        });
        System.out.println();

        inHandLeaderCards.forEach(x -> System.out.print("║"+Utils.getAlignedToCenter(" |"+x.getId()+"|",width)+"║"));
        System.out.println();

        inHandLeaderCards.forEach(x -> System.out.print("║"+Utils.getAlignedToLeft(x.requirementsToCLI(),width)+"║"));
        System.out.println();

        inHandLeaderCards.forEach(x -> System.out.print("║"+Utils.getAlignedToLeft(" ",width)+"║"));
        System.out.println();

        inHandLeaderCards.forEach(x -> System.out.print("║"+Utils.getAlignedToCenter(x.victoryPointsToCLI(),width)+"║"));
        System.out.println();

        inHandLeaderCards.forEach(x -> System.out.print("║"+Utils.getAlignedToLeft(" ",width)+"║"));
        System.out.println();

        inHandLeaderCards.forEach(x -> System.out.print("║"+Utils.getAlignedToCenter(x.specialAbilityToCLI(),width)+"║"));
        System.out.println();

        inHandLeaderCards.forEach(x -> {
            System.out.print("╚");
            IntStream.range(0,width).forEach(y -> System.out.print("═"));
            System.out.print("╝");
        });
        System.out.println();


    }

    @Override
    public void askForLeaderCardsToDiscard() {
        clear();
        List<String> validInput = reducedGameModel.getReducedInHandLeaderCards().getInHandLeaderCards().stream().map(x -> String.valueOf(x.getId())).collect(Collectors.toList());
        drawInHandLeaderCards();
        String id1 = null;
        do {
            System.out.println("Choose the ID of the first Leader Cards to discard");
            try {
                id1 = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while (!validInput.contains(id1));
        validInput.remove(id1);


        String id2 = null;
        do {
            System.out.println("Choose the ID of the second Leader Cards to discard");
            try {
                id2 = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while (!validInput.contains(id2));

        client.sendMessage(new DiscardedLeaderCardsMessage(client.getNickName(), Integer.parseInt(Objects.requireNonNull(id1)), Integer.parseInt(Objects.requireNonNull(id2))));
    }

}
