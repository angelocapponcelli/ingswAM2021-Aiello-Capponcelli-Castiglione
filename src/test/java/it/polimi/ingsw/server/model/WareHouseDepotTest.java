package it.polimi.ingsw.server.model;

import it.polimi.ingsw.utils.exceptions.DepotException;
import it.polimi.ingsw.server.model.personalBoard.depots.WareHouseDepot;
import it.polimi.ingsw.server.model.resources.ResourceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WareHouseDepotTest {

    @Test
    void multipleAddRemove() throws DepotException {
        WareHouseDepot wareHouseDepot = new WareHouseDepot();
        assertThrows(DepotException.class, () -> wareHouseDepot.addResource(ResourceType.COIN, -1, 0));
        assertThrows(DepotException.class, () -> wareHouseDepot.addResource(ResourceType.COIN, 3, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> wareHouseDepot.addResource(ResourceType.COIN, 1, 3));
        wareHouseDepot.addResource(ResourceType.COIN, 1, 0);
        assertThrows(DepotException.class, () -> wareHouseDepot.addResource(ResourceType.COIN, 1, 1));
        assertThrows(DepotException.class, () -> wareHouseDepot.addResource(ResourceType.COIN, 1, 0));
        assertThrows(DepotException.class, () -> wareHouseDepot.removeResources(ResourceType.COIN, -1));
        wareHouseDepot.removeResources(ResourceType.COIN, 0);
        assertEquals(1, wareHouseDepot.getAllResourceCount());
        wareHouseDepot.removeResources(ResourceType.COIN, 1);
        assertThrows(DepotException.class, () -> wareHouseDepot.removeResources(ResourceType.COIN, 1));
        assertEquals(0, wareHouseDepot.getAllResourceCount());
        wareHouseDepot.addResource(ResourceType.COIN, 2, 1);
        wareHouseDepot.addResource(ResourceType.SERVANT, 3, 2);
        assertThrows(DepotException.class, () -> wareHouseDepot.swap(1, 2));
        wareHouseDepot.removeResources(ResourceType.SERVANT, 1);
        wareHouseDepot.addResource(ResourceType.SERVANT, 1, 2);
        assertThrows(DepotException.class, () -> wareHouseDepot.swap(2, 1));
        wareHouseDepot.removeResources(ResourceType.SERVANT, 1);
        wareHouseDepot.swap(2, 1);
        assertEquals(4, wareHouseDepot.getAllResourceCount());
    }
}