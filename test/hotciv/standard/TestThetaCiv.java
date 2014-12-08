package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Theta.ThetaCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Henrik on 24-11-14.
 */
public class TestThetaCiv {

    private GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(new ThetaCivFactory());
        UnitImpl unit = new UnitImpl(GameConstants.CHARIOT, Player.RED);
        game.getUnitMap()[13][12] = unit;
    }

    @Test
    public void shouldReturn3AttackStrengthForChariot(){
        assertEquals("Chariot should have attackstrength 3", 3 , game.getUnitAt(new Position(13,12)).getAttackingStrength());
    }

    @Test
    public void shouldHave2DefenseWhenActionOnChariot(){
        game.performUnitActionAt(new Position(13,12));
        assertEquals("", 2, game.getUnitAt(new Position(13, 12)).getDefensiveStrength());
    }

    @Test
    public void shouldBeAbleToChangeProductionToChariot(){
        game.changeProductionInCityAt( new Position(1,1) , GameConstants.CHARIOT);
        assertTrue("Should not be able to change productiontype in city to unknown type", game.getCityAt(new Position(1, 1)).getProduction().equals(GameConstants.CHARIOT));
    }

}
