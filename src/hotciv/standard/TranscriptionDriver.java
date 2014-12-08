package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.Semi.SemiCivFactory;

/**
 * Created by Christian Møller on 03-12-2014.
 */
public class TranscriptionDriver {

    private static Game game;
    private static Game decorator;
    private static Game transcriptionDecorator;
    private static Boolean isDecoratorTurnedOn = false;

    public static void main(String args[]) {
        game = new GameImpl(new SemiCivFactory());
        decorator = new GameDecorator(game);
        transcriptionDecorator = new TranscriptionGameDecorator(game);
        game = decorator; //Initialize the game to not transcript anything.

        //Run some code that should not be transmitted.
        System.out.println("The transcripting decorator is turned off");
        System.out.println("now we make an endOfTurn, and changes the production at city to Archer");
        System.out.println("This will not be logged");
        game.endOfTurn();
        game.changeProductionInCityAt(new Position(4,5), GameConstants.ARCHER);
        System.out.println("Now the decorator is turned on, and we make an endTurn, and changes production in city at (8,12) to Legions!");
        System.out.println("This will be logged.");
        turnDecoratorOnOff(); //turns on the transcription function
        //Run some code that should be transmitted
        game.endOfTurn();
        game.changeProductionInCityAt(new Position(8,12), GameConstants.LEGION);

        System.out.println(((TranscriptionGameDecorator) game).getTranscript(0));
        System.out.println(((TranscriptionGameDecorator) game).getTranscript(1));
    }

    public static void turnDecoratorOnOff() {
        if (isDecoratorTurnedOn) {
            game = decorator;
        }
        else {
            game = transcriptionDecorator;
        }
    }
}
