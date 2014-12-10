package hotciv.stub;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.CivFactory;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by Henrik on 10-12-14.
 */
public class StubGame3 extends GameImpl {

    public StubGame3(CivFactory civFactory) {
        super(civFactory);
    }

    public void performUnitActionAt( Position p ){
        UnitImpl unit = getUnitAt(p);
        //Settler remove
        super.getUnitMap()[p.getRow()][p.getColumn()] = null;
        super.notifyObserversWorldChanged(p);
        //Insert city
        super.getCityMap()[p.getRow()][p.getColumn()] = new CityImpl(unit.getOwner());
        super.notifyObserversWorldChanged(p);
    }

}
