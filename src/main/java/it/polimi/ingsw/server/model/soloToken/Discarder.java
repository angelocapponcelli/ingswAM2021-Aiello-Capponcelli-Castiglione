package it.polimi.ingsw.server.model.soloToken;

import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.interfaces.Revealable;

import java.util.ArrayList;

public class Discarder implements Revealable {
    private final Colors type;

    public Discarder(Colors type) {
        this.type = type;
    }


    @Override
    public void onReveal(Lorenzo lorenzo) {
        GlobalBoard globalBoard1 = new GlobalBoard();
        /*get global board in some way*/
        int tmp1;
        int tmp2;
        ArrayList<Deck> listOfPossibleDecks= new ArrayList<>();
        for (tmp1=0; tmp1< 3; tmp1++){
            for (tmp2=0; tmp2<4; tmp2++){
                DevelopmentCard developmentCard= globalBoard1.getDevelopmentCardGrid().getDeck(tmp1,tmp2).getDeck().get(0);
                if(developmentCard.getTypeLevel().getType().equals(this.type)){
                    listOfPossibleDecks.add(globalBoard1.getDevelopmentCardGrid().getDeck(tmp1,tmp2));
                }
            }
        }
        int tmp3=0;
        /*asks the player which deck*/
        listOfPossibleDecks.get(tmp3).getDeck().remove(0);
        listOfPossibleDecks.get(tmp3).getDeck().remove(0);
    }
}
