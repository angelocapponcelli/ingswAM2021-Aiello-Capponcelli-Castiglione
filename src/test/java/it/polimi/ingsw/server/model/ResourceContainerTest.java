package it.polimi.ingsw.server.model;

import it.polimi.ingsw.utils.exceptions.DepotException;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.SpecialContainer;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.StrongBoxContainer;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.WareHouseContainer;
import it.polimi.ingsw.server.model.resources.ResourceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceContainerTest {

    @Test
    void StrongBoxContainer() throws DepotException {
        StrongBoxContainer strongBoxContainer = new StrongBoxContainer(ResourceType.COIN);
        assertThrows(DepotException.class, () -> strongBoxContainer.addResource(-1));
        assertThrows(DepotException.class, () -> strongBoxContainer.remove(1));
        assertThrows(DepotException.class, () -> strongBoxContainer.remove(-1));
        strongBoxContainer.addResource(20);
        assertEquals(20, strongBoxContainer.getCount());
        assertThrows(DepotException.class, () -> strongBoxContainer.remove(21));
        strongBoxContainer.remove(20);
        assertEquals(0, strongBoxContainer.getCount());
        strongBoxContainer.addResource(1);
        assertEquals(1, strongBoxContainer.getCount());
        assertThrows(DepotException.class, () -> strongBoxContainer.remove(2));
    }

    @Test
    void SpecialContainer() throws DepotException {
        SpecialContainer specialContainer = new SpecialContainer(ResourceType.SERVANT);
        assertThrows(DepotException.class, () -> specialContainer.addResource(-1));
        assertThrows(DepotException.class, () -> specialContainer.remove(1));
        assertThrows(DepotException.class, () -> specialContainer.remove(-1));
        assertThrows(DepotException.class, () -> specialContainer.remove(3));
        specialContainer.addResource(2);
        assertEquals(2, specialContainer.getCount());
        assertThrows(DepotException.class, () -> specialContainer.remove(3));
        specialContainer.remove(2);
        assertEquals(0, specialContainer.getCount());
        specialContainer.addResource(1);
        assertEquals(1, specialContainer.getCount());
        assertThrows(DepotException.class, () -> specialContainer.remove(2));
    }

    @Test
    void WareHouseContainer() throws DepotException {
        WareHouseContainer wareHouseContainer = new WareHouseContainer(3);
        assertNull(wareHouseContainer.getType());
        assertThrows(DepotException.class, () -> wareHouseContainer.addResource(-1));
        assertThrows(DepotException.class, () -> wareHouseContainer.remove(1));
        assertThrows(DepotException.class, () -> wareHouseContainer.remove(-1));
        assertThrows(DepotException.class, () -> wareHouseContainer.remove(4));
        wareHouseContainer.addResource(2);
        assertEquals(2, wareHouseContainer.getCount());
        assertThrows(DepotException.class, () -> wareHouseContainer.remove(3));
        wareHouseContainer.remove(2);
        assertEquals(0, wareHouseContainer.getCount());
        wareHouseContainer.addResource(2);
        assertEquals(2, wareHouseContainer.getCount());
        assertThrows(DepotException.class, () -> wareHouseContainer.remove(3));
        assertThrows(DepotException.class, () -> wareHouseContainer.setResourceType(ResourceType.COIN));
        assertThrows(DepotException.class, () -> wareHouseContainer.setCapacity(1));
        wareHouseContainer.setCapacity(2);
        assertEquals(2, wareHouseContainer.getCapacity());
        assertThrows(DepotException.class, () -> wareHouseContainer.addResource(1));
        wareHouseContainer.remove(2);
        assertEquals(0, wareHouseContainer.getCount());
        wareHouseContainer.setResourceType(ResourceType.COIN);
        assertEquals(ResourceType.COIN, wareHouseContainer.getType());

    }

}