package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Interfaces.StartingMapStrategy;
import thirdparty.ThirdPartyFractalGenerator;

/**
 * Created by Christian Møller on 04-12-2014.
 *
 * This is a StartingMapStrategy that set the world with a random terrain.
 * It functions as a Strategy, but also as an Adapter in the Adapter pattern, with ThirdPartyFractalGenerator as the adaptee.
 * Be aware, that the terrain and the placed units and cities is not synchronized, so you can experience having units and cities
 * spawned in oceans or mountains, witch is not allowed in the game. So this class is not error free.
 */
public class RandomStartingMapStrategy implements StartingMapStrategy {

    private ThirdPartyFractalGenerator terrainGenerator;

    public RandomStartingMapStrategy() {
        this.terrainGenerator = new ThirdPartyFractalGenerator();
    }

    @Override
    public void setWorld(GameImpl game) {
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char tileChar = terrainGenerator.getLandscapeAt(r, c);
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
        placeunitsAndCities(game);
    }

    public void placeunitsAndCities(GameImpl game) {
        //Insert Red and Blue city
        game.getCityMap()[8][12] = new CityImpl(Player.RED);
        game.getCityMap()[4][5] = new CityImpl(Player.BLUE);
        //Insert starting units
        game.getUnitMap()[4][4] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        game.getUnitMap()[3][8] = new UnitImpl(GameConstants.ARCHER, Player.RED);
    }
}
