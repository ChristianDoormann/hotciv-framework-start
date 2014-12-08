package hotciv.standard;

import hotciv.standard.Interfaces.DiceStrategy;

/**
 * Created by Henrik on 17-11-14.
 */
public class StaticDiceStrategy implements DiceStrategy {
    private int diceValue;

    public StaticDiceStrategy(int diceValue) {
        this.diceValue = diceValue;
    }

    @Override
    public int diceThrow() {
        return diceValue;
    }
}
