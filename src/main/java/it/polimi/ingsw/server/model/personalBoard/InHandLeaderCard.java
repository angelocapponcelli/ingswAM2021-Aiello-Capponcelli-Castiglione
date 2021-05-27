package it.polimi.ingsw.server.model.personalBoard;

import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedInHandLeaderCardMessage;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Faith;
import it.polimi.ingsw.server.model.specialAbilities.SpecialAbility;
import it.polimi.ingsw.utils.observer.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Player's in hand leader cards
 */
public class InHandLeaderCard extends Observable{
    List<LeaderCard> inHandLeaderCards;

    public InHandLeaderCard() {
        this.inHandLeaderCards = new ArrayList<>();
    }

    public List<LeaderCard> getInHandLeaderCards() {
        return inHandLeaderCards;
    }

    public LeaderCard getCard(int index) {
        return inHandLeaderCards.get(index);
    }

    public void remove(Integer leaderCardID) {
        LeaderCard leaderCard = inHandLeaderCards.stream().filter(card -> card.getId().equals(leaderCardID)).findFirst().orElse(null);
        this.inHandLeaderCards.remove(leaderCard);
        notifyObserver(new UpdatedInHandLeaderCardMessage( inHandLeaderCards.stream().map(LeaderCard::getId).collect(Collectors.toList()) ) );
    }

    public void addLeaderCard(List<LeaderCard> leaderCards){
        inHandLeaderCards = leaderCards;
        notifyObserver(new UpdatedInHandLeaderCardMessage(  inHandLeaderCards.stream().map(LeaderCard::getId).collect(Collectors.toList()) ) );
    }

    public void inGameDiscard(Integer leaderCardID, RealPlayer realPlayer) {
        remove(leaderCardID);
        Faith faith = Faith.getInstance();
        faith.onTaking(realPlayer);
    }

    public List<SpecialAbility> getEnabledAbilities() {
        List<SpecialAbility> enabledAbilities = new LinkedList<>();
        List<LeaderCard> tmpCards = inHandLeaderCards;
        for (LeaderCard leaderCard : tmpCards) {
            if (leaderCard.isPlayed()) {
                enabledAbilities.add(leaderCard.getSpecialAbility());
            }
        }
        return enabledAbilities;
    }

    public Integer getVictoryPoint() {
        int sum = 0;
        for (LeaderCard leaderCard : inHandLeaderCards) {
            if (leaderCard.isPlayed()) {
                sum = sum + leaderCard.getVictoryPoint();
            }
        }
        return sum;
    }

    public Integer getCardsCount() {
        int tmp = 0;
        for (LeaderCard leaderCard : this.inHandLeaderCards) {
            tmp++;
        }
        return tmp;
    }
}

