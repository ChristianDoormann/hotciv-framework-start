package hotciv.standard.Zeta;

import hotciv.framework.Player;
import hotciv.standard.Beta.BetaWinningStrategy;
import hotciv.standard.Epsilon.EpsilonWinningStrategy;
import hotciv.standard.GameImpl;
import hotciv.standard.Interfaces.WinningStrategy;

/**
 * Created by Messi on 19-11-2014.
 */
public class ZetaWinningStrategy implements WinningStrategy {

    private WinningStrategy beforeRound20;
    private WinningStrategy afterRound20;
    private WinningStrategy currentStrategy;

    public ZetaWinningStrategy(WinningStrategy beforeRound20, WinningStrategy afterRound20) {

        this.afterRound20 = afterRound20;
        this.beforeRound20 = beforeRound20;
        currentStrategy = null;
    }

    @Override
    public Player getWinner(GameImpl game) {
        if (game.getRound() <= 20) {
            game.resetSuccessfulAttacks();
            currentStrategy = beforeRound20;
        }
        if (game.getRound() > 20) {
            currentStrategy = afterRound20;
        }

        return currentStrategy.getWinner(game) ;
    }
}
