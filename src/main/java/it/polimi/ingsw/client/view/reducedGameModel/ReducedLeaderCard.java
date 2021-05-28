package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.cards.TypeLevel;
import it.polimi.ingsw.server.model.interfaces.Requirement;
import it.polimi.ingsw.server.model.resources.*;
import it.polimi.ingsw.server.model.specialAbilities.*;
import it.polimi.ingsw.utils.parsers.LeaderCardParser;

import java.util.HashMap;
import java.util.Map;

public class ReducedLeaderCard {
    private final int id;
    private final Map<ReducedRequirement, Integer> requirements;
    private final int victoryPoints;
    private final SpecialAbilityType specialAbility;
    private final ResourceType specialResourceType;
    private Boolean played;

    public ReducedLeaderCard(int i) {
        LeaderCard leaderCard = LeaderCardParser.getLeaderCards().get(i);
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
}
