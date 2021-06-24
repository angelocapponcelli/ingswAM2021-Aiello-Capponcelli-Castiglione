package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.personalBoard.depots.SpecialDepot;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpecialDepotTest {

    @Test
    void multipleAddRemove() throws DepotException {
        SpecialDepot specialDepot = new SpecialDepot();
        assertThrows(DepotException.class, () -> specialDepot.addResources(ResourceType.COIN, -1));
        assertThrows(DepotException.class, () -> specialDepot.addResources(ResourceType.COIN, 0));
        assertThrows(DepotException.class, () -> specialDepot.addResources(ResourceType.COIN, 1));
        assertThrows(DepotException.class, () -> specialDepot.addResources(ResourceType.COIN, 50));
        specialDepot.addSpecialContainer(ResourceType.COIN);
        assertThrows(DepotException.class, () -> specialDepot.addResources(ResourceType.COIN, -1));
        assertThrows(DepotException.class, () -> specialDepot.addResources(ResourceType.COIN, 3));
        assertThrows(DepotException.class, () -> specialDepot.addResources(ResourceType.SERVANT, 1));
        assertEquals(0, specialDepot.getAllResourceCount());
        specialDepot.addResources(ResourceType.COIN, 2);
        assertEquals(2, specialDepot.getAllResourceCount());
        specialDepot.addSpecialContainer(ResourceType.SERVANT);
        specialDepot.addResources(ResourceType.SERVANT, 1);
        assertEquals(3, specialDepot.getAllResourceCount());
        assertThrows(DepotException.class, () -> specialDepot.removeResources(ResourceType.SERVANT, 2));


        assertEquals(2, specialDepot.getSpecialContainers().get(0).getCount());
        assertEquals(1, specialDepot.getSpecialContainers().get(1).getCount());
        specialDepot.removeResources(ResourceType.COIN,1);
        assertEquals(2, specialDepot.getAllResourceCount());
        assertEquals(1, specialDepot.getSpecialContainers().get(0).getCount());
        assertEquals(1, specialDepot.getSpecialContainers().get(1).getCount());
    }

}