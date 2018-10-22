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
public class Schlag extends ZutatenDrinks{

    private Drinks drink;
    
    public Schlag(Drinks drink)
    {
        this.drink = drink;
        this.description = "Schlag";
    }
     
    @Override
    public double cost() {
        return 0.2 + drink.cost();
    }
    
}
