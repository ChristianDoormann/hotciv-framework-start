package hotciv.standard.Semi;

import hotciv.standard.Alpha.AlphaAllowedUnitStrategy;
import hotciv.standard.Beta.BetaAgingStrategy;
import hotciv.standard.CivFactory;
import hotciv.standard.Delta.DeltaStartingMapStrategy;
import hotciv.standard.Epsilon.EpsilonAttackingStrategy;
import hotciv.standard.Epsilon.EpsilonWinningStrategy;
import hotciv.standard.Epsilon.RandomDiceStrategy;
import hotciv.standard.Interfaces.*;

/**
 * Created by Henrik on 24-11-14.
 */
public class SemiCivFactory implements CivFactory {

    private DiceStrategy diceStrategy;

    public SemiCivFactory() {
        this.diceStrategy = new RandomDiceStrategy();
    }

    public SemiCivFactory(DiceStrategy diceStrategy) {
        this.diceStrategy = diceStrategy;
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new BetaAgingStrategy();
    }

    @Override
    public AttackingStrategy createAttackingStrategy() {
        return new EpsilonAttackingStrategy(diceStrategy);
    }

    @Override
    public StartingMapStrategy createStartingMapStrategy() {
        return new DeltaStartingMapStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new SemiUnitActionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new EpsilonWinningStrategy();
    }

    @Override
    public AllowedUnitStrategy createAllowedUnitStrategy() {
        return new AlphaAllowedUnitStrategy();
    }
}
