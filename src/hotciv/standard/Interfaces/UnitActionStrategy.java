package hotciv.standard.Interfaces;

import hotciv.framework.*;
import hotciv.standard.GameImpl;

/**
 * Created by Messi on 13-11-2014.
 */
public interface UnitActionStrategy {
    public void performAction(Position p, GameImpl game);
}
