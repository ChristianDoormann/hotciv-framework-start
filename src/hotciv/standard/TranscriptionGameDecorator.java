package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;

/**
 * Created by Christian Møller on 03-12-2014.
 */
public class TranscriptionGameDecorator extends GameDecorator implements Game{

    private ArrayList<String> transcripts;

    public TranscriptionGameDecorator(Game game) {
        super(game);
        transcripts = new ArrayList<String>();
    }

    public String getTranscript(int index) {
        String transcript = transcripts.get(index);
        return transcript;
    }

    public boolean moveUnit(Position from, Position to) {
        String transcript = "";
        Unit unit = game.getUnitAt(from);
        String positions = "from (" + from.getRow() + "," + from.getColumn() + ") to (" + to.getRow() + "," + to.getColumn() + ").";
        transcript = unit.getOwner() + " moves " + unit.getTypeString() + " " + positions;
        transcripts.add(transcript);

        return super.moveUnit(from , to);
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        City city = game.getCityAt(p);
        String transcript = city.getOwner() + " changes production in city at (" + p.getRow() + "," + p.getColumn() + ") to " + unitType + ".";
        transcripts.add(transcript);
        super.changeProductionInCityAt(p , unitType);
    }

    public void endOfTurn() {
        String transcript = game.getPlayerInTurn() + " finished his turn";
        transcripts.add(transcript);
        super.endOfTurn();
    }

    public Player getWinner() {
        String transcript = "";
        Player winner = super.getWinner();
        if (winner != null) {
            transcript += winner + " wins the game.";
            transcripts.add(transcript);
        }
        return winner;
    }

    public void performUnitActionAt(Position p) {
        Unit unit = game.getUnitAt(p);
        String type = unit.getTypeString();
        Player owner = unit.getOwner();
        int unitDefense = unit.getDefensiveStrength();
        City city = game.getCityAt(p);

        String action = null;
        super.performUnitActionAt(p);

        if (city == null && game.getCityAt(p) != null) {
            action = "build city";
        }

        else if (unit.getDefensiveStrength() == 2 * unitDefense) {
            action = "fortify";
        }

        if (action != null) {
            String transcript = owner + " performs " + action + " on " + type + " at (" + p.getRow() + "," + p.getColumn() + ").";
            transcripts.add(transcript);
        }
    }
}
