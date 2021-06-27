package it.polimi.ingsw.utils.parsers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.server.model.cards.*;
import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.*;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.server.model.resources.*;
import it.polimi.ingsw.server.model.specialAbilities.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SettingsParser {
    private final static SettingsParser INSTANCE = new SettingsParser();

    private String json =  "src/main/resources/JSONs/settings.json";

    private SettingsParser() {
    }

    public static SettingsParser getInstance() {
        return INSTANCE;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public MarketTray getMarketTray() throws FileNotFoundException {

        JsonObject jsonObject = JsonParser.parseReader(new FileReader(json)).getAsJsonObject().get("MarketTray").getAsJsonObject();

        List<ResourceType> marblesList = new ArrayList<>();
        ResourceType slide;

        jsonObject.get("marbles").getAsJsonObject().entrySet().forEach( entry -> {
                    for (int i = 0; i < entry.getValue().getAsInt(); i++) {
                        marblesList.add(ResourceType.parse(entry.getKey()));
                    }
                }
        );

        Collections.shuffle(marblesList);

        int nRows = jsonObject.get("nRows").getAsInt();
        int nCols = jsonObject.get("nCols").getAsInt();
        Resource[][] tmpMarketTray = new Resource[nRows][nCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                tmpMarketTray[i][j] = ResourceType.getResourceClass(marblesList.get(i + j + ((nCols - 1) * i)));
            }
        }
        slide = marblesList.get(marblesList.size() - 1);

        return new MarketTray(tmpMarketTray, ResourceType.getResourceClass(slide));

    }

    public List<DevelopmentCard> getDevelopmentCards() throws FileNotFoundException {
        List<DevelopmentCard> developmentCards = new ArrayList<>();

        JsonArray tempDeck = JsonParser.parseReader(new FileReader(json)).getAsJsonObject().getAsJsonArray("DevelopmentCards");

        for(JsonElement element: tempDeck){

            Cost cost = new Cost();
            element.getAsJsonObject().get("cost").getAsJsonObject()
                    .entrySet()
                    .forEach( entry -> cost.add(ResourceType.parse(entry.getKey()), entry.getValue().getAsInt()) );

            ProductionPowerInput productionPowerInput = new ProductionPowerInput();
            element.getAsJsonObject().get("productionPower").getAsJsonObject().get("productionPowerInput").getAsJsonObject()
                    .entrySet()
                    .forEach(entry -> productionPowerInput.add(ResourceType.getResourceClass(ResourceType.parse(entry.getKey())),entry.getValue().getAsInt()));

            ProductionPowerOutput productionPowerOutput = new ProductionPowerOutput();
            element.getAsJsonObject().get("productionPower").getAsJsonObject().get("productionPowerOutput").getAsJsonObject()
                    .entrySet()
                    .forEach(entry -> productionPowerOutput.add(ResourceType.parse(entry.getKey()),entry.getValue().getAsInt()));

            ProductionPower productionPower = new ProductionPower(productionPowerInput, productionPowerOutput);

            TypeLevel typeLevel = new TypeLevel(Colors.parse(element.getAsJsonObject().get("typeLevel").getAsJsonObject().get("type").getAsString()),
                    element.getAsJsonObject().get("typeLevel").getAsJsonObject().get("level").getAsInt());



            developmentCards.add(new DevelopmentCard(element.getAsJsonObject().get("id").getAsInt(),
                    cost,typeLevel,productionPower,element.getAsJsonObject().get("victoryPoints").getAsInt()));


        }

        return developmentCards;


    }

    public ProductionPower getBasicProductionPower() throws FileNotFoundException {

        JsonObject jsonObject = JsonParser.parseReader(new FileReader(json)).getAsJsonObject().getAsJsonObject("BoardBasicProductionPower");
        JsonArray tmpInput = jsonObject.getAsJsonArray("productionPowerInput");
        JsonArray tmpOutput = jsonObject.getAsJsonArray("productionPowerOutput");

        ProductionPowerInput input = new ProductionPowerInput();
        ProductionPowerOutput output = new ProductionPowerOutput();

        for (int i = 0; i < tmpInput.size(); i++) {
            switch (tmpInput.get(i).getAsJsonObject().get("resourceType").getAsString()) {
                case "ANY":
                    input.add(ResourceType.getResourceClass(ResourceType.ANY), tmpInput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "COIN":
                    input.add(ResourceType.getResourceClass(ResourceType.COIN), tmpInput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "STONE":
                    input.add(ResourceType.getResourceClass(ResourceType.STONE), tmpInput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "SHIELD":
                    input.add(ResourceType.getResourceClass(ResourceType.SHIELD), tmpInput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "SERVANT":
                    input.add(ResourceType.getResourceClass(ResourceType.SERVANT), tmpInput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
            }
        }

        for (int i = 0; i < tmpOutput.size(); i++) {
            String s = tmpOutput.get(i).getAsJsonObject().get("resourceType").getAsString();
            switch (s) {
                case "ANY":
                    output.add(ResourceType.ANY, tmpOutput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "COIN":
                    output.add(ResourceType.COIN, tmpOutput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "STONE":
                    output.add(ResourceType.STONE, tmpOutput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "SHIELD":
                    output.add(ResourceType.SHIELD, tmpOutput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "SERVANT":
                    output.add(ResourceType.SERVANT, tmpOutput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
            }

        }


        return new ProductionPower(input, output);


    }

    public FaithTrack getFaithTrack(){
        List<Cell> track = new ArrayList<>();
        List<VaticanReportSection> reportSections = new ArrayList<>();

        JsonObject jsonObject = null;
        try {
            jsonObject = JsonParser.parseReader(new FileReader(json)).getAsJsonObject().getAsJsonObject("FaithTrack");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonArray tmpTrack = jsonObject.getAsJsonArray("track");
        JsonArray tmpReport = jsonObject.getAsJsonArray("vaticanReportSections");

        for (int i = 0; i < tmpTrack.size(); i++) {
            String type = tmpTrack.get(i).getAsJsonObject().get("type").getAsString();
            switch (type) {
                case "NORMAL":
                    track.add(new Cell(tmpTrack.get(i).getAsJsonObject().get("victoryPoints").getAsInt()));
                    break;
                case "POPE":
                    track.add(new PopeSpaceCell(tmpTrack.get(i).getAsJsonObject().get("victoryPoints").getAsInt()));
                    break;
                case "FINAL":
                    track.add(new FinalCell(tmpTrack.get(i).getAsJsonObject().get("victoryPoints").getAsInt()));
                    break;
            }
        }

        for (int i = 0; i < tmpReport.size(); i++) {
            List<Cell> tempCells = new ArrayList<>();
            for (int j = 0; j < tmpReport.get(i).getAsJsonObject().get("cells").getAsJsonArray().size(); j++) {
                tempCells.add(track.get(tmpReport.get(i).getAsJsonObject().get("cells").getAsJsonArray().get(j).getAsInt()));

            }
            reportSections.add(new VaticanReportSection(tempCells, tmpReport.get(i).getAsJsonObject().get("victoryPoints").getAsInt()));
        }

        return new FaithTrack(track, reportSections);

    }

    public List<LeaderCard> getLeaderCards(){
        List<LeaderCard> leaderCards = new ArrayList<>();

        JsonArray tempDeck = null;
        try {
            tempDeck = JsonParser.parseReader(new FileReader(json)).getAsJsonObject().getAsJsonArray("LeaderCards");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(JsonElement element: Objects.requireNonNull(tempDeck)) {

            //Requirements
            LeaderRequirements leaderRequirements = new LeaderRequirements();
            element.getAsJsonObject().get("requirements").getAsJsonObject()
                    .entrySet().forEach(entry -> {
                if (ResourceType.contains(entry.getKey())) {
                    leaderRequirements.add((ConcreteResource) ResourceType.getResourceClass(ResourceType.valueOf(entry.getKey())), entry.getValue().getAsInt());
                } else {
                    String type = entry.getKey().substring(0, entry.getKey().indexOf(' '));
                    int level = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf(' ') + 1));
                    leaderRequirements.add(new TypeLevel(Colors.valueOf(type), level), entry.getValue().getAsInt());
                }
            });

            //Special Ability
            SpecialAbility specialAbility = null;
            try {
                specialAbility = getSpecialAbility(element.getAsJsonObject().get("specialAbility").getAsJsonObject().get("resource").getAsString(), "src/main/resources/JSONs/settings.json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            leaderCards.add(new LeaderCard(element.getAsJsonObject().get("id").getAsInt(), leaderRequirements, element.getAsJsonObject().get("victoryPoints").getAsInt(), specialAbility));


        }




        return leaderCards;
    }

    public SpecialAbility getSpecialAbility(String specialAbilityType, String  resourceType) throws FileNotFoundException {

        JsonObject SpecialAbilityAttribute = JsonParser.parseReader(new FileReader(json)).getAsJsonObject()
                .get("SpecialAbilities").getAsJsonObject();


        switch (specialAbilityType){
            case "DISCOUNT":
                return new SpecialDiscount(ResourceType.getResourceClass(ResourceType.parse(resourceType)),SpecialAbilityAttribute.get("DISCOUNT").getAsInt());
            case "DEPOT":
                return new SpecialExtraDepot(ResourceType.getResourceClass(ResourceType.parse(resourceType)), SpecialAbilityAttribute.get("DEPOT").getAsInt());
            case "WHITE_MARBLE":
                return new SpecialWhiteMarble(ResourceType.getResourceClass(ResourceType.parse(resourceType)), SpecialAbilityAttribute.get("WHITE_MARBLE").getAsInt());
            case "PRODUCTION_POWER":
                ProductionPowerInput input = new ProductionPowerInput();
                ProductionPowerOutput output = new ProductionPowerOutput();

                input.add(ResourceType.getResourceClass(ResourceType.parse(resourceType)), SpecialAbilityAttribute.get("PRODUCTION_POWER").getAsJsonObject().get("input").getAsInt());
                SpecialAbilityAttribute.get("PRODUCTION_POWER").getAsJsonObject().get("output").getAsJsonObject().entrySet().forEach(entry -> output.add(ResourceType.parse(entry.getKey()), entry.getValue().getAsInt()) );

                return new SpecialAdditionalProductionPower(ResourceType.getResourceClass(ResourceType.parse(resourceType)), new ProductionPower(input,output));

        }

        return new SpecialDiscount(Coin.getInstance(),1);
    }
}
