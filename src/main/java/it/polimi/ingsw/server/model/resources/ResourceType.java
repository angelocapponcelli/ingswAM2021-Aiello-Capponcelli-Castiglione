package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedRequirement;
import it.polimi.ingsw.client.view.CLI.CLIColors;

/**
 * The enum ResourceType.
 */
public enum ResourceType implements ReducedRequirement {
    COIN,
    STONE,
    SERVANT,
    SHIELD,
    FAITH,
    ANY;

    public static Resource getResourceClass(ResourceType resourceType) {
        switch (resourceType.toString()) {
            case "COIN":
                return Coin.getInstance();
            case "STONE":
                return Stone.getInstance();
            case "SHIELD":
                return Shield.getInstance();
            case "SERVANT":
                return Servant.getInstance();
            case "FAITH":
                return Faith.getInstance();
            case "ANY":
                return Any.getInstance();
            default:
                return null;
        }
    }

    public static boolean contains(String test) {

        for (ResourceType resourceType : ResourceType.values()) {
            if (resourceType.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

    public String getColor() {
        switch (this.toString()) {
            case "COIN":
                return CLIColors.getAnsiYellow();
            case "STONE":
                return CLIColors.getAnsiBrightBlack();
            case "SHIELD":
                return CLIColors.getAnsiBlue();
            case "SERVANT":
                return CLIColors.getAnsiPurple();
            case "FAITH":
                return CLIColors.getAnsiRed();
            case "ANY":
                return CLIColors.getAnsiWhite();
            default:
                return null;
        }
    }
}
