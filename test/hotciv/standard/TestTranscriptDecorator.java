package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.Semi.SemiCivFactory;
import hotciv.standard.Theta.ThetaCivFactory;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by Christian Møller on 02-12-2014.
 */
public class TestTranscriptDecorator {

    private TranscriptionGameDecorator decorator;
    private GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(new SemiCivFactory(new StaticDiceStrategy(1)));
        decorator = new TranscriptionGameDecorator(game);
    }

    @Test
    public void shouldTranscriptWhenMovingRedArcher() {
        assertEquals("There should be a red Archer at 3,8", Player.RED, decorator.getUnitAt(new Position(3, 8)).getOwner());
        decorator.moveUnit(new Position(3, 8), new Position(3, 9));
        String msg = "RED moves archer from (3,8) to (3,9).";
        assertEquals("There should be a transcript of the unitMove", msg, decorator.getTranscript(0));
    }

    @Test
    public void shouldTranscriptWhenMovingBlueLegion() {
        assertEquals("There should be a blue Legion at 4,4", Player.BLUE, decorator.getUnitAt(new Position(4, 4)).getOwner());
        decorator.moveUnit(new Position(4, 4), new Position(4, 3));
        String msg = "BLUE moves legion from (4,4) to (4,3).";
        assertEquals("There should be a transcript of the unitMove", msg, decorator.getTranscript(0));
    }

    @Test
    public void shouldTranscriptWhenCityChangesProduction() {
        assertEquals("There should be a red city at (8,12)", Player.RED, decorator.getCityAt(new Position(8, 12)).getOwner());
        decorator.changeProductionInCityAt(new Position(8, 12), GameConstants.ARCHER);
        String msg = "RED changes production in city at (8,12) to archer.";
        assertEquals("There should be transcript when city changes production", msg, decorator.getTranscript(0));
    }

    @Test
    public void shouldTranscriptWhenEndTurn() {
        decorator.endOfTurn();
        String msg = "RED finished his turn";
        assertEquals("There should be transcript when ending turn", msg, decorator.getTranscript(0));
        decorator.endOfTurn();
        msg = "BLUE finished his turn";
        assertEquals("There should be transcript when ending turn", msg, decorator.getTranscript(1));
    }

    @Test
    public void shouldTranscriptWhenWeHaveAWinner() {
        game.getUnitMap()[6][3] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        game.getUnitMap()[6][5] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        game.getUnitMap()[8][5] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        game.getUnitMap()[7][4] = new UnitImpl(GameConstants.LEGION, Player.RED);

        game.moveUnit(new Position(7,4) , new Position(6,3));
        game.endOfTurn(); game.endOfTurn(); //run a round
        game.moveUnit(new Position(6,3) , new Position(7,4));
        game.endOfTurn(); game.endOfTurn();
        game.moveUnit(new Position(7,4), new Position(6,5));
        game.endOfTurn(); game.endOfTurn();
        game.moveUnit(new Position(6,5), new Position(7,4));
        game.endOfTurn(); game.endOfTurn();
        game.moveUnit(new Position(7,4), new Position(8,5));
        //Red now has 3 successful attacks, and have won the game.
        decorator.getWinner();
        String msg = "RED wins the game.";
        assertEquals("There should be a transcript saying red is the winner." , msg , decorator.getTranscript(0));
    }

    @Test
    public void shouldTranscriptWhenPerformingUnitActionOnArcher() {
        //We will test this method with ThetaCivFactory, getting max. amount of unit actions.
        game = new GameImpl(new ThetaCivFactory());
        decorator = new TranscriptionGameDecorator(game);
        UnitImpl unit = new UnitImpl(GameConstants.ARCHER, Player.RED);
        game.getUnitMap()[7][4] = unit;
        decorator.performUnitActionAt(new Position(7,4));
        String msg = "RED performs fortify on archer at (7,4).";
        assertEquals("There should be transcript of performing unit action" , msg, decorator.getTranscript(0));
    }

    @Test
    public void shouldTranscriptWhenPerformingUnitActionOnChariot() {
        //We will test this method with ThetaCivFactory, getting max. amount of unit actions.
        game = new GameImpl(new ThetaCivFactory());
        decorator = new TranscriptionGameDecorator(game);
        game.endOfTurn(); //setting playerInTurn = BLUE
        UnitImpl unit = new UnitImpl(GameConstants.CHARIOT, Player.BLUE);
        game.getUnitMap()[7][4] = unit;
        decorator.performUnitActionAt(new Position(7,4));
        String msg = "BLUE performs fortify on chariot at (7,4).";
        assertEquals("There should be transcript of performing unit action" , msg, decorator.getTranscript(0));
    }

    @Test
    public void shouldTranscriptWhenPerformingUnitActionOnSettler() {
        //We will test this method with ThetaCivFactory, getting max. amount of unit actions.
        game = new GameImpl(new ThetaCivFactory());
        decorator = new TranscriptionGameDecorator(game);
        UnitImpl unit = new UnitImpl(GameConstants.SETTLER, Player.RED);
        game.getUnitMap()[7][4] = unit;
        decorator.performUnitActionAt(new Position(7,4));
        String msg = "RED performs build city on settler at (7,4).";
        assertEquals("There should be transcript of performing unit action" , msg, decorator.getTranscript(0));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void shouldNotTranscriptWhenPerformingUnitActionOnLegion() {
        //We will test this method with ThetaCivFactory, getting max. amount of unit actions.
        game = new GameImpl(new ThetaCivFactory());
        decorator = new TranscriptionGameDecorator(game);
        UnitImpl unit = new UnitImpl(GameConstants.LEGION, Player.RED);
        game.getUnitMap()[7][4] = unit;
        decorator.performUnitActionAt(new Position(7, 4));
        String msg = "RED performs build city on settler at (7,4).";
        //The next statement throws an exception, because there is no transcript to get.
        decorator.getTranscript(0);
    }
}
