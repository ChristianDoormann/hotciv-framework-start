package hotciv.standard;

import hotciv.standard.Interfaces.*;

/**
 * Created by Messi on 20-11-2014.
 */
public interface CivFactory {

    public AgingStrategy createAgingStrategy();

    public AttackingStrategy createAttackingStrategy();

    public StartingMapStrategy createStartingMapStrategy();

    public UnitActionStrategy createUnitActionStrategy();

    public WinningStrategy createWinningStrategy();

    public AllowedUnitStrategy createAllowedUnitStrategy();
}
