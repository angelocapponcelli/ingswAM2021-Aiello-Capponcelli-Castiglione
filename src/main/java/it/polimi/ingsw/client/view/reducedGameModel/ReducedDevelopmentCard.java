package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class ReducedDevelopmentCard {
    private final Integer id;
    private final Map<ResourceType, Integer> cost = new HashMap<>();
    private final Map<Colors, Integer> typeLevel = new HashMap<>();
    private final Map<Map<ResourceType, Integer>, Map<ResourceType, Integer>> productionPower = new HashMap<>();
    private final int victoryPoints;

    public ReducedDevelopmentCard(DevelopmentCard developmentCard) {
        id = developmentCard.getId();
        victoryPoints = developmentCard.getVictoryPoints();
    }
}
