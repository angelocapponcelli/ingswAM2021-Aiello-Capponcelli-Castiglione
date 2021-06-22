package it.polimi.ingsw.server.model.misc;

import it.polimi.ingsw.client.view.CLI.CLIColors;

/**
 * The enum Colors.
 */
public enum Colors {
    WHITE,
    BLUE,
    GREY,
    YELLOW,
    PURPLE,
    RED,
    GREEN;

    public String getColor() {
        if (this == Colors.WHITE)
            return CLIColors.getAnsiYellow();
        if (this == Colors.BLUE)
            return CLIColors.getAnsiYellow();
        if (this == Colors.GREY)
            return CLIColors.getAnsiBlack();
        if (this == Colors.YELLOW)
            return CLIColors.getAnsiYellow();
        if (this == Colors.PURPLE)
            return CLIColors.getAnsiPurple();
        if (this == Colors.RED)
            return CLIColors.getAnsiRed();
        if (this == Colors.GREEN)
            return CLIColors.getAnsiGreen();


        else return null;
    }
}
