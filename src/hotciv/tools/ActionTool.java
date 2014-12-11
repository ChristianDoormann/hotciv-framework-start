package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;

import java.awt.event.MouseEvent;

/**
 * Created by Henrik on 11-12-14.
 */
public class ActionTool extends CivNullTool {

    private DrawingEditor editor;
    private Game game;

    public ActionTool(DrawingEditor editor, Game game){
        this.game = game;
        this.editor = editor;
    }

    public void mouseDown(MouseEvent e, int x, int y){

        if( !e.isAltDown() ){
            return;
        }

        if( !super.isValidMapCoordinate(x, y) ){
            return;
        }

        Position p = GfxConstants.getPositionFromXY(x, y);

        Unit unit = game.getUnitAt(p);

        if(unit != null){
            game.performUnitActionAt(p);
        }

    }

}
