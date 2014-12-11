package hotciv.tools;

import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.standard.NullTool;

/**
 * Created by Henrik on 11-12-14.
 */
public class CivNullTool extends NullTool {

    public boolean isValidMapCoordinate( int x, int y ){
        if (y <= GfxConstants.MAP_OFFSET_Y) {
            return false;
        }

        if (x <= GfxConstants.MAP_OFFSET_X) {
            return false;
        }

        Position pTemp = GfxConstants.getPositionFromXY(x,y);

        if(pTemp.getColumn() > 15 || pTemp.getRow() > 15){
            return false;
        }

        return true;
    }

}
