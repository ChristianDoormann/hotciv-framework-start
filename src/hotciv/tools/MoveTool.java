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
public class MoveTool extends CivNullTool {

    private Game game;
    private DrawingEditor editor;
    private Position from;

    public MoveTool( DrawingEditor editor, Game game ){
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown( MouseEvent e, int x, int y ){

        from = null;

        if( !super.isValidMapCoordinate(x, y) ){
            return;
        }

        Position pTemp = GfxConstants.getPositionFromXY(x, y);
        Unit unit = game.getUnitAt(pTemp);

        if( unit != null ){
            from = pTemp;
        }

    }

    public void mouseUp( MouseEvent e, int x, int y ){

        Position to;

        if( from != null ) {
            to = GfxConstants.getPositionFromXY(x, y);
            game.moveUnit(from, to);
            from = null;
        }

    }

}