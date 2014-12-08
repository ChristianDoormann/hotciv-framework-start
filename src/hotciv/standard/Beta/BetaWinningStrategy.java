package hotciv.standard.Beta;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.Interfaces.WinningStrategy;

/**
 * Created by Henrik on 12-11-14.
 */
public class BetaWinningStrategy implements WinningStrategy {

    @Override
    public Player getWinner(GameImpl game) {

        Player firstFoundOwner = null;
        CityImpl tmpCity;

        for( int i = 0; i < GameConstants.WORLDSIZE; i++){
            for( int j = 0; j < GameConstants.WORLDSIZE; j++ ){

                tmpCity = game.getCityAt(new Position(i,j));

                if(tmpCity == null){
                    continue;
                }

                if(firstFoundOwner == null){
                    firstFoundOwner = tmpCity.getOwner();
                }

                if(!tmpCity.getOwner().equals(firstFoundOwner)){
                    return null;
                }
            }
        }
        return firstFoundOwner;
    }
}
