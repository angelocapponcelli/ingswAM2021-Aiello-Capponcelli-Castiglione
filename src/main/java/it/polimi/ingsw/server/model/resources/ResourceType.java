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

    /**
     * Gets the class correspondent to the enumeration type
     * @param resourceType the type of the resource
     * @return resource class
     */
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

    /**
     * Parses
     * @param string the string that has to be converted to enumeration
     * @return resource type correspondent to the string
     */
    public static ResourceType parse(String string){
        switch (string) {
            case "COIN":
                return COIN;
            case "STONE":
                return STONE;
            case "SHIELD":
                return SHIELD;
            case "SERVANT":
                return SERVANT;
            case "FAITH":
                return FAITH;
            case "ANY":
                return ANY;
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

    /**
     * Gets color
     * @return color correspondent to the type
     */
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

    public String toCLI() {
        switch (this.toString()) {
            case "COIN":
                return CLIColors.getAnsiYellow()+"●"+CLIColors.getAnsiReset();
            case "STONE":
                return CLIColors.getAnsiBrightBlack()+"●"+CLIColors.getAnsiReset();
            case "SHIELD":
                return CLIColors.getAnsiBlue()+"●"+CLIColors.getAnsiReset();
            case "SERVANT":
                return CLIColors.getAnsiPurple()+"●"+CLIColors.getAnsiReset();
            case "FAITH":
                return CLIColors.getAnsiRed()+"●"+CLIColors.getAnsiReset();
            case "ANY":
                return CLIColors.getAnsiWhite()+"●"+CLIColors.getAnsiReset();
            default:
                return null;
        }
    }



}
