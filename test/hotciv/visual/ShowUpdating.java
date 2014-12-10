package hotciv.visual;

import hotciv.standard.Alpha.AlphaCivFactory;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Show how GUI changes can be induced by making
    updates in the underlying domain model.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowUpdating {
  
  public static void main(String[] args) {
    Game game = new StubGame3(new AlphaCivFactory());
    //Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Click anywhere to see Drawing updates",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.setTool( new UpdateTool(editor, game) );

    editor.showStatus("Click anywhere to state changes reflected on the GUI");
                      
    // Try to set the selection tool instead to see
    // completely free movement of figures, including the icon

    editor.setTool( new SelectionTool(editor, game) );
  }
}

class SelectionTool extends NullTool{

    private DrawingEditor editor;
    private Game game;

    public SelectionTool( DrawingEditor editor, Game game ){
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {

        game.setTileFocus(GfxConstants.getPositionFromXY(x,y));

    }

}

/** A tool that simply 'does something' new every time
 * the mouse is clicked anywhere */
class UpdateTool extends NullTool {

    private Game game;
    private DrawingEditor editor;
    private int count = 0;

    public UpdateTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        switch(count) {
    case 0: {
      editor.showStatus( "State change: Moving archer to (1,1)" );
      game.moveUnit( new Position(2,0), new Position(1,1) );
      break;
    }
    case 1: {
      editor.showStatus( "State change: Moving archer to (2,2)" );
      game.moveUnit( new Position(1,1), new Position(2,2) );
      break;
    }
    case 2: {
      editor.showStatus( "State change: End of Turn (over to blue)" );
      game.endOfTurn();
      break;
    }
    case 3: {
      editor.showStatus( "State change: End of Turn (over to red)" );
      game.endOfTurn();
      break;
    }
    case 4: {
      editor.showStatus( "State change: Inspect Unit at (4,3)" );
      game.setTileFocus(new Position(4,3));
      break;
    }
    case 5: {
        editor.showStatus( "State change: Perform Unit Action at (4,3)" );
        game.performUnitActionAt(new Position(4,3));
        break;
    }
    case 6: {
        editor.showStatus( "State change: Spawn units" );
        game.moveUnit( new Position(1,1), new Position(2,1) );
        game.endOfTurn();
        game.endOfTurn();
        break;
    }
    case 7:  {
        editor.showStatus( "State change: Move units into attack position" );
        game.moveUnit( new Position(2,1), new Position(3,0) );
        game.endOfTurn();
        break;
    }
    case 8: {
        editor.showStatus( "State change: Perform Unit Attack" );
        game.moveUnit( new Position(4,1), new Position(3,0) );
        break;
    }
    case 9: {
        editor.showStatus("State change: Inspect unit at (4,1)");
        game.setTileFocus(new Position(4,1));
        break;
    }
    case 10: {
        editor.showStatus("State change: Inspect unit at (0,0) = None");
        game.setTileFocus(new Position(0,0));
        break;
    }
      // ADD CASES FOR OTHER EVENTS THAT GAME MUST SEND...
    default: {
      editor.showStatus("No more changes in my list...");
    }
    }
    count ++;
  }
}

