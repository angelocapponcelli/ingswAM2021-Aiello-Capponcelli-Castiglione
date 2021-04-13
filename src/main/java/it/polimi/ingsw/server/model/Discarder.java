package it.polimi.ingsw.server.model;

public class Discarder implements Revealable {
    private Colors type;

    public Discarder (Colors type) {this.type= type ;}


    @Override
    public void onReveal(Lorenzo lorenzo){
        /**
         * search in deckcardgrid based on type of discarder and do two pop
         *
         */
    }
}
