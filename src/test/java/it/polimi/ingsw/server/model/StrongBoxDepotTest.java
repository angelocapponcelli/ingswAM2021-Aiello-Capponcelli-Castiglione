package it.polimi.ingsw.server.model;

import it.polimi.ingsw.utils.exceptions.DepotException;
import it.polimi.ingsw.server.model.personalBoard.depots.StrongBoxDepot;
import it.polimi.ingsw.server.model.resources.ResourceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StrongBoxDepotTest {

    @Test
    void multipleAddRemove() throws DepotException {
        StrongBoxDepot strongBoxDepot = new StrongBoxDepot();
        strongBoxDepot.addResources(ResourceType.COIN, 0);
        assertEquals(0, strongBoxDepot.getAllResourceCount());
        strongBoxDepot.removeResources(ResourceType.SHIELD, 0);
        assertEquals(0, strongBoxDepot.getAllResourceCount());
        assertThrows(DepotException.class, () -> strongBoxDepot.removeResources(ResourceType.SHIELD, 1));
        strongBoxDepot.addResources(ResourceType.STONE, 1000000);
        assertEquals(1000000, strongBoxDepot.getAllResourceCount());
        strongBoxDepot.addResources(ResourceType.COIN, 1000000);
        strongBoxDepot.addResources(ResourceType.SHIELD, 1000000);
        strongBoxDepot.addResources(ResourceType.SERVANT, 1000000);
        assertEquals(4000000, strongBoxDepot.getAllResourceCount());
        assertThrows(DepotException.class, () -> strongBoxDepot.removeResources(ResourceType.SHIELD, 4000000));
    }
}