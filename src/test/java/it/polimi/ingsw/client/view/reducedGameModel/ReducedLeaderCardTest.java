package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.client.view.CLI.Utils;
import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.utils.parsers.LeaderCardParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ReducedLeaderCardTest {

    public static int getStringLengthWithoutANSI(String str) {
        return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length();
    }

    @Test
    void test1(){
        List<LeaderCard> leaderCards = LeaderCardParser.getLeaderCards();
        List<ReducedLeaderCard> reducedLeaderCards = new ArrayList<>();
        List<String> requirements = new ArrayList<>();
        List<String> formatted = new ArrayList<>();


        for(int i = 0; i< 4;  i++){
            reducedLeaderCards.add(new ReducedLeaderCard(leaderCards.get(i)));
            requirements.add(reducedLeaderCards.get(i).requirementsToCLI());
        }

        requirements.forEach(x -> System.out.println(x.length()));
        requirements.forEach(x -> System.out.println(getStringLengthWithoutANSI(x)));

        for(String string: requirements){
            System.out.println("|"+Utils.getAlignedToLeft(string,30)+"|");
        }


    }

}