package hotciv.standard.Alpha;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.standard.Interfaces.WinningStrategy;

/**
 * Created by Henrik on 12-11-14.
 */
public class AlphaWinningStrategy implements WinningStrategy {

    @Override
    public Player getWinner( GameImpl game ) {
        if( game.getAge() == -3000 ) {
            return Player.RED;
        }

        return null;
    }
}
