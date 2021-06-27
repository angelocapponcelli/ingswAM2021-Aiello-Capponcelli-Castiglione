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


    public String getBGColor() {

        switch (this) {
            case WHITE:
                return CLIColors.getAnsiWhiteBackground();
            case BLUE:
                return CLIColors.getAnsiBlueBackground();
            case GREY:
                return CLIColors.getAnsiBrightBlackBackground();
            case YELLOW:
                return CLIColors.getAnsiYellowBackground();
            case PURPLE:
                return CLIColors.getAnsiPurpleBackground();
            case RED:
                return CLIColors.getAnsiRedBackground();
            case GREEN:
                return CLIColors.getAnsiGreenBackground();
            default:
                return CLIColors.ANSI_RESET;
        }

    }

    public static Colors parse(String string){
        switch (string){
            case "WHITE":
                return WHITE;
            case "BLUE":
                return BLUE;
            case "GREY":
                return GREY;
            case "YELLOW":
                return YELLOW;
            case "PURPLE":
                return PURPLE;
            case "RED":
                return RED;
            case "GREEN":
                return GREEN;
            default:
                return null;
        }
    }
}
