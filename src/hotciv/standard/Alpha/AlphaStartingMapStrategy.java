package hotciv.standard.Alpha;
import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.standard.Interfaces.StartingMapStrategy;

/**
 * Created by Messi on 13-11-2014.
 */
public class AlphaStartingMapStrategy implements StartingMapStrategy {
    @Override
    public void setWorld(GameImpl game) {

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j){;
                game.getTileMap()[i][j] = new TileImpl( GameConstants.PLAINS);
            }
        }
        game.getCityMap()[1][1] = new CityImpl(Player.RED);
        game.getCityMap()[4][1] = new CityImpl(Player.BLUE);

        game.getTileMap()[1][0] = new TileImpl( GameConstants.OCEANS);
        game.getTileMap()[0][1] = new TileImpl( GameConstants.HILLS);
        game.getTileMap()[2][2] = new TileImpl( GameConstants.MOUNTAINS);

        game.getUnitMap()[2][0] = new UnitImpl( GameConstants.ARCHER, Player.RED);
        game.getUnitMap()[3][2] = new UnitImpl( GameConstants.LEGION, Player.BLUE);
        game.getUnitMap()[4][3] = new UnitImpl( GameConstants.SETTLER, Player.RED);
    }
}
