package it.polimi.ingsw.server.model.soloToken;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.globalBoard.DevelopmentCardGrid;
import it.polimi.ingsw.server.model.globalBoard.GlobalBoard;
import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.misc.Deck;
import it.polimi.ingsw.server.model.player.Lorenzo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Discarder implements Revealable {
    private final Colors type;

    public Discarder(Colors type) {
        this.type = type;
    }


    @Override
    public void onReveal(Lorenzo lorenzo) {
        List<DevelopmentCard> tmp= new ArrayList<>();
        int column;
        int maxsize=2;
        for(column=0; column<4; column++){
            if(lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(0, column).peek().getTypeLevel().getType().equals(this.type)){
                break;
            }
        }
        DevelopmentCardGrid grid= lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid();
        for (int i=2; i>=0; i--){
            for(int j=grid.getDeck(i,column).getDeck().size()-1; j>=0; j--){
                tmp.add(grid.getDeck(i,column).getDeck().get(j));
                maxsize--;
                if(maxsize==0){
                    break;
                }
            }
            if(maxsize==0){
                break;
            }
        }

        if(tmp.size()<2 && grid.getDeck(0,column).getDeck().size()<=2){
            System.out.println("Lorenzo has won");
        }

        for(int i=0; i<3;i++){
            lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(i, column).getDeck().removeIf(tmp::contains);
        }

    }
}
