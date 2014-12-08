package hotciv.standard.Alpha;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.standard.Interfaces.AllowedUnitStrategy;

/**
 * Created by Henrik on 27-11-14.
 */
public class AlphaAllowedUnitStrategy implements AllowedUnitStrategy {

    @Override
    public boolean isValidUnitType(String unitType) {
        if(unitType.equals(GameConstants.ARCHER)){
            return true;
        }

        if(unitType.equals(GameConstants.LEGION)){
            return true;
        }

        if(unitType.equals(GameConstants.SETTLER)){
            return true;
        }

        return false;
    }
}
