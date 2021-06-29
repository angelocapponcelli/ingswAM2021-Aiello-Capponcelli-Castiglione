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

    /**
     * @return color
     */
    public String getColor() {

        switch (this) {
            case WHITE:
                return CLIColors.getAnsiWhite();
            case BLUE:
                return CLIColors.getAnsiBlue();
            case GREY:
                return CLIColors.getAnsiBrightBlack();
            case YELLOW:
                return CLIColors.getAnsiYellow();
            case PURPLE:
                return CLIColors.getAnsiPurple();
            case RED:
                return CLIColors.getAnsiRed();
            case GREEN:
                return CLIColors.getAnsiGreen();
            default:
                return CLIColors.ANSI_RESET;
        }

    }

}
