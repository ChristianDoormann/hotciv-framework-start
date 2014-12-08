package hotciv.standard.Interfaces;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

/**
 * Created by Henrik on 17-11-14.
 */
public interface AttackingStrategy {
    public boolean attack(GameImpl game, Position from, Position to);
}
