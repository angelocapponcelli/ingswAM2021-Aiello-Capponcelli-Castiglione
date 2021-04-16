package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.ResourceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrongBoxDepotTest {

    @Test
    void multipleAddRemove() throws DepotException {
        StrongBoxDepot strongBoxDepot = new StrongBoxDepot();
        assertThrows(DepotException.class, () -> strongBoxDepot.add(ResourceType.COIN,-1));
        strongBoxDepot.add(ResourceType.COIN, 0);
        assertEquals(0, strongBoxDepot.getResourceCount());
        strongBoxDepot.remove(ResourceType.SHIELD,0);
        assertEquals(0, strongBoxDepot.getResourceCount());
        assertThrows(DepotException.class, () -> strongBoxDepot.remove(ResourceType.SHIELD,1));
        strongBoxDepot.add(ResourceType.STONE, 1000000);
        assertEquals(1000000, strongBoxDepot.getResourceCount());
        strongBoxDepot.add(ResourceType.COIN, 1000000);
        strongBoxDepot.add(ResourceType.SHIELD, 1000000);
        strongBoxDepot.add(ResourceType.SERVANT, 1000000);
        assertEquals(4000000, strongBoxDepot.getResourceCount());
        assertThrows(DepotException.class, () -> strongBoxDepot.remove(ResourceType.SHIELD,4000000));
    }
}