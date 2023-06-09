package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.cards.LeaderRequirements;
import it.polimi.ingsw.server.model.personalBoard.InHandLeaderCard;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Coin;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.server.model.resources.Servant;
import it.polimi.ingsw.server.model.specialAbilities.SpecialDiscount;
import it.polimi.ingsw.server.model.specialAbilities.SpecialWhiteMarble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In hand Leader Card test
 */
class InHandLeaderCardTest {
    RealPlayer realPlayer;
    InHandLeaderCard inHandLeaderCard;

    /**
     * Initializes the player and populates the hand leader card
     */
    @BeforeEach
    void init() {
        realPlayer = new RealPlayer("Seth Gecko", new GameController(2,1));
        inHandLeaderCard = new InHandLeaderCard();
        LeaderRequirements leaderRequirements = new LeaderRequirements();
        leaderRequirements.add(Coin.getInstance(), 1);
        LeaderCard leaderCard1 = new LeaderCard(1, leaderRequirements, 1, new SpecialDiscount(Coin.getInstance(),1));
        LeaderCard leaderCard2 = new LeaderCard(2, new LeaderRequirements(), 3, new SpecialWhiteMarble(Servant.getInstance(),1));
        inHandLeaderCard.getInHandLeaderCards().add(leaderCard1);
        inHandLeaderCard.getInHandLeaderCards().add(leaderCard2);
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(ResourceType.COIN, 3);
        leaderCard1.playCard(realPlayer);
    }

    /**
     * Verifies the special abilities enabled list
     */
    @Test
    void getEnabledAbilities() {
        int tmp = 0;
        int tmp1 = 0;
        for (LeaderCard leaderCard : inHandLeaderCard.getInHandLeaderCards()) {
            if (inHandLeaderCard.getInHandLeaderCards().get(tmp1).isPlayed()) {
                tmp++;
            }
            tmp1++;
        }
        assertEquals(1, tmp);
    }

    /**
     * Checks if the sum of victory points of the card's played is correct
     */
    @Test
    void getVictoryPoint() {
        int tmp = 0;
        int tmp1 = 0;
        for (LeaderCard leaderCard : inHandLeaderCard.getInHandLeaderCards()) {
            if (inHandLeaderCard.getInHandLeaderCards().get(tmp1).isPlayed()) {
                tmp = tmp + inHandLeaderCard.getInHandLeaderCards().get(tmp1).getVictoryPoint();
            }
            tmp1++;
        }
        assertEquals(1, tmp);
    }

    /**
     * Checks when a card is removed from the in hand leader card
     */
    @Test
    void remove(){
        int tmp;
        LeaderCard leaderCard3 = new LeaderCard(3, new LeaderRequirements(), 3, new SpecialWhiteMarble(Servant.getInstance(),1));
        LeaderCard leaderCard4 = new LeaderCard(4, new LeaderRequirements(), 5, new SpecialDiscount(Coin.getInstance(),1));
        List<Integer> removeList= new ArrayList<>();
        inHandLeaderCard.getInHandLeaderCards().add(leaderCard3);
        inHandLeaderCard.getInHandLeaderCards().add(leaderCard4);
        tmp= inHandLeaderCard.getCard(1).getId();
        removeList.add(inHandLeaderCard.getCard(0).getId());
        removeList.add(inHandLeaderCard.getCard(2).getId());

        inHandLeaderCard.remove(removeList);

        assertEquals(2, inHandLeaderCard.getInHandLeaderCards().size());

        assertEquals(leaderCard4.getId(), inHandLeaderCard.getCard(1).getId());
        assertEquals(tmp, inHandLeaderCard.getCard(0).getId());

    }


    /**
     * Discards in game card. Checks player's position is incremented.
     */
    @Test
    void discardInGame(){
        int tmp= realPlayer.getFaithPosition();
        List<Integer> discard= new ArrayList<>();
        discard.add(inHandLeaderCard.getCard(0).getId());
        inHandLeaderCard.inGameDiscard(discard, realPlayer);
        assertEquals(1, inHandLeaderCard.getInHandLeaderCards().size());
        assertEquals(tmp+1, realPlayer.getFaithPosition());
    }

    /**
     * Checks card's count
     */
    @Test
    void getCardsCount(){
        assertEquals(2, inHandLeaderCard.getCardsCount());
        inHandLeaderCard.getInHandLeaderCards().remove(0);
        assertEquals(1, inHandLeaderCard.getCardsCount());
    }

    /**
     * Checks the addition of a card to the in hand leader cards
     */
    @Test
    void addCard(){
        LeaderCard leaderCard3 = new LeaderCard(3, new LeaderRequirements(), 3, new SpecialWhiteMarble(Servant.getInstance(),1));
        LeaderCard leaderCard4 = new LeaderCard(4, new LeaderRequirements(), 5, new SpecialDiscount(Coin.getInstance(),1));
        InHandLeaderCard testHand= new InHandLeaderCard();
        List<LeaderCard> tmp= new ArrayList<>();
        tmp.add(leaderCard3);
        tmp.add(leaderCard4);
        testHand.addLeaderCard(tmp);
        assertEquals(2, testHand.getInHandLeaderCards().size());
        assertEquals(3,testHand.getCard(0).getId());
        assertEquals(5, testHand.getInHandLeaderCards().get(1).getVictoryPoint());
    }

    /**
     * checks get card
     */
    @Test
    void getCard(){
        assertEquals(1, inHandLeaderCard.getCard(0).getId());
    }


}