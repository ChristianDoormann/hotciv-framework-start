package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.standard.Theta.ThetaCivFactory;

/**
 * Created by Christian Møller on 04-12-2014.
 */
public class RandomMapGeneratorDriver {

    public static void main(String[] args) {
        RandomStartingMapStrategy mapGenerator = new RandomStartingMapStrategy();

        GameImpl game = new GameImpl(new ThetaCivFactory());

        mapGenerator.setWorld(game);

        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            String row = "";
            for (int c = 0; c < GameConstants.WORLDSIZE; c++){
                row += game.getTileMap()[r][c].getTypeString() + ", ";
            }
            System.out.println(row);
        }
    }
}
