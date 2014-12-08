package hotciv.standard.Theta;

import hotciv.standard.Alpha.*;
import hotciv.standard.CivFactory;
import hotciv.standard.Gamma.GammaUnitActionStrategy;
import hotciv.standard.Interfaces.*;

/**
 * Created by Henrik on 24-11-14.
 */
public class ThetaCivFactory implements CivFactory {
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
        return new ThetaUnitActionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new AlphaWinningStrategy();
    }

    @Override
    public AllowedUnitStrategy createAllowedUnitStrategy() {
        return new ThetaAllowedUnitStrategy();
    }
}
