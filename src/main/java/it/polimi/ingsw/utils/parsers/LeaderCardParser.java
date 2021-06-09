package it.polimi.ingsw.utils.parsers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.cards.LeaderRequirements;
import it.polimi.ingsw.server.model.cards.TypeLevel;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.resources.ConcreteResource;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.server.model.specialAbilities.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LeaderCardParser {

    public static List<LeaderCard> getLeaderCards() {
        List<LeaderCardTemp> leaderCardTempList = null;
        List<LeaderCard> leaderCardList = new ArrayList<>();
        Type listOfMyClassObject = new TypeToken<ArrayList<LeaderCardTemp>>() {
        }.getType();

        Gson gson = new Gson();

        try {
            leaderCardTempList = gson.fromJson(new FileReader("src/main/resources/JSONs/LeaderCard.json"), listOfMyClassObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (LeaderCardTemp leaderCardTemp : leaderCardTempList) {

            LeaderRequirements leaderRequirements = new LeaderRequirements();
            for (Map.Entry<String, Integer> entry : leaderCardTemp.requirements.entrySet()) {
                if (ResourceType.contains(entry.getKey())) {

                    leaderRequirements.add((ConcreteResource) ResourceType.getResourceClass(ResourceType.valueOf(entry.getKey())), entry.getValue());
                } else {
                    String type = entry.getKey().substring(0, entry.getKey().indexOf(' '));
                    int level = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf(' ') + 1));
                    leaderRequirements.add(new TypeLevel(Colors.valueOf(type), level), entry.getValue());

                }
            }
            SpecialAbility specialAbility = null;
            Resource resource = ResourceType.getResourceClass(ResourceType.valueOf(leaderCardTemp.specialAbility.resource));
            switch (leaderCardTemp.specialAbility.type) {
                case "DISCOUNT":
                    specialAbility = new SpecialDiscount(resource);
                    break;
                case "DEPOT":
                    specialAbility = new SpecialExtraDepot(resource);
                    break;
                case "WHITEMARBLE":
                    specialAbility = new SpecialWhiteMarble(resource);
                    break;
                case "PRODUCTIONPOWER":
                    specialAbility = new SpecialAdditionalProductionPower(resource);
                    break;

            }


            leaderCardList.add(new LeaderCard(leaderCardTemp.id, leaderRequirements, leaderCardTemp.victoryPoints, specialAbility));
        }

        Collections.shuffle(leaderCardList);

        return leaderCardList;
    }

    private static class SpecialAbilityTemp {
        String type;
        String resource;

        public SpecialAbilityTemp(String type, String resource) {
            this.type = type;
            this.resource = resource;
        }
    }

    private static class LeaderCardTemp {
        int id;
        Map<String, Integer> requirements;
        int victoryPoints;
        SpecialAbilityTemp specialAbility;

        public LeaderCardTemp(int id, Map<String, Integer> requirements, int victoryPoints, SpecialAbilityTemp specialAbility) {
            this.id = id;
            this.requirements = requirements;
            this.victoryPoints = victoryPoints;
            this.specialAbility = specialAbility;
        }
    }


}
