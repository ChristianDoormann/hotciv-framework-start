package hotciv.visual;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.standard.Semi.SemiCivFactory;
import hotciv.tools.CompositionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

/**
 * Created by Henrik on 11-12-14.
 */
public class ShowSemi {

    public static void main(String[] args) {
        Game game = new GameImpl(new SemiCivFactory());

        DrawingEditor editor =
                new MiniDrawApplication( "Click and/or drag any item to see all game actions",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");

        // Replace the setting of the tool with your CompositionTool implementation.
        editor.setTool( new CompositionTool(editor, game) );
    }

}
