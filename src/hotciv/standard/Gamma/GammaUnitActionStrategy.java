package hotciv.standard.Gamma;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.standard.Interfaces.UnitActionStrategy;

/**
 * Created by Messi on 13-11-2014.
 */
public class GammaUnitActionStrategy implements UnitActionStrategy {

    @Override
    public void performAction(Position p, GameImpl game) {
        UnitImpl unit = game.getUnitAt(p);

        if (unit.getTypeString().equals(GameConstants.SETTLER)) {
            game.getUnitMap()[p.getRow()][p.getColumn()] = null;
            game.getCityMap()[p.getRow()][p.getColumn()] = new CityImpl(unit.getOwner());
        }

        if (unit.getTypeString().equals(GameConstants.ARCHER )) {
            if (unit.getAllowMovement()) {
                unit.setDefenseStrength(unit.getDefensiveStrength() * 2); //Doubles the units defenseStrength
                unit.setAllowedMovement(false);
            }
            else {
                unit.setDefenseStrength(unit.getDefensiveStrength() / 2);
                unit.setAllowedMovement(true);
            }
        }
    }
}
