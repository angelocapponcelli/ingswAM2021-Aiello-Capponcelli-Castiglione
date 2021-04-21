package it.polimi.ingsw;

import com.google.gson.Gson;
import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.interfaces.Requirement;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.server.model.resources.Stone;
import it.polimi.ingsw.server.utils.DevelopmentCardFactory;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;


public class App
{
    public static void main( String[] args ) throws FileNotFoundException {
        List<DevelopmentCard> deck;

        deck = DevelopmentCardFactory.getCards();
        DevelopmentCard dc1 = deck.get(15);

        HashMap<Requirement,Integer> req = new HashMap<>();
        req.put(Stone.getInstance(), 5);
        req.put(new TypeLevel(Colors.YELLOW, -1),1);



        LeaderCard lc = new LeaderCard(49, new LeaderRequirements(req), 2, new ExtraDepot(ResourceType.COIN));
        Gson gson = new Gson();
        String s = gson.toJson(lc);
    }
}
