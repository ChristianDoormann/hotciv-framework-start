package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.Semi.SemiCivFactory;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by Henrik on 24-11-14.
 */
public class TestSemiCiv {

    private GameImpl game;

    @Before
    public void setUp(){
        game = new GameImpl(new SemiCivFactory());
    }

    @Test
    public void shouldBeAbleToUseSettlerUnitAction() {
        game.getUnitMap()[13][13] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        game.getCityMap()[13][13] = null;
        game.performUnitActionAt(new Position(13,13));
        assertNotNull("There should be a city at 13,13" , game.getCityAt(new Position(13,13)));
    }

    @Test
    public void shouldNotBeAbleToUseArcherUnitAction() {
        game.getUnitMap()[13][13] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        int strBefore = game.getUnitAt(new Position(13,13)).getDefensiveStrength();
        game.performUnitActionAt(new Position(13,13));
        int strAfter = game.getUnitAt(new Position(13,13)).getDefensiveStrength();
        assertEquals("Archer should have the same defenseStrength as before unitAction invoked",strBefore , strAfter );
    }

}