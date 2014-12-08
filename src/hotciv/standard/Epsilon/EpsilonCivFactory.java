package hotciv.standard.Epsilon;

import hotciv.standard.Alpha.AlphaAgingStrategy;
import hotciv.standard.Alpha.AlphaAllowedUnitStrategy;
import hotciv.standard.Alpha.AlphaStartingMapStrategy;
import hotciv.standard.Alpha.AlphaUnitActionStrategy;
import hotciv.standard.CivFactory;
import hotciv.standard.Interfaces.*;

/**
 * Created by Messi on 20-11-2014.
 */
public class EpsilonCivFactory implements CivFactory {

    private DiceStrategy diceStrategy;


    //Standard constructor, that uses RandomDiceStrategy
    public EpsilonCivFactory() {
        this(new RandomDiceStrategy());
    }

    //Constructor where you choose the DiceStrategy
    public EpsilonCivFactory(DiceStrategy diceStrategy) {
        this.diceStrategy = diceStrategy;
    }


    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public AttackingStrategy createAttackingStrategy() {
        return new EpsilonAttackingStrategy(diceStrategy);
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
        return new EpsilonWinningStrategy();
    }

    @Override
    public AllowedUnitStrategy createAllowedUnitStrategy() {
        return new AlphaAllowedUnitStrategy();
    }
}
