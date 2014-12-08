package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Alpha.AlphaStartingMapStrategy;
import hotciv.standard.Alpha.AlphaUnitActionStrategy;
import hotciv.standard.Beta.BetaAgingStrategy;
import hotciv.standard.Beta.BetaCivFactory;
import hotciv.standard.Beta.BetaWinningStrategy;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Henrik on 12-11-14.
 */

public class TestBetaCiv {

    private GameImpl game;

    /**
     * Fixture for betaciv testing.
     */
    @Before
    public void setUp() {
       game = new GameImpl(new BetaCivFactory());
    }

    @Test
    public void gameShouldBeAtAge3000BCAfter10Rounds() {
        endRound(); endRound(); endRound(); endRound(); endRound();
        endRound(); endRound(); endRound(); endRound(); endRound();
        assertEquals("Game should be at age -3000 after 10 rounds", -3000, game.getAge());
    }

    @Test
    public void gameShouldAge1YearAfterARoundInYear25BC(){
        game.setAge(-25);
        endRound();
        assertEquals("Game should be age 24BC" , -24 , game.getAge());
    }

    @Test
    public void gameShouldAge1YearAfterARoundInYear0(){
        game.setAge(0);
        endRound();
        assertEquals("Game should be age 1" , 1 , game.getAge());
    }

    @Test
    public void gameShouldAge50YearsAfterARoundInYear1200(){
        game.setAge(1200);
        endRound();
        assertEquals("Game should be age 1250" , 1250 , game.getAge());
    }

    @Test
    public void gameShouldAge25YearsAfterARoundInYear1800(){
        game.setAge(1800);
        endRound();
        assertEquals("Game should be age 1825" , 1825 , game.getAge());
    }

    @Test
    public void gameShouldAge5YearsAfterARoundInYear1910(){
        game.setAge(1910);
        endRound();
        assertEquals("Game should be age 1915" , 1915 , game.getAge());
    }

    @Test
    public void gameShouldAge1YearAfterARoundInYear1980(){
        game.setAge(1980);
        endRound();
        assertEquals("Game should be age 1981" , 1981 , game.getAge());
    }

    @Test
    public void playerRedShouldWinTheGameWhenPlayerBlueCityIsConquered(){
        game.getCityAt(new Position(4,1)).setOwner( Player.RED );
        endRound();
        assertEquals("Red should be the winner" , Player.RED , game.getWinner());
    }

    public void endRound() {
        game.endOfTurn();
        game.endOfTurn();
    }

}
