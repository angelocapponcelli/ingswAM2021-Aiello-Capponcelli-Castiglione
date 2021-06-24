package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.cards.LeaderRequirements;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;
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

class InHandLeaderCardTest {
    RealPlayer realPlayer;
    InHandLeaderCard inHandLeaderCard;

    @BeforeEach
    void init() {
        realPlayer = new RealPlayer("Seth Gecko", new GameController(2,1));
        inHandLeaderCard = new InHandLeaderCard();
        LeaderRequirements leaderRequirements = new LeaderRequirements();
        leaderRequirements.add(Coin.getInstance(), 1);
        LeaderCard leaderCard1 = new LeaderCard(1, leaderRequirements, 1, new SpecialDiscount(Coin.getInstance()));
        LeaderCard leaderCard2 = new LeaderCard(2, new LeaderRequirements(), 3, new SpecialWhiteMarble(Servant.getInstance()));
        inHandLeaderCard.getInHandLeaderCards().add(leaderCard1);
        inHandLeaderCard.getInHandLeaderCards().add(leaderCard2);
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(ResourceType.COIN, 3);
        leaderCard1.playCard(realPlayer);
    }


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

    @Test
    void remove(){
        int tmp;
        LeaderCard leaderCard3 = new LeaderCard(3, new LeaderRequirements(), 3, new SpecialWhiteMarble(Servant.getInstance()));
        LeaderCard leaderCard4 = new LeaderCard(4, new LeaderRequirements(), 5, new SpecialDiscount(Coin.getInstance()));
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

    @Test
    void discardInGame(){
        int tmp= realPlayer.getFaithPosition();
        List<Integer> discard= new ArrayList<>();
        discard.add(inHandLeaderCard.getCard(0).getId());
        inHandLeaderCard.inGameDiscard(discard, realPlayer);
        assertEquals(1, inHandLeaderCard.getInHandLeaderCards().size());
        assertEquals(tmp+1, realPlayer.getFaithPosition());
    }

    @Test
    void getCardsCount(){
        assertEquals(2, inHandLeaderCard.getCardsCount());
        inHandLeaderCard.getInHandLeaderCards().remove(0);
        assertEquals(1, inHandLeaderCard.getCardsCount());
    }

    @Test
    void addCard(){
        LeaderCard leaderCard3 = new LeaderCard(3, new LeaderRequirements(), 3, new SpecialWhiteMarble(Servant.getInstance()));
        LeaderCard leaderCard4 = new LeaderCard(4, new LeaderRequirements(), 5, new SpecialDiscount(Coin.getInstance()));
        InHandLeaderCard testHand= new InHandLeaderCard();
        List<LeaderCard> tmp= new ArrayList<>();
        tmp.add(leaderCard3);
        tmp.add(leaderCard4);
        testHand.addLeaderCard(tmp);
        assertEquals(2, testHand.getInHandLeaderCards().size());
        assertEquals(3,testHand.getCard(0).getId());
        assertEquals(5, testHand.getInHandLeaderCards().get(1).getVictoryPoint());
    }

    @Test
    void getCard(){
        assertEquals(1, inHandLeaderCard.getCard(0).getId());
    }


}