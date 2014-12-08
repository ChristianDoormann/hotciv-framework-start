package hotciv.standard.Epsilon;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.standard.Interfaces.WinningStrategy;

/**
 * Created by Henrik on 17-11-14.
 */
public class EpsilonWinningStrategy implements WinningStrategy {

    @Override
    public Player getWinner(GameImpl game) {

        if (game.getSuccessfulAttacks(Player.BLUE) > 2) {
            return Player.BLUE;
        }

        if (game.getSuccessfulAttacks(Player.RED) > 2) {
            return Player.RED;
        }

        return null;
    }
}
