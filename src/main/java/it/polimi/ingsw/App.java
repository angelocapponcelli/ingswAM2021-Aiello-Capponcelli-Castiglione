package it.polimi.ingsw;

import com.google.gson.Gson;
import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.server.utils.LeaderCardParser;

import java.io.FileNotFoundException;
import java.util.List;


public class App {

    public static void main(String[] args){
        List<LeaderCard> leaderDeck;
        try {
            leaderDeck = LeaderCardParser.getLeaderCards();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}
