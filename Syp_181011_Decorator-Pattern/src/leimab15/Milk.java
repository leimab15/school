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
public class Milk extends ZutatenDrinks{

    private Drinks drink;
    
    public Milk(Drinks drink)
    {
        this.drink = drink;
        this.description = "Milk";
    }
    
    @Override
    public double cost() {
       return 0.12 + drink.cost();
    }
    
}
