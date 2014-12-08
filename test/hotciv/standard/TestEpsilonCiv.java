package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Alpha.AlphaAgingStrategy;
import hotciv.standard.Alpha.AlphaStartingMapStrategy;
import hotciv.standard.Alpha.AlphaUnitActionStrategy;
import hotciv.standard.Epsilon.EpsilonAttackingStrategy;
import hotciv.standard.Epsilon.EpsilonCivFactory;
import hotciv.standard.Epsilon.EpsilonWinningStrategy;
import hotciv.standard.Interfaces.AttackingStrategy;
import hotciv.standard.Interfaces.DiceStrategy;
import hotciv.standard.Interfaces.WinningStrategy;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Henrik on 17-11-14.
 */
public class TestEpsilonCiv {

    private GameImpl game;

    @Before
    public void setUp(){
        game = new GameImpl(new EpsilonCivFactory(new StaticDiceStrategy(6)));
    }

    @Test
    public void shouldReturnRedAsWinnerWhenRedHas3SuccessfulAttacks() {

        //Inserting 4 units to test with
        game.getUnitMap()[2][8] = new UnitImpl(GameConstants.LEGION,Player.RED);
        game.getUnitMap()[2][9] = new UnitImpl(GameConstants.SETTLER,Player.BLUE);
        game.getUnitMap()[2][10] = new UnitImpl(GameConstants.SETTLER,Player.BLUE);
        game.getUnitMap()[2][11] = new UnitImpl(GameConstants.SETTLER,Player.BLUE);
        game.getTileMap()[2][8] = new TileImpl(GameConstants.HILLS);
        game.moveUnit(new Position(2,8),new Position(2,9));
        //Now blue has one successful attack
        assertEquals("Red should have one successful attack", 1, game.getSuccessfulAttacks(Player.RED));
        endOfRound();
        game.getTileMap()[2][9] = new TileImpl(GameConstants.HILLS);
        game.moveUnit(new Position(2, 9), new Position(2,10));
        //Now blue has two successful attacks
        assertEquals("Red should have two successful attacks",2 ,game.getSuccessfulAttacks(Player.RED) );
        endOfRound();
        game.getTileMap()[2][10] = new TileImpl(GameConstants.HILLS);
        game.moveUnit(new Position(2,10),new Position(2,11));
        game.endOfTurn();
        //Now according to Alphaciv attacking strategy, the blue archer at starting position (2,0), has slain
        //3 red units.
        assertEquals("Red should win after 3 successful attacks", Player.RED, game.getWinner());
    }

    @Test
    public void shouldReturnBlueAsWinnerWhenBlueHas3SuccessfulAttacks() {

        //Inserting 4 units to test with
        game.getUnitMap()[2][8] = new UnitImpl(GameConstants.LEGION,Player.BLUE);
        game.getUnitMap()[2][9] = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.getUnitMap()[2][10] = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.getUnitMap()[2][11] = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.endOfTurn();
        game.getTileMap()[2][8] = new TileImpl(GameConstants.HILLS);
        game.moveUnit(new Position(2,8),new Position(2,9));
        //Now blue has one successful attack
        assertEquals("Blue should have one successful attack",1 ,game.getSuccessfulAttacks(Player.BLUE));

        endOfRound();
        game.getTileMap()[2][9] = new TileImpl(GameConstants.HILLS);
        game.moveUnit(new Position(2, 9), new Position(2,10));
        //Now blue has two successful attacks
        assertEquals("Blue should have two successful attacks",2 ,game.getSuccessfulAttacks(Player.BLUE) );

        endOfRound();
        game.getTileMap()[2][10] = new TileImpl(GameConstants.HILLS);
        game.moveUnit(new Position(2,10),new Position(2,11));
        game.endOfTurn();
        //Now according to Alphaciv attacking strategy, the blue archer at starting position (2,0), has slain
        //3 red units.
        assertEquals("Blue should win after 3 successful attacks", Player.BLUE, game.getWinner());
    }

    @Test
    public void shouldNotBeAbleToAttackWhenSettlerUnit(){
        //Lets try to move red settler to blue legions position
        assertFalse("Settler should not be able to attack" , game.moveUnit(new Position(4,3) , new Position(3,2)) );
    }

    @Test
    public void shouldLooseAndWinRespectivelyForArchersAttackingSettlersFromDifferentTiles(){
        game.endOfTurn();
        game.getUnitMap()[12][12] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);
        game.getUnitMap()[12][13] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);
        game.getUnitMap()[12][14] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);
        game.getUnitMap()[13][12] = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.getUnitMap()[13][13] = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.getUnitMap()[13][14] = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.getTileMap()[12][13] = new TileImpl(GameConstants.HILLS);
        game.getCityMap()[12][14] = new CityImpl(Player.BLUE);

        assertFalse("This archer should loose", game.moveUnit(new Position(12,12),new Position(13,12)));
        assertTrue("This archer should win", game.moveUnit(new Position(12, 13), new Position(13, 13)));
        assertTrue("This archer should win", game.moveUnit(new Position(12, 14), new Position(13, 14)));
    }

    @Test
    public void shouldBeWinForRedLegionOverBlueArcher() {

        EpsilonAttackingStrategy as = new EpsilonAttackingStrategy(new StaticDiceStrategy(6));
        game = new GameImpl(new EpsilonCivFactory(new StaticDiceStrategy(6)));

        game.getUnitMap()[13][12] = new UnitImpl(GameConstants.LEGION,Player.RED);
        game.getUnitMap()[12][13] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);

        assertEquals("Red attacking Legion at position 13,12 should have 4 attackingStrength", 4 , as.getStrength(game, new Position(13,12), "ATTACK"));
        assertEquals("Blue defending Archer at position 12,13 should have 3 attackingStrength", 3 , as.getStrength(game, new Position(12,13), "DEFENSE"));
        assertTrue("Red legion should defeat blue archer", game.moveUnit(new Position(13,12),new Position(12,13)));
    }

    @Test
    public void shouldBeLooseForRedLegionOverBlueArcherWithAdjacentUnit() {

        EpsilonAttackingStrategy as = new EpsilonAttackingStrategy(new StaticDiceStrategy(6));
        game = new GameImpl(new EpsilonCivFactory(new StaticDiceStrategy(6)));

        game.getUnitMap()[13][12] = new UnitImpl(GameConstants.LEGION,Player.RED);
        game.getUnitMap()[12][13] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);
        game.getUnitMap()[12][14] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);

        assertEquals("Red attacking Legion at position 13,12 should have 4 attackingStrength", 4 , as.getStrength(game, new Position(13,12), "ATTACK"));
        assertEquals("Blue defending Archer at position 12,13 should have 4 attackingStrength", 4 , as.getStrength(game, new Position(12,13), "DEFENSE"));
        assertFalse("Red legion should loose against blue archer with adjacent unit", game.moveUnit(new Position(13,12),new Position(12,13)));
    }

    @Test
    public void shouldBeWinForRedLegionOverBlueArcherWhenOnHills() {

        EpsilonAttackingStrategy as = new EpsilonAttackingStrategy(new StaticDiceStrategy(6));
        game = new GameImpl(new EpsilonCivFactory(new StaticDiceStrategy(6)));

        game.getUnitMap()[13][12] = new UnitImpl(GameConstants.LEGION,Player.RED);
        game.getUnitMap()[12][13] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);
        game.getUnitMap()[12][14] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);
        game.getTileMap()[13][12] = new TileImpl(GameConstants.HILLS);

        assertEquals("Red attacking Legion at position 13,12 should have 8 attackingStrength", 8 , as.getStrength(game, new Position(13,12), "ATTACK"));
        assertEquals("Blue defending Archer at position 12,13 should have 4 attackingStrength", 4 , as.getStrength(game, new Position(12,13), "DEFENSE"));
        assertTrue("Red legion should loose against blue archer with adjacent unit", game.moveUnit(new Position(13,12),new Position(12,13)));
    }

    private void endOfRound() {
        game.endOfTurn();
        game.endOfTurn();
    }
}
