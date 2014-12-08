package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Alpha.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Skeleton class for AlphaCiv test cases
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
 * <p/>
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
public class TestAlphaCiv {
    private GameImpl game;
    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaCivFactory());
    }

    @Test
    public void shouldBeRedThatHasFirstTurn() {
        assertEquals("Red should start the game", Player.RED, game.getPlayerInTurn());
    }

    @Test
    public void shouldBeBlueAfterRedEndTurn() {
        game.endOfTurn();
        assertEquals("It should be Blues turn, after red", Player.BLUE, game.getPlayerInTurn());
    }

    @Test
    public void citySizeIsAlways1() {
        assertEquals("City size should always be 1", 1, game.getCityAt(new Position(1,1)).getSize());
    }

    @Test
    public void shouldBeACityAtPosition1_1() {
        assertNotNull("There should be a city at position 1,1", game.getCityAt(new Position(1, 1)));
    }

    @Test
    public void shouldBeRedCityAtPosition1_1() {
        assertEquals("There should be a red city at position 1,1", Player.RED, game.getCityAt(new Position(1, 1)).getOwner());
    }

    @Test
    public void shouldBeOceanAtTile1_0() {
        assertEquals("There should be ocean at tile 1,0", GameConstants.OCEANS, game.getTileAt(new Position(1, 0)).getTypeString());
    }

    @Test
    public void shouldBeHillAt0_1(){
        assertEquals("There should be a hill at 0.1" , GameConstants.HILLS , game.getTileAt(new Position(0,1)).getTypeString());
    }

    @Test
    public void shouldBeMountainAt2_2(){
        assertEquals("There should be a mountain at 2.2" , GameConstants.MOUNTAINS , game.getTileAt(new Position(2,2)).getTypeString());
    }

    @Test
    public void shouldBeRedArcherAt2_0(){
        assertNotNull(game.getUnitAt(new Position(2,0)));
        assertEquals("There should be an archer at position 2,0", GameConstants.ARCHER, game.getUnitAt(new Position(2, 0)).getTypeString());
        assertEquals("Red should be the owner of the archer", Player.RED , game.getUnitAt(new Position(2, 0)).getOwner());
    }

    @Test
    public void shouldBeBlueLegionAt3_2(){
        assertNotNull(game.getUnitAt(new Position(3,2)));
        assertEquals("There should be a Legion at position 3,2", GameConstants.LEGION, game.getUnitAt(new Position(3, 2)).getTypeString());
        assertEquals("Blue should be the owner of the Legion", Player.BLUE, game.getUnitAt(new Position(3, 2)).getOwner());
    }

    @Test
    public void shouldBeRedSettlerAt4_3(){
        assertNotNull(game.getUnitAt(new Position(4,3)));
        assertEquals("There should be a Settler at position 4,3", GameConstants.SETTLER, game.getUnitAt(new Position(4, 3)).getTypeString());
        assertEquals("Blue should be the owner of the Settler", Player.RED , game.getUnitAt(new Position(4, 3)).getOwner());
    }

    @Test
    public void shouldBeBlueCityAt4_1(){
        assertEquals("There should be a blue city at position 4,1", Player.BLUE, game.getCityAt(new Position(4, 1)).getOwner());
    }

    @Test
    public void shouldBe4000BcWhenGameStarts(){
        assertEquals("Year should be 4000BC when game starts" , -4000 , game.getAge() );
    }

    @Test
    public void shouldAge100YearsPerRound(){
        game.endOfTurn();
        game.endOfTurn(); //End of round
        assertEquals("Game should age 100 years per round", -3900, game.getAge());
    }

    @Test
    public void shouldYieldRedAsWinnerAt3000BC(){
        endRounds(10);
        //10 round after initial start.
        assertEquals("Red should be the winner at year 3000BC" , Player.RED , game.getWinner());
    }

    @Test
    public void shouldBeNoWinnerAt3500BC(){
        endRounds(5);
        //5 rounds after intiail start.
        assertEquals("There should be no winner at year 3500BC" , null , game.getWinner());
    }

    @Test
    public void shouldIncreaseCityProductionBy6PerRound(){
        endRounds(1);
        CityImpl RedCity = game.getCityAt( new Position( 1,1 ) );
        CityImpl BlueCity = game.getCityAt( new Position( 4,1 ) );
        assertEquals("Red city should have increased production by 6" , 6 , RedCity.getProductionStock());
        assertEquals("Blue city should have increased production by 6", 6, BlueCity.getProductionStock());
    }

    @Test
    public void shouldHaveAttack2ifArcher(){
        assertEquals("Archer should have 2 attack" , 2 , game.getUnitAt(new Position(2,0)).getAttackingStrength() );
    }

    @Test
    public void shouldHaveAttack4ifLegion(){
        assertEquals("Legion should have 4 attack" , 4 , game.getUnitAt(new Position(3,2)).getAttackingStrength() );
    }

    @Test
    public void shouldHaveAttack0IfSettler() {
        assertEquals("Settler should have 0 attack" ,0 ,game.getUnitAt(new Position(4,3)).getAttackingStrength() );
    }

    @Test
    public void shouldHaveDefense3IfArcher() {
        assertEquals("Archer should have 3 in defense strength",3 ,game.getUnitAt(new Position(2,0)).getDefensiveStrength());
    }

    @Test
    public void shouldHaveDefense2IfLegion() {
        assertEquals("Legion should have 2 in defense strength" ,2 ,game.getUnitAt(new Position(3,2)).getDefensiveStrength());
    }

    @Test
    public void shouldHaveDefense3IfSettler() {
        assertEquals("Settler should have 3 in defense Strength" ,3 ,game.getUnitAt(new Position(4,3)).getDefensiveStrength() );
    }



    @Test
    public void shouldBeProducingArcherAtRedCity() {
        game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
        assertEquals("Red city should produce archers", GameConstants.ARCHER, game.getCityAt(new Position(1, 1)).getProduction());
    }
    @Test
    public void shouldBeProducingLegionAtRedCity() {
        game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION);
        assertEquals("Red city should produce Legions", GameConstants.LEGION, game.getCityAt(new Position(1, 1)).getProduction());
    }

    @Test
    public void shouldMoveRedArcherFrom2_0To2_1() {
        game.moveUnit(new Position(2,0) , new Position(2,1)); //moves red archer from (2,0) to (2,1)
        assertNotNull(game.getUnitAt(new Position(2,1))); //check if there is a unit at (2,1) or not.
    }

    @Test
    public void shouldBePlainTileAtManyPlaces(){
        assertEquals("Should be plain tile at (13,15)", GameConstants.PLAINS, game.getTileAt(new Position(13, 15)).getTypeString());

        assertEquals("Should be plain tile at (15,15)", GameConstants.PLAINS, game.getTileAt(new Position(15,15)).getTypeString());

        assertEquals("Should be plain tile at (15,12)", GameConstants.PLAINS, game.getTileAt(new Position(15, 12)).getTypeString());
    }

    @Test
    public void shouldNotBeAbleToMoveUnitIntoMountainTile(){
        game.moveUnit(new Position(2, 0), new Position(2, 1)); //moves unit from (2,0) to (2,1)
        assertEquals(false, game.moveUnit(new Position(2, 1), new Position(2, 2)));
    }

    @Test
    public void shouldNotBeAbleToMoveUnitIntoOceanTile(){
        assertEquals(false, game.moveUnit(new Position(2, 0), new Position(1, 0)));
    }

    @Test
    public void shouldKillUnitWeMoveOnTo(){
        //We move the unit in two steps
        game.moveUnit(new Position(2, 0), new Position(3, 1)); //moves red archer from (2,0) to (3,1)
        endRounds(1);
        game.moveUnit(new Position(3,1), new Position(3,2)); //moves red archer from (3,1) to (3,2), where a blue unit is.
        assertEquals("Should kill the unit we move on to", GameConstants.ARCHER, game.getUnitAt(new Position(3, 2)).getTypeString());
    }

    @Test
    public void shouldNotMoveRedUnitIfBluePlayer(){
        game.endOfTurn(); //now it is blues turn. We will now move a red archer, and see it fail.
        assertEquals("Blue should not be able to move red unit", false, game.moveUnit(new Position(2, 0), new Position(2, 1)));
    }

    @Test
    public void shouldNotMoveBlueUnitIfRedPlayer(){
        //now it is reds turn
        assertEquals("Red should not be able to move blue unit", false, game.moveUnit(new Position(3,2) , new Position(4,2)));
    }

    @Test
    public void shouldNotMoveUnitToStartPosition(){
        assertEquals("Unit should not be able to move to where it comes from" , false , game.moveUnit(new Position(2,0) , new Position(2,0) ) );
    }

    @Test
    public void shouldNotBeAbleToMoveMoreThanOneTile(){
        //moves red archer two tiles, and should of course fail.
        assertEquals("Unit should not be able to move more than one tile" , false , game.moveUnit(new Position(2,0) , new Position(4,0) ));
    }

    @Test
    public void shouldOnlyHaveOneMovementForArcherPerTurn(){
        game.moveUnit(new Position(2,0) , new Position(3,0) );
        assertEquals("Archer should only move one time per turn" , false , game.moveUnit(new Position(3,0) , new Position(4,0) ));
    }

    @Test
    public void shouldOnlyHaveOneMovementForLegionPerTurn(){
        //The Legion we try to move is owned by Blue, so we make one turn to get to Blue players turn
        game.endOfTurn();
        game.moveUnit(new Position(3,2) , new Position(3,3) );
        assertEquals("Legion should only move one time per turn" , false , game.moveUnit(new Position(3,3) , new Position(3,4) ));
    }

    @Test
    public void shouldOnlyHaveOneMovementForSettlerPerTurn(){
        game.moveUnit(new Position(4,3) , new Position(5,4) );
        assertEquals("Settler should only move one time per turn" , false , game.moveUnit(new Position(5,4) , new Position(5,5) ));
    }

    @Test
    public void shouldBeAbleToMoveDiagonally(){
        assertTrue("Should be able to move diagonally", game.moveUnit(new Position(2, 0), new Position(3, 1)));
    }

    @Test
    public void shouldNotBeAbleToMoveUnitIntoUnitOwnedBySamePlayer(){
        //moves the red archer 1 tile, pr. round
        game.moveUnit(new Position(2, 0), new Position(3, 0));
        endRounds(1);
        game.moveUnit(new Position(3, 0), new Position(3, 1));
        endRounds(1);
        game.moveUnit(new Position(3, 1), new Position(4, 2));
        endRounds(1);
        //tries to move the red archer into the red settler at (4,3), and it should fail.
        assertFalse("Should not be able to move onto your own unit", game.moveUnit(new Position(4, 2), new Position(4, 3)));
    }

    @Test
    public void shouldSpawnArcherAtRedCityAfterTwoRoundsAndStillHave2ProductionLeft() {
        game.changeProductionInCityAt(new Position(1,1),GameConstants.ARCHER); //Sets red city to produce archers.
        endRounds(2); //production = 12 - 10 for have produced an archer.
        //now there should be an archer at the city's location
        assertEquals("There should be an archer at the same position as the city", GameConstants.ARCHER, game.getUnitAt(new Position(1, 1)).getTypeString());
        assertEquals("productionStock in red city should be 12-10 " , 12-10 ,game.getCityAt(new Position(1,1)).getProductionStock());
    }

    @Test
    public void shouldSpawnTwoRedLegionsAfter5RoundsAndHave0ProductionLeft() {
        game.changeProductionInCityAt(new Position(1,1),GameConstants.LEGION); //Sets red city to produce legions.
        endRounds(3); //production = 18 - 15 for have produced a legion
        assertEquals("The red city at 1,1 should have 3 in productionStock" ,18-15 ,game.getCityAt(new Position(1,1)).getProductionStock() );
        assertEquals("There should be a legion at the same position as the city", GameConstants.LEGION, game.getUnitAt(new Position(1, 1)).getTypeString());
        endRounds(2); //production = 15 - 15 for have produced a legion.
        assertEquals("There should be a legion directly above the city" , GameConstants.LEGION , game.getUnitAt(new Position(0,1)).getTypeString() );
        assertEquals("productionStock in red city should be 15-15 " , 15-15 , game.getCityAt(new Position(1,1)).getProductionStock());
    }

    @Test
    public void shouldSpawn5BlueArchersAfter9RoundsAndHave4ProductionsLeft() {
        game.changeProductionInCityAt(new Position(4,1), GameConstants.ARCHER); //Sets blue city to produce archers.
        endRounds(2); //produced 1 archer, and 12-10 productionStock left
        assertEquals("Blue city should have an archer at their city" ,GameConstants.ARCHER ,game.getUnitAt(new Position(4,1)).getTypeString() );
        endRounds(2);
        endRounds(2); //produced 3 archers totally, and have 36-30 productionStock left
        assertEquals("Blue city should have an archer directly above their city", GameConstants.ARCHER ,game.getUnitAt(new Position(3,1)).getTypeString());
        assertEquals("Blue city should have an archer to the left of their city", GameConstants.ARCHER ,game.getUnitAt(new Position(4,2)).getTypeString());
        endRounds(2);
        endRounds(1); //Production in blue city should be 54-50 for spawning 5 archers.
        assertEquals("Blue city should have an archer in the DOWN-RIGHT corner of their city", GameConstants.ARCHER ,game.getUnitAt(new Position(5,2)).getTypeString());
        assertEquals("Blue city should have an archer directly BELOW their city ", GameConstants.ARCHER ,game.getUnitAt(new Position(5,1)).getTypeString());
        assertEquals("Blue city should have 54-50 productionStock after producing 5 archers" ,54-50 ,game.getCityAt(new Position(4,1)).getProductionStock() );
    }

    @Test
    public void shouldNotSpawnUnitInMountainOrOceanOrInAnotherUnit() {
        game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER); //Sets red city to produce archers.
        endRounds(10); //Red has won now, but we ignore it in this test
        /*Reds' productionStock is now 60-60 for producing 6 archers.
        *the city is at (1,1) so after producing 6 archers, we have an archer at (1,1), (0,1), (0,2), (1,2).
        *but the next tile is (2,2), but there is a mountain. */
        assertNull("There should not be an archer at (2,2)", game.getUnitAt(new Position(2,2)));
        //The fifth archer is therefore placed at (2,1). There is a unit at (2,0) where the sixth archer should be,
        //so at (2,0) there should not spawn a new archer, but instead at (1,0). Though there is an ocean tile
        //at (1,0), so there should not be an archer here
        assertNull("There should not be an archer at (1,0)", game.getUnitAt(new Position(1,0)));
        //now we have that the last archer is placed at (0,0).
        assertEquals("There should be an archer at (0,0)", GameConstants.ARCHER, game.getUnitAt(new Position(0,0)).getTypeString());
    }

    @Test
    public void shouldNotLetCityHaveNegativeProductionStock() {
        //Reds city has currently 0 productionStock
        assertFalse("Subtracting 1 from 0 in a citys productionStock should fail" ,game.getCityAt(new Position(1,1)).subtractProductionStock(1));
    }

    @Test
    public void shouldNotBeAbleToChangeProductionToUnknown(){
        game.changeProductionInCityAt( new Position(1,1) , "HEST");
        assertFalse("Should not be able to change productiontype in city to unknown type" ,game.getCityAt(new Position(1,1)).getProduction().equals("HEST"));
    }

    public void endRounds( int rounds ) {
        for(int i = 0; i < rounds; i++ ) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }
}