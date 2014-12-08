package hotciv.standard.Beta;

import hotciv.standard.Alpha.AlphaAllowedUnitStrategy;
import hotciv.standard.Alpha.AlphaAttackingStrategy;
import hotciv.standard.Alpha.AlphaStartingMapStrategy;
import hotciv.standard.Alpha.AlphaUnitActionStrategy;
import hotciv.standard.CivFactory;
import hotciv.standard.Interfaces.*;

/**
 * Created by Messi on 20-11-2014.
 */
public class BetaCivFactory implements CivFactory {

    @Override
    public AgingStrategy createAgingStrategy() {
        return new BetaAgingStrategy();
    }

    @Override
    public AttackingStrategy createAttackingStrategy() {
        return new AlphaAttackingStrategy();
    }

    @Override
    public StartingMapStrategy createStartingMapStrategy() {
        return new AlphaStartingMapStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new BetaWinningStrategy();
    }

    @Override
    public AllowedUnitStrategy createAllowedUnitStrategy() {
        return new AlphaAllowedUnitStrategy();
    }
}
