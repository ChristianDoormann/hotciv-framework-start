package hotciv.standard.Epsilon;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.*;
import hotciv.standard.Interfaces.AttackingStrategy;
import hotciv.standard.Interfaces.DiceStrategy;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Henrik on 17-11-14.
 */
public class EpsilonAttackingStrategy implements AttackingStrategy {

    private DiceStrategy diceStrategy;

    public EpsilonAttackingStrategy( DiceStrategy diceStrategy ){
        setDiceStrategy(diceStrategy);
    }

    public void setDiceStrategy( DiceStrategy diceStrategy ){
        this.diceStrategy = diceStrategy;
    }

    @Override
    public boolean attack(GameImpl game, Position from, Position to) {

        int attackStrength;
        int defenseStrength;

        attackStrength = getStrength( game, from, "ATTACK" );
        defenseStrength = getStrength(game, to, "DEFENSE");

        int diceAttack = diceStrategy.diceThrow();
        int diceDefense = diceStrategy.diceThrow();

        attackStrength = attackStrength * diceAttack;
        defenseStrength = defenseStrength * diceDefense;

        if( attackStrength > defenseStrength ){
            return true;
        } else {
            return false;
        }
    }

    public int getStrength( GameImpl game, Position p, String type ){
        int strength;

        UnitImpl unit = game.getUnitAt(p);

        if( type.equals("ATTACK") ) {
            strength = unit.getAttackingStrength();
        } else {
            strength = unit.getDefensiveStrength();
        }

        int tileFactor = getTerrainFactor(game, p);
        int support = getFriendlySupport(game, p, unit.getOwner());

        strength = (strength + support) * tileFactor;

        return strength;

    }

    private int getTerrainFactor( GameImpl game, Position p ){

        if(game.getCityAt(p) != null){
            return 3;
        }

        Tile tile = game.getTileAt(p);
        if(tile == null){
            return 1;
        }

        if(tile.getTypeString().equals(GameConstants.HILLS)){
            return 2;
        }

        if(tile.getTypeString().equals(GameConstants.FOREST)){
            return 2;
        }

        return 1;
    }

    private Iterator<Position> get8NeighborhoodIterator(Position center) {
        ArrayList<Position> list = new ArrayList<Position>();
        int row = center.getRow(); int col = center.getColumn();
        int r,c;
        for (int dr = -1; dr <= +1; dr++) {
            for (int dc = -1; dc <= +1; dc++) {
                r = row+dr; c = col+dc;
                // avoid positions outside the world
                if ( r >= 0 && r < GameConstants.WORLDSIZE &&
                        c >= 0 && c < GameConstants.WORLDSIZE ) {
                    // avoid center position
                    if ( r != row || c != col ){
                        list.add( new Position(r,c));
                    }
                }
            }
        }
        return list.iterator();
    }

    private int getFriendlySupport(GameImpl game, Position position,
                                         Player player) {
        Iterator<Position> neighborhood = get8NeighborhoodIterator(position);
        Position p;
        int support = 0;
        while ( neighborhood.hasNext() ) {
            p = neighborhood.next();
            if ( game.getUnitAt(p) != null &&
                    game.getUnitAt(p).getOwner() == player ) {
                support++;
            }
        }
        return support;
    }

}
