package hotciv.standard.Theta;

import hotciv.framework.GameConstants;
import hotciv.standard.Interfaces.AllowedUnitStrategy;

/**
 * Created by Henrik on 27-11-14.
 */
public class ThetaAllowedUnitStrategy implements AllowedUnitStrategy {
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

        if(unitType.equals(GameConstants.CHARIOT)){
            return true;
        }

        return false;
    }
}
