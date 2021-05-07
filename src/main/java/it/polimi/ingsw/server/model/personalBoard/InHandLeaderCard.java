package it.polimi.ingsw.server.model.personalBoard;

import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Faith;
import it.polimi.ingsw.server.model.specialAbilities.SpecialAbility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Player's in hand leader cards
 */
public class InHandLeaderCard {
    List<LeaderCard> cards;

    public InHandLeaderCard() {
        this.cards = new ArrayList<>();
    }

    public List<LeaderCard> getCards() {
        return cards;
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
        Faith faith = Faith.getInstance();
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

    public Integer getCardsCount() {
        int tmp = 0;
        for (LeaderCard leaderCard : this.cards) {
            tmp++;
        }
        return tmp;
    }
}

