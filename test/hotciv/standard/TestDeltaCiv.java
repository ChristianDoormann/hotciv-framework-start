package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Alpha.AlphaAgingStrategy;
import hotciv.standard.Alpha.AlphaUnitActionStrategy;
import hotciv.standard.Alpha.AlphaWinningStrategy;
import hotciv.standard.Delta.DeltaCivFactory;
import hotciv.standard.Delta.DeltaStartingMapStrategy;
import hotciv.standard.Gamma.GammaCivFactory;
import org.junit.*;

import static org.junit.Assert.*;
/**
 * Created by Messi on 13-11-2014.
 */
public class TestDeltaCiv {
    private GameImpl game;

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new DeltaCivFactory());
    }

    @Test
    public void shouldBeDifferentTilesAtDifferentPositions() {
        assertEquals("There should be an ocean at (6,6)", GameConstants.OCEANS ,game.getTileAt(new Position(6,6)).getTypeString());
        assertEquals("There should be an ocean at (15,15)", GameConstants.OCEANS, game.getTileAt(new Position(15, 15)).getTypeString());
        assertEquals("There should be a mountain at (11,3)", GameConstants.MOUNTAINS, game.getTileAt(new Position(11, 3)).getTypeString());
        assertEquals("There should be a mountain at (0,5)", GameConstants.MOUNTAINS, game.getTileAt(new Position(0, 5)).getTypeString());
        assertEquals("There should be a forest at (1,10)", GameConstants.FOREST ,game.getTileAt(new Position(1,10)).getTypeString());
        assertEquals("There should be a forest at (9,10)", GameConstants.FOREST ,game.getTileAt(new Position(9,10)).getTypeString());
        assertEquals("There should be a plains at (2,4)", GameConstants.PLAINS ,game.getTileAt(new Position(2,4)).getTypeString());
        assertEquals("There should be a plains at (11,1)", GameConstants.PLAINS ,game.getTileAt(new Position(11,1)).getTypeString());
        assertEquals("There should be a hill at (1,3)", GameConstants.HILLS ,game.getTileAt(new Position(1,3)).getTypeString());
        assertEquals("There should be a hill at (14,5)", GameConstants.HILLS ,game.getTileAt(new Position(14,5)).getTypeString());
    }

    @Test
    public void shouldBeCitysAtSpecificPositions() {
        assertEquals("There should be a red city at (8,12)",Player.RED ,game.getCityAt(new Position(8,12)).getOwner());
        assertEquals("There should be a blue city at (4,5)", Player.BLUE , game.getCityAt(new Position(4,5)).getOwner());
    }

    @Test
    public void shouldBeRedArcherAt3_8AndBlueLegionAt4_4() {
        assertEquals("There should be a red archer at (3,8)", GameConstants.ARCHER ,game.getUnitAt(new Position(3,8)).getTypeString() );
        assertEquals("There should be a blue legion at (4,4)", GameConstants.LEGION ,game.getUnitAt(new Position(4,4)).getTypeString() );
    }
}
