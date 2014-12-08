package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * Created by ThomasMøller on 05-11-2014.
 */

public class CityImpl implements City {

    private int size = 1;
    private Player owner;
    private int productionStock;
    private String production;

    public CityImpl(Player owner) {
        this.owner = owner;
        production = ""; //initialize production to produce nothing as standard.
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getProduction() {
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    public int getProductionStock() {
        return productionStock;
    }

    public void addProduction( int productionAmount ){
        productionStock += productionAmount;
    }

    public void setProduction(String production) {
        this.production = production;
    }
    //returns true if the city has production enough, and executes the subtraction.
    //Returns false otherwise, and cancels the request.
    public boolean subtractProductionStock(int amount) {
        if (productionStock - amount < 0) {
            return false;
        }
        productionStock -= amount;
        return true;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }
}
