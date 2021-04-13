package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceContainerTest {

    @Test
    void multipleAddRemove() {
        ResourceContainer resourceContainer = new ResourceContainer(ResourceType.COIN, -1);
        assertThrows(DepotException.class, () -> resourceContainer.add(-1));
        assertThrows(DepotException.class, () -> resourceContainer.remove());
        assertThrows(DepotException.class, () -> resourceContainer.remove(-1));
        try {
            resourceContainer.add(20);
        } catch (DepotException e) {
            e.printStackTrace();
        }
        assertEquals(20, resourceContainer.getCount());
        assertThrows(DepotException.class, () -> resourceContainer.remove(21));
        try {
            resourceContainer.remove(20);
        } catch (DepotException e) {
            e.printStackTrace();
        }
        assertEquals(0, resourceContainer.getCount());
        try {
            resourceContainer.add();
        } catch (DepotException e) {
            e.printStackTrace();
        }
        assertEquals(1, resourceContainer.getCount());
        assertThrows(DepotException.class, () -> resourceContainer.remove(2));
    }

}