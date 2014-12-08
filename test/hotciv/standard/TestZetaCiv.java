package hotciv.standard;
import hotciv.framework.*;

import hotciv.standard.Zeta.ZetaCivFactory;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Messi on 19-11-2014.
 */
public class TestZetaCiv {

    GameImpl game;

    @Before
    public void setUp() {
        //We use the standard constructor for GameImpl
        game = new GameImpl(new ZetaCivFactory());
    }

    @Test
    public void shouldReturnRedAsWinnerWhenRedHasAllCitiesAfter6Rounds() {
        //Then we run 6 rounds
        runXRounds(6);
        assertEquals("It should be at round 7 after 6 endRounds" ,7 ,game.getRound() );
        //There is a blue city at (4,1) which we overwrite with a red city. Then Red has all cities.
        game.getCityMap()[4][1] = new CityImpl(Player.RED);
        game.endOfTurn(); //the check for the winner is first made after endOfTurn.
        assertEquals("Red should be the winner", Player.RED, game.getWinner());
    }

    @Test
    public void shouldReturnBlueAsWinnerWhenBlueHasAllCitiesAfter18Rounds() {
        //First we run 18 rounds
        runXRounds(18);
        //There is a red city at (1,1) which we overwrite with a blue city. Then blue has all cities.
        game.getCityMap()[1][1] = new CityImpl(Player.BLUE);
        assertEquals("It should be at round 19 after 18 endRounds", 19, game.getRound());
        game.endOfTurn(); //the check for the winner is first made after endOfTurn.
        assertEquals("Blue should be the winner",Player.BLUE , game.getWinner());
    }

    @Test
    public void shouldNotReturnBlueAsWinnerInRound24ThoughBlueHasAllCities() {
        //First we run 20 rounds
        runXRounds(20);
        game.getCityMap()[1][1] = new CityImpl(Player.BLUE);
        game.endOfTurn();
        assertFalse("Blue cannot be the winner after 20 rounds though blue has all cities", game.getWinner() == Player.BLUE);
    }

    @Test
    public void shouldReturnBlueAsWinnerWhen3successfulAttacksHasBeenMadeByBlueInRound23() {
        runXRounds(22);
        game.endOfTurn();
        game.getUnitMap()[12][12] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);
        game.getUnitMap()[12][13] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);
        game.getUnitMap()[12][14] = new UnitImpl(GameConstants.ARCHER,Player.BLUE);
        game.getUnitMap()[13][12] = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.getUnitMap()[13][13] = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.getUnitMap()[13][14] = new UnitImpl(GameConstants.SETTLER,Player.RED);
        game.getTileMap()[12][13] = new TileImpl(GameConstants.HILLS);
        game.getTileMap()[12][12] = new TileImpl(GameConstants.HILLS);
        game.getTileMap()[12][14] = new TileImpl(GameConstants.HILLS);
        //The following attacks will guarantee that blue has 3 successful attacks.
        assertTrue("This archer should win", game.moveUnit(new Position(12,12),new Position(13,12)));
        assertTrue("This archer should win", game.moveUnit(new Position(12, 13), new Position(13, 13)));
        assertTrue("This archer should win", game.moveUnit(new Position(12, 14), new Position(13, 14)));
        game.endOfTurn();
        assertEquals("Blue should be yield as winner" ,Player.BLUE , game.getWinner());
    }

    public void runXRounds(int x) {
        for (int i=0; i<x; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }


}
