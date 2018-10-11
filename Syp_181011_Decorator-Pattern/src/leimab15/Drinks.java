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
    private String description;
    private int cost;
    
    abstract double cost();

    public String getDescription() {
        return description;
    }
}
