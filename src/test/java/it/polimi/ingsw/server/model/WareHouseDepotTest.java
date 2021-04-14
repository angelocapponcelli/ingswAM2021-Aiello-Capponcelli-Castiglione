package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WareHouseDepotTest {

    /*
     *   to complete and verify WareHouseDepot class
     */
    @Test
    void multipleAddRemove() throws DepotException {
        WareHouseDepot wareHouseDepot = new WareHouseDepot();
        assertThrows(DepotException.class, () -> wareHouseDepot.add(ResourceType.COIN,-1, 0));
        assertThrows(DepotException.class, () -> wareHouseDepot.add(ResourceType.COIN,3, 0));
        wareHouseDepot.add(ResourceType.COIN,1,0);

    }
}