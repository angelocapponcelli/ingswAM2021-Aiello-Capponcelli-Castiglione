package it.polimi.ingsw;

import it.polimi.ingsw.server.utils.DevelopmentCardFactory;

import java.io.FileNotFoundException;

public class App
{
    public static void main( String[] args ) throws FileNotFoundException {
        DevelopmentCardFactory fact =new DevelopmentCardFactory();

        fact.loadCards();
    }
}
