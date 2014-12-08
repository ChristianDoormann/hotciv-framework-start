package hotciv.standard.Interfaces;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created by Henrik on 12-11-14.
 */

public interface WinningStrategy {

    public Player getWinner( GameImpl game );
}
