package hotciv.standard.Delta;

import hotciv.standard.Alpha.*;
import hotciv.standard.CivFactory;
import hotciv.standard.Interfaces.*;

/**
 * Created by Messi on 20-11-2014.
 */
public class DeltaCivFactory implements CivFactory {

    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public AttackingStrategy createAttackingStrategy() {
        return new AlphaAttackingStrategy();
    }

    @Override
    public StartingMapStrategy createStartingMapStrategy() {
        return new DeltaStartingMapStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new AlphaWinningStrategy();
    }

    @Override
    public AllowedUnitStrategy createAllowedUnitStrategy() {
        return new AlphaAllowedUnitStrategy();
    }
}
