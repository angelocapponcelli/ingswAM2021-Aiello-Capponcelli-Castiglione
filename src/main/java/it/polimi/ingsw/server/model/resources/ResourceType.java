package it.polimi.ingsw.server.model.resources;

/**
 * The enum ResourceType.
 */
public enum ResourceType {
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
}
