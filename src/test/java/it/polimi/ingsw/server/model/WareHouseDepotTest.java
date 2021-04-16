package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.ResourceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WareHouseDepotTest {

    @Test
    void multipleAddRemove() throws DepotException {
        WareHouseDepot wareHouseDepot = new WareHouseDepot();
        assertThrows(DepotException.class, () -> wareHouseDepot.add(ResourceType.COIN,-1, 0));
        assertThrows(DepotException.class, () -> wareHouseDepot.add(ResourceType.COIN,3, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> wareHouseDepot.add(ResourceType.COIN,1, 3));
        wareHouseDepot.add(ResourceType.COIN,1,0);
        assertThrows(DepotException.class, () -> wareHouseDepot.add(ResourceType.COIN,1, 1));
        assertThrows(DepotException.class, () -> wareHouseDepot.add(ResourceType.COIN,1, 0));
        assertThrows(DepotException.class, () -> wareHouseDepot.remove(ResourceType.COIN,-1));
        wareHouseDepot.remove(ResourceType.COIN,0);
        assertEquals(1,wareHouseDepot.getResourceCount());
        wareHouseDepot.remove(ResourceType.COIN,1);
        assertThrows(DepotException.class, () -> wareHouseDepot.remove(ResourceType.COIN,1));
        assertEquals(0,wareHouseDepot.getResourceCount());
        wareHouseDepot.add(ResourceType.COIN,2,1);
        wareHouseDepot.add(ResourceType.SERVANT,3,2);
        assertThrows(DepotException.class, () -> wareHouseDepot.swap(1,2));
        wareHouseDepot.remove(ResourceType.SERVANT,1);
        wareHouseDepot.add(ResourceType.SERVANT,1,2);
        assertThrows(DepotException.class, () -> wareHouseDepot.swap(2,1));
        wareHouseDepot.remove(ResourceType.SERVANT,1);
        wareHouseDepot.swap(2,1);
        assertEquals(4,wareHouseDepot.getResourceCount());
    }
}