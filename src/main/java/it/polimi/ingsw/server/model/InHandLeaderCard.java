package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.Faith;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InHandLeaderCard {
    List<LeaderCard> cards;

    public InHandLeaderCard() {
        this.cards = new ArrayList<>();
    }

    public LeaderCard getCard(int index) {
        return cards.get(index);
    }

    public void remove(LeaderCard leaderCard) {
        this.cards.remove(leaderCard);
    }

    /*
     * has to be checked
     */
    public void discard(LeaderCard leaderCard, RealPlayer realPlayer) {
        this.cards.remove(leaderCard);
        Faith faith= Faith.getInstance();
        faith.onTaking(realPlayer);
    }

    public List<SpecialAbility> getEnabledAbilities() {
        List<SpecialAbility> enabledAbilities = new LinkedList<>();
        List<LeaderCard> tmpCards = cards;
        for (LeaderCard leaderCard : tmpCards) {
            if (leaderCard.isPlayed()) {
                enabledAbilities.add(leaderCard.getSpecialAbility());
            }
        }
        return enabledAbilities;
    }

    public Integer getVictoryPoint() {
        int sum = 0;
        for (LeaderCard leaderCard : cards) {
            if (leaderCard.isPlayed()) {
                sum = sum + leaderCard.getVictoryPoint();
            }
        }
        return sum;
    }
}

