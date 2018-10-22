/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leimab15;

/**
 *
 * @author Manuel
 */
public class Schokostreusel extends ZutatenDrinks{

    private Drinks drink;
    
    public Schokostreusel(Drinks drink)
    {
        this.drink = drink;
        this.description = "Schokostreusel";
    }
     
    @Override
    public double cost() {
        return 0.5 + drink.cost();
    }
    
}
