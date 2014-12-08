package hotciv.standard.Beta;

import hotciv.standard.Interfaces.AgingStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by Henrik on 12-11-14.
 */
public class BetaAgingStrategy implements AgingStrategy {
    @Override
    public void ageGame(GameImpl game) {

        int gameCurrentAge = game.getAge();

        if(gameCurrentAge < -100){
            game.setAge(gameCurrentAge + 100);
            return;
        }

        if(gameCurrentAge >= -100 && gameCurrentAge < 50 ){
            game.setAge(gameCurrentAge+1);
            return;
        }

        if(gameCurrentAge >= 50 && gameCurrentAge < 1750){
            game.setAge(gameCurrentAge+50);
            return;
        }

        if(gameCurrentAge >= 1750 && gameCurrentAge < 1900){
            game.setAge(gameCurrentAge+25);
            return;
        }

        if(gameCurrentAge >= 1900 && gameCurrentAge < 1970 ){
            game.setAge(gameCurrentAge+5);
            return;
        }

        if(gameCurrentAge >= 1970){
            game.setAge(gameCurrentAge+1);
            return;
        }
    }
}
