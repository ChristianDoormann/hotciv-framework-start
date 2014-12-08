package hotciv.standard.Alpha;

import hotciv.framework.Position;
import hotciv.standard.Interfaces.AttackingStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by Henrik on 17-11-14.
 */
public class AlphaAttackingStrategy implements AttackingStrategy {
    @Override
    public boolean attack(GameImpl game, Position from, Position to) {
        return true;
    }
}
