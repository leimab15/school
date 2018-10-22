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
public class Espresso extends Drinks{

    public Espresso() {
        this.description = "Espresso";
    }
    
    @Override
    public double cost() {
        return 2.9;
    }
    
}
