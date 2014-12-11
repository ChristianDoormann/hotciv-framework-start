package hotciv.tools;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * Created by Henrik on 11-12-14.
 */
public class EndOfTurnTool extends NullTool {

    private DrawingEditor editor;
    private Game game;

    public EndOfTurnTool(DrawingEditor editor, Game game){
        this.game = game;
        this.editor = editor;
    }

    public void mouseUp(MouseEvent e, int x, int y){

        if(x >= 560 && x <= 585 && y >= 70 && y <= 100 ){
            game.endOfTurn();
        }
    }
}
