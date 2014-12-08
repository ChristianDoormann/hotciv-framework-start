package hotciv.observer;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.Semi.SemiCivFactory;
import hotciv.standard.StaticDiceStrategy;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Henrik on 08-12-14.
 */
public class TestObserver {

    private GameImpl game;
    private ObserverGame observer;

    @Before
    public void setUp() {
        game = new GameImpl(new SemiCivFactory(new StaticDiceStrategy(1)));
        observer = new ObserverGame();
        game.addObserver(observer);
    }

    @Test
    public void observerShouldHavePlayerBlueAfterOneTurn(){
        game.endOfTurn();
        assertEquals("Observer should have blue as player after one turn", Player.BLUE, observer.player);
    }

    @Test
    public void observerShouldHavePosition3_9AfterUnitMove(){
        game.moveUnit(new Position(3,8), new Position(3,9));
        assertEquals("observer should have column value 9 after move of unit", observer.position.getColumn() , 9);
        assertEquals("observer should have row value 3 after move of unit", observer.position.getRow() , 3);
    }

}
