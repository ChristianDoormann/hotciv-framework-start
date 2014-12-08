package hotciv.standard.Alpha;

import hotciv.standard.Interfaces.AgingStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by Henrik on 12-11-14.
 */

public class AlphaAgingStrategy implements AgingStrategy {

    @Override
    public void ageGame(GameImpl game) {
        game.setAge(game.getAge() + 100) ;
    }
}
