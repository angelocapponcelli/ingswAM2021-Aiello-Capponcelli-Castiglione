package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.exceptions.DepotException;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.server.model.resources.Shield;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StrongBoxDepotTest {

    @Test
    void multipleAddRemove() throws DepotException {
        StrongBoxDepot strongBoxDepot = new StrongBoxDepot();
        assertThrows(DepotException.class, () -> strongBoxDepot.add(ResourceType.COIN, -1));
        strongBoxDepot.add(ResourceType.COIN, 0);
        assertEquals(0, strongBoxDepot.getResourceCount());
        strongBoxDepot.remove(Shield.getInstance(), 0);
        assertEquals(0, strongBoxDepot.getResourceCount());
        assertThrows(DepotException.class, () -> strongBoxDepot.remove(Shield.getInstance(), 1));
        strongBoxDepot.add(ResourceType.STONE, 1000000);
        assertEquals(1000000, strongBoxDepot.getResourceCount());
        strongBoxDepot.add(ResourceType.COIN, 1000000);
        strongBoxDepot.add(ResourceType.SHIELD, 1000000);
        strongBoxDepot.add(ResourceType.SERVANT, 1000000);
        assertEquals(4000000, strongBoxDepot.getResourceCount());
        assertThrows(DepotException.class, () -> strongBoxDepot.remove(Shield.getInstance(), 4000000));
    }
}