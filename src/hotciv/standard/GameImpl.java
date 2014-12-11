package hotciv.standard;


import hotciv.framework.*;
import hotciv.standard.Interfaces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Skeleton implementation of HotCiv.
 * <p/>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p/>
 * Please visit http://www.baerbak.com/ for further information.
 * <p/> h
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class GameImpl implements Game {

    private Player playerInTurn;
    private CityImpl[][] cityMap;
    private TileImpl[][] tileMap;
    private UnitImpl[][] unitMap;
    private int gameAge;
    private Player gameWinner;
    private WinningStrategy winningStrategy;
    private AgingStrategy agingStrategy;
    private UnitActionStrategy unitActionStrategy;
    private StartingMapStrategy startingMapStrategy;
    private int redSuccessfulAttacks;
    private int blueSuccessfulAttacks;
    private AttackingStrategy attackStrategy;
    private int round;
    private CivFactory civFactory;
    private AllowedUnitStrategy allowedUnitStrategy;
    private List<GameObserver> observers = new ArrayList<GameObserver>();

    public GameImpl(CivFactory civFactory) {
        this.civFactory = civFactory;

        agingStrategy = civFactory.createAgingStrategy();
        attackStrategy = civFactory.createAttackingStrategy();
        startingMapStrategy = civFactory.createStartingMapStrategy();
        unitActionStrategy = civFactory.createUnitActionStrategy();
        winningStrategy = civFactory.createWinningStrategy();
        allowedUnitStrategy = civFactory.createAllowedUnitStrategy();

        cityMap = new CityImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        tileMap = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        unitMap = new UnitImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

        redSuccessfulAttacks = 0;
        blueSuccessfulAttacks = 0;

        round = 1;
        playerInTurn = Player.RED; //Starting player is red
        setAge(-4000 ); //Game start at age 4000BC

        startingMapStrategy.setWorld(this);
    }

    public Tile getTileAt(Position p) {
        return tileMap[p.getRow()][p.getColumn()];
    }

    public UnitImpl getUnitAt(Position p) {
        return unitMap[p.getRow()][p.getColumn()];
    }

    public CityImpl getCityAt(Position p) {
        return cityMap[p.getRow()][p.getColumn()];
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        return winningStrategy.getWinner( this );
    }

    public int getAge() {
        return gameAge;
    }

    public void setAge(int age){
        this.gameAge = age;
    }

    public boolean moveUnit(Position from, Position to) {
        //Saves the unit we are trying to move
        UnitImpl fromUnit = unitMap[from.getRow()][from.getColumn()];
        //Checks if the unit still has movement left.
        if( !fromUnit.getAllowMovement() ){
            return false;
        }

        if (fromUnit.getMoveCount() <= 0) {
            return false;
        }

        //Checks for unit not moving into Ocean or Mountain.
        String toTileType = tileMap[to.getRow()][to.getColumn()].getTypeString();
        if (toTileType.equals(GameConstants.MOUNTAINS) || toTileType.equals(GameConstants.OCEANS)){
            return false;
        }

        //Checks that the player that tries to move this unit, is in fact the owner.
        if( playerInTurn != fromUnit.getOwner() ){
            return false;
        }

        //Checks that a unit does not move to the position it is currently standing.
        if( from.equals(to) ){
            return false;
        }

        //No movement larger than 1 in columns
        if( Math.abs(to.getColumn()-from.getColumn()) > 1 ){
            return false;
        }

        //No movement larger than 1 in rows
        if( Math.abs(to.getRow()-from.getRow()) > 1 ){
            return false;
        }



        UnitImpl toUnit = unitMap[to.getRow()][to.getColumn()];
        if (toUnit != null) {

            //Checks that a unit cannot move into (or stack) with another friendly unit.
            if (toUnit.getOwner().equals(playerInTurn)) {
                return false;
            }

            //Since the tile we move to is not empty, and the player in turn is not the owner - attack is engaged

            if( fromUnit.getAttackingStrength() < 1 ){
                return false;
            }

            Boolean successfulAttack = attackStrategy.attack(this, from, to);

            if (successfulAttack){
                //You win, so you get to move and is increased in successfulAttacks
                if (playerInTurn.equals(Player.RED)) {
                    redSuccessfulAttacks += 1;
                } else {
                    blueSuccessfulAttacks += 1;
                }
            } else {
                //You loose, so you get deleted from the game
                unitMap[from.getRow()][from.getColumn()] = null;
                notifyObserversWorldChanged(new Position(from.getRow() , from.getColumn()));
                return false;
            }
        }

        //blocks the moving unit from moving more this round.
        fromUnit.setMoveCount(0);

        //All the checks are done, and the move is accepted
        unitMap[from.getRow()][from.getColumn()] = null;
        notifyObserversWorldChanged(from);

        unitMap[to.getRow()][to.getColumn()] = fromUnit;
        notifyObserversWorldChanged(to);



        return true;
    }


    public void endOfTurn() {
        if (playerInTurn == Player.RED) {
            playerInTurn = Player.BLUE;
        } else {
            //End of round
            //game-age is increased
            agingStrategy.ageGame( this );

            produceUnits(); //Add production and Produces units in all cities.
            //Resets move-distance for all units.
            for (int i = 0; i < GameConstants.WORLDSIZE; ++i) {
                for (int j = 0; j < GameConstants.WORLDSIZE; ++j){
                    UnitImpl unit = getUnitAt(new Position(i,j));
                    if( unit != null){
                        unit.setMoveCount(1);
                    }
                }
            }
            round = round + 1;
            playerInTurn = Player.RED;
        }

        notifyObserversTurnEnds(playerInTurn, gameAge);

        getWinner();
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        if( allowedUnitStrategy.isValidUnitType( unitType ) ){
            getCityAt(p).setProduction(unitType);
            notifyObserversWorldChanged(p);
        }
    }

    public void performUnitActionAt(Position p) {
        unitActionStrategy.performAction(p,this);
        notifyObserversWorldChanged(p);
    }

    public void produceUnits() {
        CityImpl city;
        //Iterate over all the cities in the map
        for (int i = 0; i < GameConstants.WORLDSIZE; ++i) {
            for (int j = 0; j < GameConstants.WORLDSIZE; ++j) {

                city = cityMap[i][j];
                if (city == null) {
                    continue;
                }

                //Add the production for the city since the round has ended
                city.addProduction(6);

                Player owner = city.getOwner();
                int productionStock = city.getProductionStock();
                String unitType = city.getProduction();

                UnitImpl tempUnit = new UnitImpl( unitType , owner );
                int cost = tempUnit.getCost();

                if( productionStock >= cost){
                    city.subtractProductionStock(cost);
                    spawnUnit(new Position(i, j), tempUnit);
                } else {
                    //Clean up
                    tempUnit = null;
                }
            }
        }
    }

    private void spawnUnit(Position cityPosition, UnitImpl unit) {

        int row = cityPosition.getRow();
        int col = cityPosition.getColumn();
        Position unitSpawned = new Position(row , col);
        //If there is no unit in the city, place the unit here
        if (unitMap[row][col] == null) {
            unitMap[row][col] = unit;
        }
        //If there is no unit, ocean or mountain right ABOVE the city, place the unit here.
        else if (unitMap[row-1][col] == null && tileAcceptingUnit(new Position((row-1) , col))) {
            unitMap[row-1][col] = unit;
            unitSpawned = new Position(row-1,col);
        }
        //If there is no unit, ocean or mountain in the UPPER-RIGHT corner of the city, place the unit here.
        else if (unitMap[row-1][col+1] == null && tileAcceptingUnit(new Position((row-1) , col+1))) {
            unitMap[row-1][col+1] = unit;
            unitSpawned = new Position(row-1 , col+1);
        }
        //If there is no unit, ocean or mountain directly to the RIGHT of the city, place the unit here.
        else if (unitMap[row][col+1] == null && tileAcceptingUnit(new Position((row) , col+1))) {
            unitMap[row][col+1] = unit;
            unitSpawned = new Position(row , col+1);
        }
        //If there is no unit, ocean or mountain in the DOWN-RIGHT corner of the city, place the unit here.
        else if (unitMap[row+1][col+1] == null && tileAcceptingUnit(new Position((row+1) , col+1))) {
            unitMap[row+1][col+1] = unit;
            unitSpawned = new Position(row+1 , col+1);
        }
        //If there is no unit, ocean or mountain directly BELOW the city, place the unit here.
        else if (unitMap[row+1][col] == null && tileAcceptingUnit(new Position((row+1) , col))) {
            unitMap[row+1][col] = unit;
            unitSpawned = new Position(row+1, col);
        }
        //If there is no unit, ocean or mountain in the  DOWN-LEFT corner of the city, place the unit here.
        else if (unitMap[row+1][col-1] == null && tileAcceptingUnit(new Position((row+1) , col-1))) {
            unitMap[row+1][col-1] = unit;
            unitSpawned = new Position(row+1 , col-1);
        }
        //If there is no unit, ocean or mountain directly to the LEFT of the city, place the unit here.
        else if (unitMap[row][col-1] == null && tileAcceptingUnit(new Position((row) , col-1))) {
            unitMap[row][col-1] = unit;
            unitSpawned = new Position(row , col-1);
        }
        //If there is no unit, ocean or mountain in the UPPER-LEFT corner of the city, place the unit here.
        else if (unitMap[row-1][col-1] == null && tileAcceptingUnit(new Position((row-1) , col-1))) {
            unitMap[row-1][col-1] = unit;
            unitSpawned = new Position(row-1 , col-1);
        }
        notifyObserversWorldChanged(unitSpawned);
    }

    private boolean tileAcceptingUnit(Position p) {
        if (tileMap[p.getRow()][p.getColumn()].getTypeString().equals(GameConstants.MOUNTAINS)) {
            return false;
        }
        else if (tileMap[p.getRow()][p.getColumn()].getTypeString().equals(GameConstants.OCEANS)) {
            return false;
        }
        return true;
    }

    public TileImpl[][] getTileMap() {
        return this.tileMap;
    }

    public CityImpl[][] getCityMap() {
        return this.cityMap;
    }

    public UnitImpl[][] getUnitMap() {
        return this.unitMap;
    }

    public int getSuccessfulAttacks(Player player) {
        if( player.equals(Player.RED) ){
            return redSuccessfulAttacks;
        } else {
            return blueSuccessfulAttacks;
        }
    }

    public int getRound() {
        return round;
    }

    public void resetSuccessfulAttacks(){
        blueSuccessfulAttacks = 0;
        redSuccessfulAttacks = 0;
    }

    public void notifyObserversTurnEnds( Player player , int age){

        for( GameObserver go : observers ){
            go.turnEnds(player, age);
        }
    }

    public void notifyObserversWorldChanged( Position p ){

        for( GameObserver go : observers ){
            go.worldChangedAt(p);
        }
    }

    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void setTileFocus(Position position) {
        for( GameObserver go : observers ){
            go.tileFocusChangedAt(position);
        }
    }
}
