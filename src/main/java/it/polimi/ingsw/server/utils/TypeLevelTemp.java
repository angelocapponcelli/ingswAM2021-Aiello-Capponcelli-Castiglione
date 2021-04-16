package it.polimi.ingsw.server.utils;

import it.polimi.ingsw.server.model.Colors;

public class TypeLevelTemp {
    private Colors type;
    private Integer level;

    public TypeLevelTemp(Colors type, Integer level) {
        this.type = type;
        this.level = level;
    }

    public Colors getType() {
        return type;
    }

    public Integer getLevel() {
        return level;
    }
}
