package hotciv.standard.Semi;

import hotciv.framework.*;
import hotciv.framework.GameConstants;
import hotciv.standard.*;
import hotciv.standard.Interfaces.UnitActionStrategy;

/**
 * Created by Christian Møller on 01-12-2014.
 */
public class SemiUnitActionStrategy implements UnitActionStrategy {
    @Override
    public void performAction(Position p, GameImpl game) {
        UnitImpl unit = game.getUnitAt(p);

        if (unit.getTypeString().equals(GameConstants.SETTLER)) {
            game.getUnitMap()[p.getRow()][p.getColumn()] = null;
            game.getCityMap()[p.getRow()][p.getColumn()] = new CityImpl(unit.getOwner());
        }
    }
}
