package hotciv.standard;

import hotciv.framework.Tile;

/**
 * Created by ThomasMøller on 05-11-2014.
 */
public class TileImpl implements Tile {

    private String type;

    public TileImpl(String type) {
        this.type = type;
    }

    @Override
    public String getTypeString() {
        return type;
    }
}
