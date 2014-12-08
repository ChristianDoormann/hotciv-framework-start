package hotciv.standard.Delta;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.standard.Interfaces.StartingMapStrategy;

/**
 * Created by Messi on 13-11-2014.
 */
public class DeltaStartingMapStrategy implements StartingMapStrategy {

    private String[] layout;

    public DeltaStartingMapStrategy(){
        setDefaultMap();
    }

    @Override
    public void setWorld(GameImpl game) {
        String line;
        //Iterate through the tileMap, looking at each Char in each row.
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            line = layout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char tileChar = line.charAt(c);
                String type = "error";
                //Checking what TileType we are looking at corresponding to the StringArray map above.
                if (tileChar == '.') {
                    type = GameConstants.OCEANS;
                }
                if (tileChar == 'o') {
                    type = GameConstants.PLAINS;
                }
                if (tileChar == 'M') {
                    type = GameConstants.MOUNTAINS;
                }
                if (tileChar == 'f') {
                    type = GameConstants.FOREST;
                }
                if (tileChar == 'h') {
                    type = GameConstants.HILLS;
                }
                Position p = new Position(r, c); //Interpret the row and char as a position
                game.getTileMap()[p.getRow()][p.getColumn()] = new TileImpl(type); //Insert the Tile in the TileMap
            }
        }
        //Insert Red and Blue city
        game.getCityMap()[8][12] = new CityImpl(Player.RED);
        game.getCityMap()[4][5] = new CityImpl(Player.BLUE);
        //Insert starting units
        game.getUnitMap()[4][4] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        game.getUnitMap()[3][8] = new UnitImpl(GameConstants.ARCHER, Player.RED);
    }

    private void setDefaultMap(){
        //Insert Tiles in the startingMap
        String[] defaultLayout = //defining the tileMap as a StringArray
                new String[]{
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };

        this.layout = defaultLayout;
    }
}
