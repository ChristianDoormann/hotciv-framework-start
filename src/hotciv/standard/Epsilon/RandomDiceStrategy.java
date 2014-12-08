package hotciv.standard.Epsilon;

import hotciv.standard.Interfaces.DiceStrategy;

import java.util.Random;

/**
 * Created by Henrik on 17-11-14.
 */
public class RandomDiceStrategy implements DiceStrategy {
    @Override
    public int diceThrow() {

        int min = 1;
        int max = 6;

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;

    }
}
