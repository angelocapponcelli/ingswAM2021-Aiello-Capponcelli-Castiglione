package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.client.view.CLI.CLIColors;
import it.polimi.ingsw.client.view.CLI.Utils;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.cards.TypeLevel;
import it.polimi.ingsw.server.model.interfaces.Requirement;
import it.polimi.ingsw.server.model.resources.*;
import it.polimi.ingsw.server.model.specialAbilities.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReducedLeaderCard implements Serializable {
    private final int id;
    private final Map<ReducedRequirement, Integer> requirements;
    private final int victoryPoints;
    private final SpecialAbilityType specialAbility;
    private final ResourceType specialResourceType;
    private Boolean played;

    public ReducedLeaderCard(LeaderCard leaderCard) {
        this.id = leaderCard.getId();
        this.victoryPoints = leaderCard.getVictoryPoint();
        Map<ReducedRequirement, Integer> tmp = new HashMap<>();
        for (Map.Entry<Requirement, Integer> entry : leaderCard.getRequirements().getLeaderRequirements().entrySet()) {
            if (entry.getKey() == Stone.getInstance()) tmp.put(ResourceType.STONE, entry.getValue());
            else if (entry.getKey() == Servant.getInstance()) tmp.put(ResourceType.SERVANT, entry.getValue());
            else if (entry.getKey() == Shield.getInstance()) tmp.put(ResourceType.SHIELD, entry.getValue());
            else if (entry.getKey() == Coin.getInstance()) tmp.put(ResourceType.COIN, entry.getValue());
            else tmp.put((TypeLevel) entry.getKey(), entry.getValue());
        }
        this.requirements = tmp;
        if (leaderCard.getSpecialAbility() instanceof SpecialExtraDepot) this.specialAbility = SpecialAbilityType.EXTRADEPOT;
        else if (leaderCard.getSpecialAbility() instanceof SpecialDiscount) this.specialAbility = SpecialAbilityType.DISCOUNT;
        else if (leaderCard.getSpecialAbility() instanceof SpecialAdditionalProductionPower) this.specialAbility = SpecialAbilityType.PRODUCTION_POWER;
        else this.specialAbility = SpecialAbilityType.WHITE_MARBLE;
        this.played = leaderCard.isPlayed();
        this.specialResourceType = leaderCard.getSpecialAbility().getResource().getResourceType();
    }

    public int getId() {
        return id;
    }

    public Map<ReducedRequirement, Integer> getRequirements() {
        return requirements;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public SpecialAbilityType getSpecialAbility() {
        return specialAbility;
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public ResourceType getSpecialResourceType() {
        return specialResourceType;
    }

    public String requirementsToCLI() {

        StringBuffer temp = new StringBuffer();
        requirements.forEach((key, value) -> {
            for (int i = 0; i < value; i++) {
                temp.append(key.toCLI());
            }
            temp.append(" ");
        });
        return temp.toString();
    }

    public String victoryPointsToCLI(){
        return CLIColors.getAnsiYellowBackground()+"{"+ victoryPoints +"}"+CLIColors.getAnsiReset();
    }

    public String specialAbilityToCLI(){
        switch (specialAbility){
            case DISCOUNT:
                return "-1"+specialResourceType.toCLI();
            case EXTRADEPOT:
                return "|" + specialResourceType.toCLI() + "|" +" "+ "|" + specialResourceType.toCLI() + "|";
            case PRODUCTION_POWER:
                return ResourceType.ANY.toCLI() + "=" + specialResourceType.toCLI();
            case WHITE_MARBLE:
                return "1"+ specialResourceType.toCLI() +" -> " + "1"+CLIColors.getAnsiBlack()+"●"+CLIColors.getAnsiReset() + " 1"+ResourceType.FAITH.toCLI();
            default:
                return "ERROR!";
        }
    }
}