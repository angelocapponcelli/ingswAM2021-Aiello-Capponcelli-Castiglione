package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.reducedGameModel.*;
import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.clientMessages.*;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.JoinGameMessage;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NewGameMessage;
import it.polimi.ingsw.server.model.cards.TypeLevel;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.CLIColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CLI extends View {

    private final String COLOR_TEXT_HEADING = CLIColors.getAnsiGreen();
    private final String COLOR_PROGRESS_BAR = CLIColors.getAnsiWhite();
    private final String COLOR_TEXT_PRIMARY = CLIColors.getAnsiWhite();
    private final String COLOR_TEXT_ERROR = CLIColors.getAnsiRed();
    private final String COLOR_CARD = CLIColors.getAnsiWhite();
    private final String COLOR_DEPOT = CLIColors.getAnsiWhite();
    private final String COLOR_MARKET_TRAY = CLIColors.getAnsiWhite();
    private final String COLOR_FAITH_TRACK = CLIColors.getAnsiWhite();

    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    public CLI(Client client) {
        super(client);
    }

    @Override
    public void start() { //TODO

        clear();
        System.out.println("Welcome to Maestri del Rinascimento");

        waitMilliseconds(1000);
        clear();


        new Thread(() -> {
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            Message message;
            try {
                askForNickName();
                while (!((userInput = stdIn.readLine()).equals("quit"))) {
                    switch (userInput) {
                        case "newGame": {
                            System.out.println("Insert players number");
                            String tmp = stdIn.readLine();

                            message = new NewGameMessage(Integer.parseInt(tmp));
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
    public void askForNickName() {
        boolean inputValidity = false;
        String nickname = null;
        while (!inputValidity) {
            System.out.println(COLOR_TEXT_PRIMARY + "*********************** NICKNAME *************************" + CLIColors.getAnsiReset());
            System.out.println(COLOR_TEXT_PRIMARY + "Choose your nickname" + CLIColors.getAnsiReset());
            System.out.print(COLOR_TEXT_PRIMARY + "> " + CLIColors.getAnsiReset());

            try {
                nickname = stdIn.readLine();
                if (nickname.length() <= 15) inputValidity = true;
                else
                    System.out.println(COLOR_TEXT_ERROR + "Invalid input: please insert a nickname maximum 15 characters\n" + CLIColors.getAnsiReset());
            } catch (IOException e) {
                System.out.println(COLOR_TEXT_ERROR + "Invalid input: please insert a nickname maximum 15 characters\n" + CLIColors.getAnsiReset());

            }
        }
        client.setNickName(nickname);
        client.sendMessage(new InsertNicknameMessage(nickname));
    }

    @Override
    public void refresh() { //TODO

    }

    @Override
    public void marketTrayDraw() {
        for (String row : getStringRowsMarketTray()) {
            System.out.println(row);
        }
    }

    @Override
    public void inHandLeaderCardsDraw() {
        List<List<String>> cards = new ArrayList<>();
        if (reducedGameModel.getReducedInHandLeaderCards().getInHandLeaderCards().size() > 0) {
            for (ReducedLeaderCard card : reducedGameModel.getReducedInHandLeaderCards().getInHandLeaderCards()) {
                cards.add(getStringRowsLeaderCard(card));
            }
            for (int i = 0; i < cards.get(0).size(); i++) {
                for (int j = 0; j < cards.size(); j++) {
                    System.out.print(cards.get(j).get(i) + "  ");
                }
                System.out.print("\n");
            }
        } else System.out.print("You don't have any cards in your hand\n");
    }

    @Override
    public void askForLeaderCardsToDiscard() {
        boolean inputValidity = false;
        int id1 = 0, id2 = 0;

        System.out.println(COLOR_TEXT_PRIMARY + "********************** DISCARD CARD **********************" + CLIColors.getAnsiReset());
        while (!inputValidity) {
            inHandLeaderCardsDraw();
            System.out.println(COLOR_TEXT_PRIMARY + "Insert an ID card to dicard" + CLIColors.getAnsiReset());
            System.out.print(COLOR_TEXT_PRIMARY + "> " + CLIColors.getAnsiReset());
            try {
                id1 = Integer.parseInt(stdIn.readLine());
                for (ReducedLeaderCard reducedLeaderCard : reducedGameModel.getReducedInHandLeaderCards().getInHandLeaderCards()) {
                    if (reducedLeaderCard.getId() == id1) {
                        inputValidity = true;
                        break;
                    }
                }
                if (!inputValidity)
                    System.out.println(COLOR_TEXT_ERROR + "Invalid input: insert the card ID from your hand\n" + CLIColors.getAnsiReset());
            } catch (IOException e) {
                System.out.println(COLOR_TEXT_ERROR + "Invalid input: insert the card ID from your hand\n" + CLIColors.getAnsiReset());
            }
        }
        inputValidity = false;
        while (!inputValidity) {
            inHandLeaderCardsDraw();
            System.out.println(COLOR_TEXT_PRIMARY + "Insert an ID card to dicard" + CLIColors.getAnsiReset());
            System.out.print(COLOR_TEXT_PRIMARY + "> " + CLIColors.getAnsiReset());
            try {
                id2 = Integer.parseInt(stdIn.readLine());
                if (id2 == id1)
                    System.out.println(COLOR_TEXT_ERROR + "Invalid input: choose a different card\n" + CLIColors.getAnsiReset());
                else {
                    for (ReducedLeaderCard reducedLeaderCard : reducedGameModel.getReducedInHandLeaderCards().getInHandLeaderCards()) {
                        if (reducedLeaderCard.getId() == id2) {
                            inputValidity = true;
                            break;
                        }
                    }
                    if (!inputValidity)
                        System.out.println(COLOR_TEXT_ERROR + "Invalid input: insert the card ID from your hand\n" + CLIColors.getAnsiReset());
                }
            } catch (IOException e) {
                System.out.println(COLOR_TEXT_ERROR + "Invalid input: insert the card ID from your hand\n" + CLIColors.getAnsiReset());
            }
        }

        client.sendMessage(new DiscardedLeaderCardsMessage(client.getNickName(), id1, id2));
    }

    @Override
    public void askForCreateOrJoinGame() {
        boolean inputValidity = false;
        while (!inputValidity) {
            System.out.println(COLOR_TEXT_PRIMARY + "*********************** GAME MENU ***********************" + CLIColors.getAnsiReset());
            System.out.println(COLOR_TEXT_PRIMARY + "[1] --> New game" + CLIColors.getAnsiReset());
            System.out.println(COLOR_TEXT_PRIMARY + "[2] --> Join existing game" + CLIColors.getAnsiReset());
            System.out.print(COLOR_TEXT_PRIMARY + "> " + CLIColors.getAnsiReset());
            try {
                int gameMode = 0;
                gameMode = Integer.parseInt(stdIn.readLine());
                if (gameMode == 1 || gameMode == 2) inputValidity = true;
                else
                    System.out.println(COLOR_TEXT_ERROR + "Invalid input: please insert 1 or 2\n" + CLIColors.getAnsiReset());
            } catch (InputMismatchException | IOException e) {
                System.out.println(COLOR_TEXT_ERROR + "Invalid input: please insert a number\n" + CLIColors.getAnsiReset());
            }
        }
        //TODO process choose
    }

    @Override
    public void temporaryDepotDraw() {
        List<List<String>> resources = new ArrayList<>();
        if (!reducedGameModel.getTemporaryDepot().isEmpty()) {
            for (Map.Entry<ResourceType, Integer> entry : reducedGameModel.getTemporaryDepot().entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    resources.add(getStringRowsMarketBalls(entry.getKey()));
                }
            }

            for (int i = 0; i < resources.get(0).size(); i++) {
                for (int j = 0; j < resources.size(); j++) {
                    System.out.print(resources.get(j).get(i) + "  ");
                }
                System.out.print("\n");
            }
        } else System.out.println(COLOR_TEXT_PRIMARY + "Temporary depot is empty" + CLIColors.getAnsiReset());
    }

    @Override
    public void askForInitialResources() {
        boolean inputValidity = false;
        int option = 0;
        int numberResource = 0;
        List<ResourceType> resourceChosen = new ArrayList<>();
        if (reducedGameModel.getPlayerTurnPosition() == 1 || reducedGameModel.getPlayerTurnPosition() == 2)
            numberResource = 1;
        else if (reducedGameModel.getPlayerTurnPosition() == 3)
            numberResource = 2;
        for (int i = 0; i < numberResource; i++) {
            inputValidity = false;
            while (!inputValidity) {
                System.out.println(COLOR_TEXT_PRIMARY + "******************** INITIAL RESOURCE ********************" + CLIColors.getAnsiReset());
                System.out.println(COLOR_TEXT_PRIMARY + "Choose a resource (you will receive the resource choosen)" + CLIColors.getAnsiReset());
                System.out.println(COLOR_TEXT_PRIMARY + "[1] --> Coin" + CLIColors.getAnsiReset());
                System.out.println(COLOR_TEXT_PRIMARY + "[2] --> Servant" + CLIColors.getAnsiReset());
                System.out.println(COLOR_TEXT_PRIMARY + "[3] --> Stone" + CLIColors.getAnsiReset());
                System.out.println(COLOR_TEXT_PRIMARY + "[4] --> Shield" + CLIColors.getAnsiReset());
                System.out.print(COLOR_TEXT_PRIMARY + "> " + CLIColors.getAnsiReset());

                try {
                    option = Integer.parseInt(stdIn.readLine());
                    if (option >= 1 || option <= 4) inputValidity = true;
                    else
                        System.out.println(COLOR_TEXT_ERROR + "Invalid input: please insert 1,2,3 or 4 (the number option correspond to the resource you will receive)\n" + CLIColors.getAnsiReset());
                } catch (InputMismatchException | IOException e) {
                    System.out.println(COLOR_TEXT_ERROR + "Invalid input: please insert a number\n" + CLIColors.getAnsiReset());
                }
            }
            switch (option) {
                case 1: resourceChosen.add(ResourceType.COIN);
                case 2: resourceChosen.add(ResourceType.SERVANT);
                case 3: resourceChosen.add(ResourceType.STONE);
                case 4: resourceChosen.add(ResourceType.SHIELD);
            }
        }

        client.sendMessage(new ChosenInitialResourcesMessage(client.getNickName(), resourceChosen));
    }


    public void init() {
        clear();
        System.out.println(COLOR_TEXT_HEADING + "\n\n\n\n\n\n\n" +
                "\t\t\t\t\t\t   ▄▄▄▄███▄▄▄▄      ▄████████    ▄████████     ███        ▄████████    ▄████████    ▄████████           ▄██████▄     ▄████████\n" +
                "\t\t\t\t\t\t ▄██▀▀▀███▀▀▀██▄   ███    ███   ███    ███ ▀█████████▄   ███    ███   ███    ███   ███    ███          ███    ███   ███    ███\n" +
                "\t\t\t\t\t\t ███   ███   ███   ███    ███   ███    █▀     ▀███▀▀██   ███    █▀    ███    ███   ███    █▀           ███    ███   ███    █▀ \n" +
                "\t\t\t\t\t\t ███   ███   ███   ███    ███   ███            ███   ▀  ▄███▄▄▄      ▄███▄▄▄▄██▀   ███                 ███    ███  ▄███▄▄▄    \n" +
                "\t\t\t\t\t\t ███   ███   ███ ▀███████████ ▀███████████     ███     ▀▀███▀▀▀     ▀▀███▀▀▀▀▀   ▀███████████          ███    ███ ▀▀███▀▀▀    \n" +
                "\t\t\t\t\t\t ███   ███   ███   ███    ███          ███     ███       ███    █▄  ▀███████████          ███          ███    ███   ███       \n" +
                "\t\t\t\t\t\t ███   ███   ███   ███    ███    ▄█    ███     ███       ███    ███   ███    ███    ▄█    ███          ███    ███   ███       \n" +
                "\t\t\t\t\t\t  ▀█   ███   █▀    ███    █▀   ▄████████▀     ▄████▀     ██████████   ███    ███  ▄████████▀            ▀██████▀    ███       \n" +
                "\t\t\t\t\t\t                                                                      ███    ███                                              \n\n" +
                "\t\t\t\t\t\t   ▄████████    ▄████████ ███▄▄▄▄      ▄████████  ▄█     ▄████████    ▄████████    ▄████████ ███▄▄▄▄    ▄████████    ▄████████\n" +
                "\t\t\t\t\t\t  ███    ███   ███    ███ ███▀▀▀██▄   ███    ███ ███    ███    ███   ███    ███   ███    ███ ███▀▀▀██▄ ███    ███   ███    ███\n" +
                "\t\t\t\t\t\t  ███    ███   ███    █▀  ███   ███   ███    ███ ███▌   ███    █▀    ███    █▀    ███    ███ ███   ███ ███    █▀    ███    █▀ \n" +
                "\t\t\t\t\t\t ▄███▄▄▄▄██▀  ▄███▄▄▄     ███   ███   ███    ███ ███▌   ███          ███          ███    ███ ███   ███ ███         ▄███▄▄▄    \n" +
                "\t\t\t\t\t\t▀▀███▀▀▀▀▀   ▀▀███▀▀▀     ███   ███ ▀███████████ ███▌ ▀███████████ ▀███████████ ▀███████████ ███   ███ ███        ▀▀███▀▀▀    \n" +
                "\t\t\t\t\t\t▀███████████   ███    █▄  ███   ███   ███    ███ ███           ███          ███   ███    ███ ███   ███ ███    █▄    ███    █▄ \n" +
                "\t\t\t\t\t\t  ███    ███   ███    ███ ███   ███   ███    ███ ███     ▄█    ███    ▄█    ███   ███    ███ ███   ███ ███    ███   ███    ███\n" +
                "\t\t\t\t\t\t  ███    ███   ██████████  ▀█   █▀    ███    █▀  █▀    ▄████████▀   ▄████████▀    ███    █▀   ▀█   █▀  ████████▀    ██████████\n" +
                "\t\t\t\t\t\t  ███    ███                                                                                                                  \n\n" +
                "\t\t\t\t\t\t╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" +
                CLIColors.getAnsiReset());

        for (int i = 0; i < 123; i++) {
            System.out.print(COLOR_TEXT_HEADING + "\r\t\t\t\t\t\t║ " + COLOR_PROGRESS_BAR);
            for (int j = 0; j < i; j++) {
                System.out.print("█");
            }
            for (int j = i; j < 122; j++) {
                System.out.print(" ");
            }
            System.out.print(COLOR_TEXT_HEADING + " ║");
            waitMilliseconds(1);
        }
        waitMilliseconds(100);
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + CLIColors.getAnsiBlack() + ".");

    }

    public void playersNumberChoice() {
        boolean inputValidity = false;
        while (!inputValidity) {
            System.out.println(COLOR_TEXT_PRIMARY + "********************* PLAYER NUMBER *********************" + CLIColors.getAnsiReset());
            System.out.println(COLOR_TEXT_PRIMARY + "Choose players number (between 1 and 4)" + CLIColors.getAnsiReset());
            System.out.print(COLOR_TEXT_PRIMARY + "> " + CLIColors.getAnsiReset());
            try {
                int playersNumber = Integer.parseInt(stdIn.readLine());
                if (playersNumber >= 1 && playersNumber <= 4) inputValidity = true;
                else
                    System.out.println(COLOR_TEXT_ERROR + "Invalid input: please insert a number between 1 and 4\n" + CLIColors.getAnsiReset());
            } catch (InputMismatchException | IOException e) {
                System.out.println(COLOR_TEXT_ERROR + "Invalid input: please insert a number between 1 and 4\n" + CLIColors.getAnsiReset());
            }

        }
        /** clientController.playersNumberChoice(playersNumber); **/
    }

    public void gameIDChoice() {
        boolean inputValidity = false;
        while (!inputValidity) {
            System.out.println(COLOR_TEXT_PRIMARY + "************************ GAME ID ************************" + CLIColors.getAnsiReset());
            System.out.println(COLOR_TEXT_PRIMARY + "Insert gameID number you want join" + CLIColors.getAnsiReset());
            System.out.print(COLOR_TEXT_PRIMARY + "> " + CLIColors.getAnsiReset());
            try {
                int gameID = Integer.parseInt(stdIn.readLine());
                System.out.println(COLOR_TEXT_PRIMARY + "Search game #" + gameID + "..." + CLIColors.getAnsiReset());
                inputValidity = true;
            } catch (InputMismatchException | IOException e) {
                System.out.println(COLOR_TEXT_ERROR + "Invalid input: please insert the gameID number\n" + CLIColors.getAnsiReset());
            }

        }
        /** clientController.gameIDChoice(gameID); **/
    }

    public void gameIDSuccess() {
        System.out.println(COLOR_TEXT_PRIMARY + "Game found!\n" + CLIColors.getAnsiReset());
    }

    public void gameIDError() {
        System.out.println(COLOR_TEXT_ERROR + "Game not found!\n" + CLIColors.getAnsiReset());
    }

    public void genericCommunication(String textToDisplay) {
        System.out.println(COLOR_TEXT_PRIMARY + textToDisplay + "\n" + CLIColors.getAnsiReset());
    }

    public void showBoard() {
        clear();
        List<ReducedDevelopmentCard> reducedDevelopmentCards = new ArrayList<>();
        for (int i = 0; i < reducedGameModel.getDevelopmentCardsGrid().length; i++) {
            for (int j = 0; j < reducedGameModel.getDevelopmentCardsGrid()[0].length; j++) {
                reducedDevelopmentCards.add(reducedGameModel.getDevelopmentCardsGrid()[i][j]);
            }
        }
        List<String> developmentCardGrid = getStringDevelopmentCardGrid(reducedDevelopmentCards);
        List<String> warehouse = getStringRowsWarehouse(reducedGameModel.getWareHouseDepot());
        List<String> strongBox = getStringRowsStrongBox(reducedGameModel.getStrongBoxDepot());
        //List<String> special = getStringRowsSpecial(reducedGameModel.getSpecialDepot().get); //todo
        //List<String> leaderCardToDisplay = getStringRowsLeaderCard(leaderCard);
        //List<String> marketTrayToDisplay = getStringRowsMarketTray(reducedGameModel.getMarketTray(), reducedGameModel.getSlide());
        //List<String> faithTrack = getStringRowsFaithTrack(reducedGameModel.getPlayers());

      /*  for (int i = 0; i < marketTrayToDisplay.size(); i++) {
            if (i<faithTrack.size()) System.out.print(faithTrack.get(i));
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    ");
            System.out.print("  "+marketTrayToDisplay.get(i));
            System.out.print("\n");
        } */

        int j = 0;
        for (int i = 0; i < developmentCardGrid.size(); i++) {
            System.out.print(developmentCardGrid.get(i));
            if (i < warehouse.size()) System.out.print("   " + warehouse.get(i));
            else if (i < 23) System.out.print("                               ");
            if (i < strongBox.size()) System.out.print("   " + strongBox.get(i));
            else if (i >= strongBox.size() && i < strongBox.size()) ;
            else if (i < warehouse.size()) {
                //  System.out.print("   " + special.get(j));
                j++;
            } //else if (j < special.size()) {
            //System.out.print("   " + special.get(j));
            //j++;
            //  }
            if (i > 22 && i < 33)
                //     System.out.print("   " + leaderCardToDisplay.get(i - 23) + "    " + leaderCardToDisplay.get(i - 23));
                System.out.print("\n");
        }
    }

    private List<String> getStringRowsDevelopmentCard(ReducedDevelopmentCard reducedDevelopmentCard) {
        List<String> rows = new ArrayList<>();
        rows.add(COLOR_CARD + "╔══DEVELOPMENT  CARD══╗" + CLIColors.getAnsiReset());
        rows.add(COLOR_CARD + "║ LV " + reducedDevelopmentCard.getLevel() + "         " + typeOutput(reducedDevelopmentCard.getType()) + COLOR_CARD + " ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_CARD + "║┌─Cost──────────────┐║" + CLIColors.getAnsiReset());
        int rowCount = 3;
        for (Map.Entry<ResourceType, Integer> entry : reducedDevelopmentCard.getCost().entrySet()) {
            rows.add(COLOR_CARD + "║│ " + entry.getValue() + " " + resourceOutput(entry.getKey()) + COLOR_CARD + "         │║" + CLIColors.getAnsiReset());
            rowCount--;
        }
        rows.add(COLOR_CARD + "║└───────────────────┘║" + CLIColors.getAnsiReset());
        for (int i = 0; i < rowCount; i++) {
            rows.add(COLOR_CARD + "║                     ║" + CLIColors.getAnsiReset());
        }
        rowCount = 3;
        List coutInput = new ArrayList<Integer>(reducedDevelopmentCard.getProductionPowerInput().values());
        List resourcesInput = new ArrayList<ResourceType>(reducedDevelopmentCard.getProductionPowerInput().keySet());
        List coutOutput = new ArrayList<Integer>(reducedDevelopmentCard.getProductionPowerOutput().values());
        List resourcesOutput = new ArrayList<ResourceType>(reducedDevelopmentCard.getProductionPowerOutput().keySet());
        int max = resourcesInput.size();
        if (max < resourcesOutput.size())
            max = resourcesOutput.size();

        for (int i = 0; i < max; i++) {
            if (coutInput.size() >= i + 1) {
                rows.add(COLOR_CARD + "║" + coutInput.get(i) + " " + resourceOutput((ResourceType) resourcesInput.get(i)));
            } else rows.add(COLOR_CARD + "║         ");
            if (max == 1) {
                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_CARD + " > ");
            } else {
                if (i == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_CARD + " ┐ ");
                else if (i == max - 1) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_CARD + " ┘ ");
                else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_CARD + " │ ");
            }
            if (coutOutput.size() >= i + 1) {
                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_CARD + coutOutput.get(i) + " " + resourceOutput((ResourceType) resourcesOutput.get(i)) + COLOR_CARD + "║" + CLIColors.getAnsiReset());
            } else
                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_CARD + "         ║" + CLIColors.getAnsiReset());
            rowCount--;
        }
        for (int i = 0; i < rowCount; i++) {
            rows.add(COLOR_CARD + "║                     ║" + CLIColors.getAnsiReset());
        }

        if (reducedDevelopmentCard.getVictoryPoints() > 9) {
            rows.add(COLOR_CARD + "╚═VP=" + reducedDevelopmentCard.getVictoryPoints() + "═══════════" + CLIColors.getAnsiReset());
        } else
            rows.add(COLOR_CARD + "╚═VP=" + reducedDevelopmentCard.getVictoryPoints() + "════════════" + CLIColors.getAnsiReset());
        if (reducedDevelopmentCard.getId() > 9) {
            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_CARD + "#" + reducedDevelopmentCard.getId() + "═╝" + CLIColors.getAnsiReset());
        } else
            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_CARD + "═#" + reducedDevelopmentCard.getId() + "═╝" + CLIColors.getAnsiReset());

        return rows;
    }

    public void showDevelopmentCard(ReducedDevelopmentCard developmentCard) {
        for (String row : getStringRowsDevelopmentCard(developmentCard)) {
            System.out.println(row);
        }
    }

    public void showDevelopmentCardGrid(List<ReducedDevelopmentCard> developmentCardList) {
        for (String row : getStringDevelopmentCardGrid(developmentCardList)) {
            System.out.println(row);
        }
    }

    public List<String> getStringDevelopmentCardGrid(List<ReducedDevelopmentCard> developmentCardList) {
        List<String> rows = new ArrayList<>();
        List<List<String>> cards = new ArrayList<>();
        for (ReducedDevelopmentCard cardToConvert : developmentCardList) {
            cards.add(getStringRowsDevelopmentCard(cardToConvert));
        }
        //System.out.println(COLOR_TEXT_PRIMARY + "*************************************** DEVELOPMENT CARD GRID ***************************************\n" + CLIColors.getAnsiReset());
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < cards.get(0).size(); j++) {
                rows.add((cards.get(count).get(j)));
                for (int k = count + 1; k < count + 4; k++) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   " + (cards.get(k).get(j)));
                }
                //System.out.print("\n");
            }
            count = count + 4;
        }
        return rows;
    }

    private List<String> getStringRowsLeaderCard(ReducedLeaderCard reducedLeaderCard) {
        List<String> rows = new ArrayList<>();
        rows.add(COLOR_CARD + "╔═════LEADER CARD═════╗" + CLIColors.getAnsiReset());
        rows.add(COLOR_CARD + "║┌─Requirements──────┐║" + CLIColors.getAnsiReset());
        int rowCount = 2;
        for (Map.Entry<ReducedRequirement, Integer> entry : reducedLeaderCard.getRequirements().entrySet()) {
            rows.add(COLOR_CARD + "║│ " + entry.getValue() + " ");
            if (entry.getKey() instanceof ResourceType)
                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceOutput((ResourceType) entry.getKey()) + COLOR_CARD + "         │║" + CLIColors.getAnsiReset());
            else {
                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + typeLevelRequirementOutput((TypeLevel) entry.getKey()) + COLOR_CARD + "   │║" + CLIColors.getAnsiReset());
            }
            rowCount--;
        }
        rows.add(COLOR_CARD + "║└───────────────────┘║" + CLIColors.getAnsiReset());
        for (int i = 0; i < rowCount; i++) {
            rows.add(COLOR_CARD + "║                     ║" + CLIColors.getAnsiReset());
        }

        rows.add(COLOR_CARD + "║┌─Special ability───┐║" + CLIColors.getAnsiReset());
        if (reducedLeaderCard.getSpecialAbility() == SpecialAbilityType.DISCOUNT) {
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.COIN) {
                rows.add(COLOR_CARD + "║│ $ DICOUNT  " + reducedLeaderCard.getSpecialResourceType().getColor() + "coin" + COLOR_CARD + " $ │║" + CLIColors.getAnsiReset());
            }
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.SERVANT) {
                rows.add(COLOR_CARD + "║│$ DICOUNT " + reducedLeaderCard.getSpecialResourceType().getColor() + "servant" + COLOR_CARD + " $ │║" + CLIColors.getAnsiReset());
            }
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.SHIELD) {
                rows.add(COLOR_CARD + "║│$ DICOUNT  " + reducedLeaderCard.getSpecialResourceType().getColor() + "shield" + COLOR_CARD + " $│║" + CLIColors.getAnsiReset());
            }
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.STONE) {
                rows.add(COLOR_CARD + "║│ $ DICOUNT " + reducedLeaderCard.getSpecialResourceType().getColor() + "stone" + COLOR_CARD + " $ │║" + CLIColors.getAnsiReset());
            }
        }
        if (reducedLeaderCard.getSpecialAbility() == SpecialAbilityType.PRODUCTION_POWER) {
            rows.add(COLOR_CARD + "║│ 1 " + resourceOutput(reducedLeaderCard.getSpecialResourceType()) + COLOR_CARD + "┐ ? any ?│║" + CLIColors.getAnsiReset());
            rows.add(COLOR_CARD + "║│          ┘  faith │║" + CLIColors.getAnsiReset());
        }
        if (reducedLeaderCard.getSpecialAbility() == SpecialAbilityType.WHITE_MARBLE) {
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.COIN) {
                rows.add(COLOR_CARD + "║│ * WHITE = " + reducedLeaderCard.getSpecialResourceType().getColor() + "coin" + COLOR_CARD + " *  │║" + CLIColors.getAnsiReset());
            }
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.SERVANT) {
                rows.add(COLOR_CARD + "║│* WHITE = " + reducedLeaderCard.getSpecialResourceType().getColor() + "servant" + COLOR_CARD + " *│║" + CLIColors.getAnsiReset());
            }
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.SHIELD) {
                rows.add(COLOR_CARD + "║│* WHITE = " + reducedLeaderCard.getSpecialResourceType().getColor() + "shield" + COLOR_CARD + " * │║" + CLIColors.getAnsiReset());
            }
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.STONE) {
                rows.add(COLOR_CARD + "║│ * WHITE = " + reducedLeaderCard.getSpecialResourceType().getColor() + "stone" + COLOR_CARD + " * │║" + CLIColors.getAnsiReset());
            }
        }
        if (reducedLeaderCard.getSpecialAbility() == SpecialAbilityType.EXTRADEPOT) {
            rows.add(COLOR_CARD + "║│  EXTRA  DEPOT of  │║" + CLIColors.getAnsiReset());
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.COIN) {
                rows.add(COLOR_CARD + "║│  @     " + reducedLeaderCard.getSpecialResourceType().getColor() + "coin" + COLOR_CARD + "    @  │║" + CLIColors.getAnsiReset());
            }
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.SERVANT) {
                rows.add(COLOR_CARD + "║│  @   " + reducedLeaderCard.getSpecialResourceType().getColor() + "servant" + COLOR_CARD + "   @  │║" + CLIColors.getAnsiReset());
            }
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.SHIELD) {
                rows.add(COLOR_CARD + "║│  @   " + reducedLeaderCard.getSpecialResourceType().getColor() + "shield" + COLOR_CARD + "    @  │║" + CLIColors.getAnsiReset());
            }
            if (reducedLeaderCard.getSpecialResourceType() == ResourceType.STONE) {
                rows.add(COLOR_CARD + "║│  @    " + reducedLeaderCard.getSpecialResourceType().getColor() + "stone" + COLOR_CARD + "    @  │║" + CLIColors.getAnsiReset());
            }
        }
        rows.add(COLOR_CARD + "║└───────────────────┘║" + CLIColors.getAnsiReset());

        if (reducedLeaderCard.getSpecialAbility() == SpecialAbilityType.WHITE_MARBLE || reducedLeaderCard.getSpecialAbility() == SpecialAbilityType.DISCOUNT)
            rows.add(COLOR_CARD + "║                     ║" + CLIColors.getAnsiReset());

        if (reducedLeaderCard.getVictoryPoints() > 9) {
            rows.add(COLOR_CARD + "╚═VP=" + reducedLeaderCard.getVictoryPoints() + "═══════════" + CLIColors.getAnsiReset());
        } else
            rows.add(COLOR_CARD + "╚═VP=" + reducedLeaderCard.getVictoryPoints() + "════════════" + CLIColors.getAnsiReset());
        if (reducedLeaderCard.getId() > 9) {
            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_CARD + "#" + reducedLeaderCard.getId() + "═╝" + CLIColors.getAnsiReset());
        } else
            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_CARD + "═#" + reducedLeaderCard.getId() + "═╝" + CLIColors.getAnsiReset());

        return rows;
    }

    private List<String> getStringRowsCoin() {
        List<String> rows = new ArrayList<>();
        rows.add(ResourceType.COIN.getColor() + " ▒███ " + CLIColors.getAnsiReset());
        rows.add(ResourceType.COIN.getColor() + "▒█████" + CLIColors.getAnsiReset());
        rows.add(ResourceType.COIN.getColor() + "▒█████" + CLIColors.getAnsiReset());
        rows.add(ResourceType.COIN.getColor() + " ▒███ " + CLIColors.getAnsiReset());
        return rows;
    }

    private List<String> getStringRowsServant() {
        List<String> rows = new ArrayList<>();
        rows.add(ResourceType.SERVANT.getColor() + " ▒███ " + CLIColors.getAnsiReset());
        rows.add(ResourceType.SERVANT.getColor() + " ▒███ " + CLIColors.getAnsiReset());
        rows.add(ResourceType.SERVANT.getColor() + "  ▒█  " + CLIColors.getAnsiReset());
        rows.add(ResourceType.SERVANT.getColor() + "▒█████" + CLIColors.getAnsiReset());
        return rows;
    }

    private List<String> getStringRowsShield() {
        List<String> rows = new ArrayList<>();
        rows.add(ResourceType.SHIELD.getColor() + "▒█████" + CLIColors.getAnsiReset());
        rows.add(ResourceType.SHIELD.getColor() + "▒█████" + CLIColors.getAnsiReset());
        rows.add(ResourceType.SHIELD.getColor() + " ▒███ " + CLIColors.getAnsiReset());
        rows.add(ResourceType.SHIELD.getColor() + "  ▒█  " + CLIColors.getAnsiReset());
        return rows;
    }

    private List<String> getStringRowsStone() {
        List<String> rows = new ArrayList<>();
        rows.add(ResourceType.STONE.getColor() + "  ▒█  " + CLIColors.getAnsiReset());
        rows.add(ResourceType.STONE.getColor() + " ▒███ " + CLIColors.getAnsiReset());
        rows.add(ResourceType.STONE.getColor() + "▒████ " + CLIColors.getAnsiReset());
        rows.add(ResourceType.STONE.getColor() + "▒█████" + CLIColors.getAnsiReset());
        return rows;
    }

    private List<String> getStringRowsMarketBalls(ResourceType resourceType) {
        List<String> rows = new ArrayList<>();
        rows.add(resourceType.getColor() + " ███▒ " + CLIColors.getAnsiReset());
        rows.add(resourceType.getColor() + "█████▒" + CLIColors.getAnsiReset());
        rows.add(resourceType.getColor() + "████▒▒" + CLIColors.getAnsiReset());
        rows.add(resourceType.getColor() + " ██▒▒ " + CLIColors.getAnsiReset());
        return rows;
    }

    private List<String> getStringRowsEmpty() {
        List<String> rows = new ArrayList<>();
        rows.add(COLOR_DEPOT + "      " + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "      " + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "      " + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "      " + CLIColors.getAnsiReset());
        return rows;
    }

    private List<String> getStringRowsWarehouse(List<ReducedContainer> wareHouseDepot) {
        List<String> rows = new ArrayList<>();
        rows.add(COLOR_DEPOT + "        ╔══════════╗        " + CLIColors.getAnsiReset());
        List<String> resourceToDisplay = null;
        List<String> resourceEmpty = getStringRowsEmpty();
        if (wareHouseDepot.get(0).getResourceType() != null)
            switch (wareHouseDepot.get(0).getResourceType()) {
                case COIN:
                    resourceToDisplay = getStringRowsCoin();
                    break;
                case SERVANT:
                    resourceToDisplay = getStringRowsServant();
                    break;
                case SHIELD:
                    resourceToDisplay = getStringRowsShield();
                    break;
                case STONE:
                    resourceToDisplay = getStringRowsStone();
                    break;
            }
        for (int i = 0; i < 4; i++) {
            rows.add(COLOR_DEPOT + "        ║  " + CLIColors.getAnsiReset());
            if (wareHouseDepot.get(0).getResourceType() != null)
                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceToDisplay.get(i));
            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceEmpty.get(i));
            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_DEPOT + "  ║        " + CLIColors.getAnsiReset());
        }
        rows.add(COLOR_DEPOT + "    ╔═══╝ ──────── ╚═══╗    " + CLIColors.getAnsiReset());
        if (wareHouseDepot.get(1).getResourceType() != null)
            switch (wareHouseDepot.get(1).getResourceType()) {
                case COIN:
                    resourceToDisplay = getStringRowsCoin();
                    break;
                case SERVANT:
                    resourceToDisplay = getStringRowsServant();
                    break;
                case SHIELD:
                    resourceToDisplay = getStringRowsShield();
                    break;
                case STONE:
                    resourceToDisplay = getStringRowsStone();
                    break;
            }
        for (int i = 0; i < 4; i++) {
            rows.add(COLOR_DEPOT + "    ║  " + CLIColors.getAnsiReset());
            if (wareHouseDepot.get(1).getResourceType() != null) {
                for (int j = 0; j < wareHouseDepot.get(1).getCount(); j++) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceToDisplay.get(i) + "  ");
                }
                for (int j = 0; j < 2 - wareHouseDepot.get(1).getCount(); j++) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceEmpty.get(i) + "  ");
                }
            } else
                for (int j = 0; j < 2; j++) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceEmpty.get(i) + "  ");
                }
            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_DEPOT + "║    " + CLIColors.getAnsiReset());
        }
        rows.add(COLOR_DEPOT + "╔═══╝ ──────────────── ╚═══╗" + CLIColors.getAnsiReset());
        if (wareHouseDepot.get(2).getResourceType() != null)
            switch (wareHouseDepot.get(2).getResourceType()) {
                case COIN:
                    resourceToDisplay = getStringRowsCoin();
                    break;
                case SERVANT:
                    resourceToDisplay = getStringRowsServant();
                    break;
                case SHIELD:
                    resourceToDisplay = getStringRowsShield();
                    break;
                case STONE:
                    resourceToDisplay = getStringRowsStone();
                    break;
            }
        for (int i = 0; i < 4; i++) {
            rows.add(COLOR_DEPOT + "║  " + CLIColors.getAnsiReset());
            if (wareHouseDepot.get(2).getResourceType() != null) {
                for (int j = 0; j < wareHouseDepot.get(2).getCount(); j++) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceToDisplay.get(i) + "  ");
                }
                for (int j = 0; j < 3 - wareHouseDepot.get(2).getCount(); j++) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceEmpty.get(i) + "  ");
                }
            } else
                for (int j = 0; j < 3; j++) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceEmpty.get(i) + "  ");
                }
            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_DEPOT + "║" + CLIColors.getAnsiReset());
        }
        rows.add(COLOR_DEPOT + "╠══════════════════════════╣" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║      WAREHOUSE DEPOT     ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "╚══════════════════════════╝" + CLIColors.getAnsiReset());
        return rows;
    }

    private List<String> getStringRowsStrongBox(Map<ResourceType, Integer> strongBoxDepot) {
        List<String> rows = new ArrayList<>();
        List<String> coin = getStringRowsCoin();
        List<String> servant = getStringRowsServant();
        List<String> shield = getStringRowsShield();
        List<String> stone = getStringRowsStone();
        String coinCount, servantCount, shieldCount, stoneCount;
        if (strongBoxDepot.get(ResourceType.COIN) > 9)
            coinCount = "x" + strongBoxDepot.get(ResourceType.COIN);
        else coinCount = "x" + strongBoxDepot.get(ResourceType.COIN) + " ";
        if (strongBoxDepot.get(ResourceType.SERVANT) > 9)
            servantCount = "x" + strongBoxDepot.get(ResourceType.SERVANT);
        else servantCount = "x" + strongBoxDepot.get(ResourceType.SERVANT) + " ";
        if (strongBoxDepot.get(ResourceType.SHIELD) > 9)
            shieldCount = "x" + strongBoxDepot.get(ResourceType.SHIELD);
        else shieldCount = "x" + strongBoxDepot.get(ResourceType.SHIELD) + " ";
        if (strongBoxDepot.get(ResourceType.STONE) > 9)
            stoneCount = "x" + strongBoxDepot.get(ResourceType.STONE);
        else stoneCount = "x" + strongBoxDepot.get(ResourceType.STONE) + " ";
        rows.add(COLOR_DEPOT + "╔════════════════════════════════╗" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║  " + coin.get(0) + COLOR_DEPOT + "        " + servant.get(0) + COLOR_DEPOT + "          ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║  " + coin.get(1) + COLOR_DEPOT + " coin   " + servant.get(1) + COLOR_DEPOT + " servant  ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║  " + coin.get(2) + COLOR_DEPOT + " " + coinCount + "    " + servant.get(2) + COLOR_DEPOT + " " + servantCount + "      ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║  " + coin.get(3) + COLOR_DEPOT + "        " + servant.get(3) + COLOR_DEPOT + "          ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║                                ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║  " + shield.get(0) + COLOR_DEPOT + "        " + stone.get(0) + COLOR_DEPOT + "          ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║  " + shield.get(1) + COLOR_DEPOT + " shield " + stone.get(1) + COLOR_DEPOT + " stone    ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║  " + shield.get(2) + COLOR_DEPOT + " " + shieldCount + "    " + stone.get(2) + COLOR_DEPOT + " " + stoneCount + "      ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║  " + shield.get(3) + COLOR_DEPOT + "        " + stone.get(3) + COLOR_DEPOT + "          ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "╠════════════════════════════════╣" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "║           STRONGBOX            ║" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "╚════════════════════════════════╝" + CLIColors.getAnsiReset());
        return rows;
    }

    private List<String> getStringRowsSpecial(ResourceType resourceType, int count) {
        List<String> rows = new ArrayList<>();
        String bottomLabel = "";
        List<String> resourceToDisplay = null;
        List<String> resourceEmpty = getStringRowsEmpty();
        switch (resourceType) {
            case COIN:
                resourceToDisplay = getStringRowsCoin();
                bottomLabel = "║    EXTRA COIN    ║";
                break;
            case SERVANT:
                resourceToDisplay = getStringRowsServant();
                bottomLabel = "║  EXTRA  SERVANT  ║";
                break;
            case SHIELD:
                resourceToDisplay = getStringRowsShield();
                bottomLabel = "║   EXTRA SHIELD   ║";
                break;
            case STONE:
                resourceToDisplay = getStringRowsStone();
                bottomLabel = "║   EXTRA  STONE   ║";
                break;
        }
        rows.add(COLOR_DEPOT + "╔══════════════════╗" + CLIColors.getAnsiReset());
        for (int i = 0; i < 4; i++) {
            rows.add(COLOR_DEPOT + "║  " + CLIColors.getAnsiReset());
            for (int j = 0; j < count; j++) {
                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceToDisplay.get(i) + "  ");
            }
            for (int j = 0; j < 2 - count; j++) {
                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + resourceEmpty.get(i) + "  ");
            }
            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_DEPOT + "║" + CLIColors.getAnsiReset());
        }
        rows.add(COLOR_DEPOT + "╠══════════════════╣" + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + bottomLabel + CLIColors.getAnsiReset());
        rows.add(COLOR_DEPOT + "╚══════════════════╝" + CLIColors.getAnsiReset());

        return rows;
    }

    private List<String> getStringRowsMarketTray() {
        ResourceType marketTray[][] = reducedGameModel.getMarketTray().getMarketTray();
        ResourceType slide = reducedGameModel.getMarketTray().getSlide();
        List<String> rows = new ArrayList<>();
        rows.add(COLOR_MARKET_TRAY + "╔═════════════════════════════════════╗            " + CLIColors.getAnsiReset());
        List<String> slideToDisplay = getStringRowsMarketBalls(slide);
        for (int i = 0; i < slideToDisplay.size(); i++) {
            rows.add(COLOR_MARKET_TRAY + "║                               " + getStringRowsMarketBalls(slide).get(i) + COLOR_MARKET_TRAY + "║            " + CLIColors.getAnsiReset());
        }
        rows.add(COLOR_MARKET_TRAY + "║      ╔══════════════════════════════╣            " + CLIColors.getAnsiReset());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                rows.add(COLOR_MARKET_TRAY + "║      ║" + getStringRowsMarketBalls(marketTray[i][0]).get(j) + "  " + getStringRowsMarketBalls(marketTray[i][1]).get(j) + "  " + getStringRowsMarketBalls(marketTray[i][2]).get(j) + "  " + getStringRowsMarketBalls(marketTray[i][3]).get(j));
                switch (j) {
                    case 0:
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_MARKET_TRAY + "║    ▄█      " + CLIColors.getAnsiReset());
                        break;
                    case 1:
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_MARKET_TRAY + "║  ▄█████ ROW" + CLIColors.getAnsiReset());
                        break;
                    case 2:
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_MARKET_TRAY + "║  ▀█████  " + (i + 1) + " " + CLIColors.getAnsiReset());
                        break;
                    case 3:
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_MARKET_TRAY + "║    ▀█      " + CLIColors.getAnsiReset());
                        break;
                }
            }
            switch (i) {
                case 0:
                case 1:
                    rows.add(COLOR_MARKET_TRAY + "║      ║                              ║            " + CLIColors.getAnsiReset());
                    break;
                case 2:
                    rows.add(COLOR_MARKET_TRAY + "╚══════╩══════════════════════════════╝            " + CLIColors.getAnsiReset());
                    break;
            }
        }
        rows.add(COLOR_MARKET_TRAY + "         ▄██▄    ▄██▄    ▄██▄    ▄██▄              " + CLIColors.getAnsiReset());
        rows.add(COLOR_MARKET_TRAY + "        ▄████▄  ▄████▄  ▄████▄  ▄████▄             " + CLIColors.getAnsiReset());
        rows.add(COLOR_MARKET_TRAY + "        ▀▀██▀▀  ▀▀██▀▀  ▀▀██▀▀  ▀▀██▀▀             " + CLIColors.getAnsiReset());
        rows.add(COLOR_MARKET_TRAY + "          ██      ██      ██      ██               " + CLIColors.getAnsiReset());
        rows.add(COLOR_MARKET_TRAY + "        COL 1   COL 2   COL 3   COL 4              " + CLIColors.getAnsiReset());
        return rows;
    }

    private List<String> getStringRowsFaithTrack(Map<Colors, Integer> players) {
        List<String> rows = new ArrayList<>();
        rows.add(COLOR_FAITH_TRACK + "                            ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄                                                       ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ " + CLIColors.getAnsiReset());
        rows.add(COLOR_FAITH_TRACK + "                  ╔════════╦▌═══════╦════════╦════════╦═══════▐╦════════╗                                   ╔════════╦▌═══════╦════════╦════════╦════════╦════════╦═══════▐╗" + CLIColors.getAnsiReset());

        for (int j = 0; j < 2; j++) {
            rows.add(COLOR_FAITH_TRACK + "                  ║" + CLIColors.getAnsiReset());
            for (int i = 4; i < 25; i++) {
                if (i == 10) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "                                   ║");
                    i = 17;
                } else {
                    if (players.containsKey(Colors.RED) && players.get(Colors.RED) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.RED, j));
                    else if (j == 0) switch (i) {
                        case 5:
                        case 19:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "▌  ");
                            break;
                        case 6:
                        case 9:
                        case 18:
                        case 21:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "  █");
                            break;
                        case 24:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + " █▀");
                            break;
                        default:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                    }
                    else switch (i) {
                            case 5:
                            case 19:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "▌  ");
                                break;
                            case 9:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "  █");
                                break;
                            case 18:
                            case 21:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + " ██");
                                break;
                            default:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                        }
                    if (j == 0)
                        switch (i) {
                            case 6:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▀▀");
                                break;
                            case 18:
                            case 21:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " █");
                                break;
                            case 24:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "█▐");
                                break;
                            default:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ");
                                break;
                        }
                    else if (i == 6 || i == 9 || i == 18)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ");
                    else if (i == 21) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " █");
                    else if (i == 24) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "██");
                    else if (i > 9) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + i);
                    else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + i + " ");


                    if (players.containsKey(Colors.GREEN) && players.get(Colors.GREEN) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.GREEN, j));
                    else switch (i) {
                        case 6:
                        case 9:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "█  ");
                            break;
                        case 18:
                            if (j == 0)
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▀█ ");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " █ ");
                            break;
                        case 21:
                            if (j == 0)
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▀▀ ");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "   ");
                            break;
                        case 24:
                            if (j == 0)
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "█▌▐");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " █▐");
                            break;
                        case 8:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ▐");
                            break;
                        default:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "   ");
                    }
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "║");
                }
            }
        }

        for (int j = 0; j < 2; j++) {
            rows.add(COLOR_FAITH_TRACK + "                  ║" + CLIColors.getAnsiReset());
            for (int i = 4; i < 25; i++) {
                if (i == 10) {
                    if (j == 0)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "           █▀▀▀▀▀▀▀▀▀▀▀█           ║");
                    else
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "           ▌▒▒▒▒▒▒▒▒▒▒▒▐           ║");
                    i = 17;
                } else {
                    if (players.containsKey(Colors.BLUE) && players.get(Colors.BLUE) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.BLUE, j));
                    else if (j == 0) switch (i) {
                        case 5:
                        case 19:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▌  ");
                            break;
                        case 9:
                        case 18:
                        case 21:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  █");
                            break;
                        case 24:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " ▄█");
                            break;
                        default:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "   ");
                    }
                    else switch (i) {
                            case 5:
                            case 19:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▌  ");
                                break;
                            case 6:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  █");
                                break;
                            case 18:
                            case 21:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " ▄█");
                                break;
                            case 24:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " █▄");
                                break;
                            default:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "   ");
                        }

                    switch (i) {
                        case 6:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄█");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "█▄");
                            break;
                        case 9:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "██");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ");
                            break;
                        case 18:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " ▄");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄█");
                            break;
                        case 21:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " █");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄█");
                            break;
                        case 24:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " █");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄▐");
                            break;
                        default:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ");
                    }

                    if (players.containsKey(Colors.YELLOW) && players.get(Colors.YELLOW) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.YELLOW, j));
                    else switch (i) {
                        case 6:
                            if (j == 0)
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "   ");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄  ");
                            break;
                        case 8:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ▐");
                            break;
                        case 9:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "█  ");
                            break;
                        case 18:
                            if (j == 0)
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "█  ");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄▄ ");
                            break;
                        case 21:
                            if (j == 0)
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▀█ ");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄█ ");
                            break;
                        case 24:
                            if (j == 0)
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + " █▐");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "█▌▐");
                            break;
                        default:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "   ");
                    }
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "║");
                }
            }
        }

        rows.add(COLOR_FAITH_TRACK + "                  ╠════════╬▌═══════╩════════╩════════╩═══════▐╬════════╣           ▌▒▒▒▒▒▒▒▒▒▒▒▐           ╠════════╬▌═══════╩════════╩════════╩════════╩════════╩═══════▐╝" + CLIColors.getAnsiReset());

        for (int j = 0; j < 2; j++) {
            rows.add(COLOR_FAITH_TRACK + "                  ║" + CLIColors.getAnsiReset());
            for (int i = 3; i < 19; i++) {
                if (i == 4) {
                    if (j == 0)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▀▀▀▀▀▀▀▀▀▀▀█▀▀▀▀▀▀▀▀▀▀▀█▀▀▀▀▀▀▀▀▀▀▀║");
                    else
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "           ▌▒▒▒▒▒▒▒▒▒▒▒▐           ║");
                    i = 9;
                } else if (i == 11) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "           ▌▒▒▒▒▒▒▒▒▒▒▒▐           ║");
                    i = 16;
                } else if (i == 18) {
                    if (j == 0)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█▀▀▀▀▀▀▀▀▀▀▀█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀ ");
                    else
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "                    ▌▒▒▒▒▒▒▒▒▒▒▒▐                     ");
                } else {
                    if (players.containsKey(Colors.RED) && players.get(Colors.RED) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.RED, j));
                    else if (i != 3 || j != 1) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                    else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  █");
                    if (i == 3) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "█ ");
                    else if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ");
                    else if (i > 9) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + i);
                    else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + i + " ");


                    if (players.containsKey(Colors.GREEN) && players.get(Colors.GREEN) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.GREEN, j));
                    else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "║");
                }
            }
        }

        for (int j = 0; j < 2; j++) {
            rows.add(COLOR_FAITH_TRACK + "                  ║" + CLIColors.getAnsiReset());
            for (int i = 3; i < 19; i++) {
                if (i == 4) {

                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "           ▌▒▒▒▒▒▒▒▒▒▒▒▐           ║");
                    i = 9;
                } else if (i == 11) {
                    if (j == 0)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "           ▌▒▒▒▒▒▒▒▒▒▒▒▐           ║");
                    else
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄▄▄▄▄▄▄▄▄▄▄█▄▄▄▄▄▄▄▄▄▄▄█▄▄▄▄▄▄▄▄▄▄▄▄");
                    i = 16;
                } else if (i == 18) {
                    if (j == 0)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "                    ▌▒▒▒▒▒▒▒▒▒▒▒▐                     ");
                    else
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄                   ▌▒▒▒▒▒▒▒▒▒▒▒▐                     ");
                } else {
                    if (players.containsKey(Colors.BLUE) && players.get(Colors.BLUE) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.BLUE, j));
                    else if (i == 17 && j == 1)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄▄▄");
                    else if (i == 3 && j == 1)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ▄");
                    else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "   ");

                    if (i == 17 && j == 1)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄▄");
                    else if (i == 3 && j == 0)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "█ ");
                    else if (i == 3 && j == 1)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "█▄");
                    else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ");

                    if (players.containsKey(Colors.YELLOW) && players.get(Colors.YELLOW) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.YELLOW, j));
                    else if (i != 17 || j != 1)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "   ");
                    else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄▄▄");
                    if (i != 17 || j != 1)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "║");
                    else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄");
                }
            }
        }

        rows.add(COLOR_FAITH_TRACK + "╔════════╦════════╬════════╣           ▌▒▒▒▒▒▒▒▒▒▒▒▐           ╠════════╬▌═══════╦════════╦════════╦════════╬════════╣▐                   ▌▒▒▒▒▒▒▒▒▒▒▒▐                     " + CLIColors.getAnsiReset());

        for (int j = 0; j < 2; j++) {
            rows.add(COLOR_FAITH_TRACK + "║");
            for (int i = 0; i < 18; i++) {
                if (i == 3) {
                    if (j == 0)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "           ▌▒▒▒▒▒▒▒▒▒▒▒▐           ║");
                    else
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "           █▄▄▄▄▄▄▄▄▄▄▄█           ║");
                    i = 10;
                } else if (i == 17) {
                    if (j == 0)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▐                   ▌▒▒▒▒▒▒▒▒▒▒▒▐                     ");
                    else
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▐                   █▄▄▄▄▄▄▄▄▄▄▄█                     ");
                } else {
                    if (players.containsKey(Colors.RED) && players.get(Colors.RED) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.RED, j));
                    else switch (i) {
                        case 0:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + " ▀▀");
                            break;
                        case 12:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "▌ █");
                            break;
                        case 15:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "  █");
                            break;
                        default:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                    }
                    switch (i) {
                        case 0:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "██");
                            break;
                        case 12:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▀▀");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ");
                            break;
                        case 15:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▀▀");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄▄");
                            break;
                        default:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ");
                            else if (i > 9)
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + i);
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + i + " ");
                    }
                    if (players.containsKey(Colors.GREEN) && players.get(Colors.GREEN) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.GREEN, j));
                    else switch (i) {
                        case 0:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "▀▀ ");
                            break;
                        case 12:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "▀  ");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                            break;
                        case 15:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "█  ");
                            break;
                        default:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                    }
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "║");
                }
            }
        }

        for (int j = 0; j < 2; j++) {
            rows.add(COLOR_FAITH_TRACK + "║");
            for (int i = 0; i < 18; i++) {
                if (i == 3) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "                                   ║");
                    i = 10;
                } else if (i == 17) {
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▐                                                     ");
                } else {
                    if (players.containsKey(Colors.BLUE) && players.get(Colors.BLUE) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.BLUE, j));
                    else switch (i) {
                        case 0:
                        case 15:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "  ▄");
                            break;
                        case 12:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "▌ █");
                            break;
                        default:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                    }

                    switch (i) {
                        case 0:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "██");
                            break;
                        case 12:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▀▀");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄▄");
                            break;
                        case 15:
                            if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ");
                            else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "▄▄");
                            break;
                        default:
                            rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "  ");
                    }

                    if (players.containsKey(Colors.YELLOW) && players.get(Colors.YELLOW) == i)
                        rows.set(rows.size() - 1, rows.get(rows.size() - 1) + getStringRowTrackMarker(Colors.YELLOW, j));
                    else
                        switch (i) {
                            case 0:
                                if (j == 0) rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                                else rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "▄  ");
                                break;
                            case 12:
                            case 15:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "█  ");
                                break;
                            default:
                                rows.set(rows.size() - 1, rows.get(rows.size() - 1) + "   ");
                        }
                    rows.set(rows.size() - 1, rows.get(rows.size() - 1) + COLOR_FAITH_TRACK + "║");
                }
            }
        }

        rows.add(COLOR_FAITH_TRACK + "╚════════╩════════╩════════╝                                   ╚════════╩▌═══════╩════════╩════════╩════════╩════════╝▐                                                     " + CLIColors.getAnsiReset());
        rows.add(COLOR_FAITH_TRACK + "                                                                         ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀                                                     " + CLIColors.getAnsiReset());

        return rows;
    }

    private String getStringRowTrackMarker(Colors colors, int numberRow) {
        String row = null;
        switch (colors) {
            case RED:
                row = CLIColors.getAnsiRed();
                break;
            case BLUE:
                row = CLIColors.getAnsiBlue();
                break;
            case GREEN:
                row = CLIColors.getAnsiGreen();
                break;
            case YELLOW:
                row = CLIColors.getAnsiYellow();
                break;
        }
        switch (numberRow) {
            case 0:
                row = row + "▐█▌";
                break;
            case 1:
                row = row + "███";
                break;
        }
        row = row + CLIColors.getAnsiReset();
        return row;
    }

    public void showFaithTrack(Map<Colors, Integer> players) {
        for (String row : getStringRowsFaithTrack(players)) {
            System.out.println(row);
        }
    }

    public void showSpecial() {
        List<List<String>> specialRows = new ArrayList<>();
        for (Map.Entry<ResourceType, Integer> entry : reducedGameModel.getSpecialDepot().entrySet()) {
            specialRows.add(getStringRowsSpecial(entry.getKey(), entry.getValue()));
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < specialRows.size(); j++) {
                System.out.print(specialRows.get(j).get(i) + "  ");
            }
            System.out.print("\n");
        }
    }

    public void showWarehouse() {
        for (String row : getStringRowsWarehouse(reducedGameModel.getWareHouseDepot())) {
            System.out.println(row);
        }
    }

    public void showStrongBox() {
        for (String row : getStringRowsStrongBox(reducedGameModel.getStrongBoxDepot())) {
            System.out.println(row);
        }
    }

    private String typeLevelRequirementOutput(TypeLevel typeLevel) {
        String output = null;
        output = typeLevel.getType().getColor();
        if (typeLevel.getType() == Colors.GREEN) {
            output = output + "green";
        }
        if (typeLevel.getType() == Colors.YELLOW) {
            output = output + "yellow";
        }
        if (typeLevel.getType() == Colors.PURPLE) {
            output = output + "purple";
        }
        if (typeLevel.getType() == Colors.BLUE) {
            output = output + "blue";
        }
        output = output + CLIColors.getAnsiReset();
        if (typeLevel.getLevel() > 0)
            output = output + COLOR_CARD + " - LV " + typeLevel.getLevel() + CLIColors.getAnsiReset();
        else output = output + "      ";
        if (typeLevel.getType() == Colors.GREEN) {
            output = output + " ";
        }
        if (typeLevel.getType() == Colors.BLUE) {
            output = output + "  ";
        }
        return output;
    }

    private String typeOutput(Colors color) {
        String output = null;
        output = color.getColor();
        if (color == Colors.GREEN) {
            output = output + " GREEN";
        }
        if (color == Colors.YELLOW) {
            output = output + "YELLOW";
        }
        if (color == Colors.PURPLE) {
            output = output + "PURPLE";
        }
        if (color == Colors.BLUE) {
            output = output + "  BLUE";
        }
        output = output + CLIColors.getAnsiReset();
        return output;
    }

    private String resourceOutput(ResourceType resourceType) {
        String output = null;
        output = resourceType.getColor();
        switch (resourceType.toString()) {
            case "COIN":
                output = output + "coin   ";
            case "STONE":
                output = output + "stone  ";
            case "SHIELD":
                output = output + "shield ";
            case "SERVANT":
                output = output + "servant";
            case "FAITH":
                output = output + "faith  ";
            case "ANY":
                output = output + "any    ";
        }
        output = output + CLIColors.getAnsiReset();
        return output;
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void waitMilliseconds(int mill) {
        try {
            TimeUnit.MILLISECONDS.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
