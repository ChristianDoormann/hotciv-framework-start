package hotciv.standard;
import hotciv.framework.*;

import hotciv.standard.Alpha.AlphaStartingMapStrategy;
import hotciv.standard.Beta.BetaAgingStrategy;
import hotciv.standard.Beta.BetaWinningStrategy;
import hotciv.standard.Gamma.GammaCivFactory;
import hotciv.standard.Gamma.GammaUnitActionStrategy;
import org.junit.*;

import static org.junit.Assert.*;
/**
 * Created by Messi on 13-11-2014.
 */


public class TestGammaCiv {

    GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(new GammaCivFactory());
    }

    @Test
    public void shouldTransformSettlerToCityAtSettlersPositionWhenPerformActionCalled() {
        //There is a red settler at (4,3)
        game.performUnitActionAt(new Position(4,3)); //Now Settler should be city
        assertEquals("There should be a red city at (4,3)" ,Player.RED ,game.getCityAt(new Position(4,3)).getOwner() );
    }

    @Test
    public void shouldReturn1InSizeJustAfterCreatedFromSettlerActionPerformed() {
        game.performUnitActionAt(new Position(4,3));
        assertEquals("THere should be size 1 in the new created city", 1, game.getCityAt(new Position(4, 3)).getSize());
    }

    @Test
    public void shouldDoubleDefenseStrengthOnArcherWhenActionPerformed() {
        UnitImpl unit = game.getUnitAt(new Position(2,0));
        int beforeDefenseStrength = unit.getDefensiveStrength();
        game.performUnitActionAt(new Position (2,0));
        assertEquals("The defense Strength should be doubled" ,2*beforeDefenseStrength , unit.getDefensiveStrength());
    }

    @Test
    public void shouldNotBeAbleToMoveArcherAfterFirstTimeActionPerformed() {
        //Its reds turn, and the archer we move is red, so the archer has still one movement (not considering fortify)
        game.performUnitActionAt(new Position (2,0));
        assertFalse("The move method should return false", game.moveUnit(new Position(2, 0), new Position(2, 1)));
    }

    @Test
    public void shouldMakeArcherMovableAfterActionPerformedHasRunTwice() {
        game.performUnitActionAt(new Position (2,0));
        assertFalse("The move method should return false" ,game.moveUnit(new Position(2,0), new Position(2,1)));
        game.performUnitActionAt(new Position (2,0));
        assertEquals("Moving the archer after two times action performed should return true" ,true ,game.moveUnit(new Position(2,0), new Position(2,1)));
    }
}
