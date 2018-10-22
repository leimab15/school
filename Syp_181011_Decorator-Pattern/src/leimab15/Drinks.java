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
public abstract class Drinks {
    
    protected String description;
    private int cost;
    protected Drinks drink = null;

    
    abstract double cost();

    public String getDescription() {
        return ((drink != null) ? drink.getDescription() : "") + description ;
    }
}
