package hotciv.observer;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.Semi.SemiCivFactory;
import hotciv.standard.StaticDiceStrategy;
import hotciv.standard.UnitImpl;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Henrik on 08-12-14.
 */
public class TestObserver {

    private GameImpl game;
    private ObserverGame observer;
    private String msg;

    @Before
    public void setUp() {
        game = new GameImpl(new SemiCivFactory(new StaticDiceStrategy(1)));
        observer = new ObserverGame();
        game.addObserver(observer);
        msg = new String();
    }

    @Test
    public void observerShouldHavePlayerBlueAfterOneTurn(){
        game.endOfTurn();
        msg = "Turn has ended";
        assertEquals("The message in observer should be " + msg, msg, observer.msg);
    }

    @Test
    public void observerShouldHavePosition3_9AfterUnitMove(){
        game.moveUnit(new Position(3,8), new Position(3,9));
        msg = "WorldChangedAt (3,8) WorldChangedAt (3,9) ";
        assertEquals("The message in observer should be " + msg, msg, observer.msg);
    }

    @Test
    public void ShouldOnlyCallWorldChangedAtOneTimeInCaseOfLostAttack() {
        game.getUnitMap()[8][4] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        game.getUnitMap()[8][5] = new UnitImpl(GameConstants.ARCHER, Player.BLUE);
        game.moveUnit(new Position(8,4), new Position(8,5));
        //Now the red archer should loose the battle, and the observer should only call
        //worldChangedAt en gang, på (8,4)
        msg = "WorldChangedAt (8,4) ";
        assertEquals("The message in observer should be " + msg, msg, observer.msg);
    }

    @Test
    public void shouldCallWorldChangedAtOnUnitAction() {
        game.getUnitMap()[8][4] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        game.performUnitActionAt(new Position(8, 4));
        msg = "WorldChangedAt (8,4) ";
        assertEquals("The message in observer should be " + msg , msg , observer.msg);
    }

    @Test
    public void shouldCallWorldChangedAtWhenSpawningUnit() {
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        //Now both cities should produce an archer. 3x end of turns then 2x worldChangedAt and last 1x end of turn.
        msg = "Turn has endedTurn has endedTurn has endedWorldChangedAt (4,5) WorldChangedAt (8,12) Turn has ended";
        assertEquals("The message in observer should be " + msg , msg , observer.msg);
    }




}
