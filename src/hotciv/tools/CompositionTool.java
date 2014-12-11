package hotciv.tools;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henrik on 11-12-14.
 */
public class CompositionTool extends NullTool {

    private List<NullTool> toolList = new ArrayList<NullTool>();

    public CompositionTool( DrawingEditor editor, Game game){
        toolList.add(new ActionTool(editor, game));
        toolList.add(new MoveTool(editor, game));
        toolList.add(new EndOfTurnTool(editor, game));
        toolList.add(new SetFocusTool(editor, game));
    }

    public void mouseDown( MouseEvent e , int x, int y ){
        for( NullTool nt : toolList ){
            nt.mouseDown(e, x, y);
        }
    }

    public void mouseUp( MouseEvent e, int x, int y ){
        for( NullTool nt : toolList ){
            nt.mouseUp(e, x, y);
        }
    }

}
