package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

    private Player owner;
    private String type;
    private int moveCount;
    private boolean allowMovement;
    private int defenseStrength;
    private int attackStrength;
    private int cost;

    public UnitImpl(String type, Player owner){
        this.type = type;
        this.owner = owner;
        this.allowMovement = true;
        this.moveCount = 1;

        if (type.equals(GameConstants.ARCHER)) {
            defenseStrength = 3;
            cost = 10;
            attackStrength = 2;
        }

        if (type.equals(GameConstants.LEGION)) {
            defenseStrength = 2;
            cost = 15;
            attackStrength = 4;
        }

        if (type.equals(GameConstants.SETTLER)) {
            defenseStrength = 3;
            cost = 30;
            attackStrength = 0;
        }

        if (type.equals(GameConstants.CHARIOT)) {
            defenseStrength = 1;
            cost = 20;
            attackStrength = 3;
        }
    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        return defenseStrength;
    }

    @Override
    public int getAttackingStrength() {
        return attackStrength;
    }

    public boolean getAllowMovement() {
        return allowMovement;
    }

    public void setAllowedMovement( boolean allowedMovement ) {
        allowMovement = allowedMovement;
    }

    public void setDefenseStrength(int defenseStrength) {
        this.defenseStrength = defenseStrength;
    }
    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public int getCost() {
        return this.cost;
    }
}
