package hotciv.standard.Zeta;

import hotciv.standard.Alpha.*;
import hotciv.standard.Beta.BetaWinningStrategy;
import hotciv.standard.CivFactory;
import hotciv.standard.Epsilon.EpsilonWinningStrategy;
import hotciv.standard.Interfaces.*;

/**
 * Created by Messi on 20-11-2014.
 */
public class ZetaCivFactory implements CivFactory {

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
        return new AlphaStartingMapStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new ZetaWinningStrategy(new BetaWinningStrategy(),new EpsilonWinningStrategy());
    }

    @Override
    public AllowedUnitStrategy createAllowedUnitStrategy() {
        return new AlphaAllowedUnitStrategy();
    }
}
