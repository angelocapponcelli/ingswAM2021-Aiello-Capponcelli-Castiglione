package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialDepotTest {

    @Test
    void multipleAddRemove() throws DepotException {
        SpecialDepot specialDepot = new SpecialDepot();
        assertThrows(DepotException.class, () -> specialDepot.add(ResourceType.COIN,-1));
        assertThrows(DepotException.class, () -> specialDepot.add(ResourceType.COIN,0));
        assertThrows(DepotException.class, () -> specialDepot.add(ResourceType.COIN,1));
        assertThrows(DepotException.class, () -> specialDepot.add(ResourceType.COIN,50));
        specialDepot.addContainer(ResourceType.COIN);
        assertThrows(DepotException.class, () -> specialDepot.add(ResourceType.COIN,-1));
        assertThrows(DepotException.class, () -> specialDepot.add(ResourceType.COIN,3));
        assertThrows(DepotException.class, () -> specialDepot.add(ResourceType.SERVANT,1));
        assertEquals(0, specialDepot.getResourceCount());
        specialDepot.add(ResourceType.COIN,2);
        assertEquals(2, specialDepot.getResourceCount());
        specialDepot.addContainer(ResourceType.SERVANT);
        specialDepot.add(ResourceType.SERVANT,1);
        assertEquals(3, specialDepot.getResourceCount());
        assertThrows(DepotException.class, () -> specialDepot.remove(ResourceType.SERVANT,2));

    }

}