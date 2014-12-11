package hotciv.tools;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;

import java.awt.event.MouseEvent;

/**
 * Created by Henrik on 11-12-14.
 */
public class SetFocusTool extends CivNullTool {

    private DrawingEditor editor;
    private Game game;

    public SetFocusTool( DrawingEditor editor, Game game ){
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {

        if( !super.isValidMapCoordinate(x, y) ){
            return;
        }

        game.setTileFocus(GfxConstants.getPositionFromXY(x, y));

    }

}
