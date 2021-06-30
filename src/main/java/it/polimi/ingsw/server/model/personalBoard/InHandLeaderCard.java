package it.polimi.ingsw.server.model.personalBoard;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedLeaderCard;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedInHandLeaderCardMessage;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Faith;
import it.polimi.ingsw.server.model.specialAbilities.SpecialAbility;
import it.polimi.ingsw.utils.observer.Observable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Player's in hand leader cards
 */
public class InHandLeaderCard extends Observable{
    List<LeaderCard> inHandLeaderCards;

    /**
     * Class constructor. Instantiates a new In hand leader Card list
     */
    public InHandLeaderCard() {
        this.inHandLeaderCards = new ArrayList<>();
    }

    /**
     * gets list of leader cards
     * @return list of leader cards
     */
    public List<LeaderCard> getInHandLeaderCards() {
        return inHandLeaderCards;
    }

    /**
     * Gets leader card selected
     * @param index the position where the leader card can be found in the list of in hand leader card
     * @return leader card
     */
    public LeaderCard getCard(int index) {
        return inHandLeaderCards.get(index);
    }

    /**
     * Performs removal of a list of leader cards from in hand leader card. It is usually used at the beginning
     * of a game when a player chooses two out of four cards to be discarded
     * @param leaderCardID list of identifications for leader cards
     */
    public void remove(List<Integer> leaderCardID) {

            inHandLeaderCards = inHandLeaderCards.stream()
                    .filter(x -> !(leaderCardID.contains(x.getId())))
                    .collect(Collectors.toList());

        notifyObserver(new UpdatedInHandLeaderCardMessage(inHandLeaderCards.stream().map(ReducedLeaderCard::new).collect(Collectors.toList())));
    }

    /**
     * Adds leader cards to the hand leader card of the player
     * @param leaderCards the list of leader card to be added to the hand leader card of the player
     */
    public void addLeaderCard(List<LeaderCard> leaderCards){
        inHandLeaderCards = leaderCards;
        notifyObserver(new UpdatedInHandLeaderCardMessage(inHandLeaderCards.stream().map(ReducedLeaderCard::new).collect(Collectors.toList())));
    }

    /**
     * Performs removal of a list of leader cards. It also increments the player's position
     * @param leaderCardIDs the list of leader cards that have to be discarded
     * @param realPlayer the player that decides to discard the cards
     */
    public void inGameDiscard(List<Integer> leaderCardIDs, RealPlayer realPlayer) {
        remove(leaderCardIDs);
        Faith faith = Faith.getInstance();
        faith.onTaking(realPlayer);
    }

    /**
     * Gets a list of enabled special abilities
     * @return list of special abilities
     */
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


    /**
     * Gets the sum of victory points of the cards that have been played
     * @return sum of victory points
     */
    public Integer getVictoryPoint() {
        int sum = 0;
        for (LeaderCard leaderCard : inHandLeaderCards) {
            if (leaderCard.isPlayed()) {
                sum = sum + leaderCard.getVictoryPoint();
            }
        }
        return sum;
    }


    /**
     * Performs the counting of leader cards
     * @return count of cards
     */
    public Integer getCardsCount() {
        int tmp = 0;
        for (LeaderCard leaderCard : this.inHandLeaderCards) {
            tmp++;
        }
        return tmp;
    }

    public void playCard(int id, RealPlayer realPlayer){
        inHandLeaderCards.stream().filter(leaderCard -> leaderCard.getId() == id).findFirst().get().playCard(realPlayer);
        notifyObserver(new UpdatedInHandLeaderCardMessage(inHandLeaderCards.stream().map(ReducedLeaderCard::new).collect(Collectors.toList())));
    }
}

