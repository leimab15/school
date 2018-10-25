package beans;

import beans.Pizza;

/**
 *
 * @author Manuel
 */
public abstract class PizzaZutaten extends Pizza{
    
     protected Pizza pizza;
     protected double zuzatenPreis;
     protected String zutatenName;
    
     public abstract double getPrice();
     public abstract double getZutatenPreis();
     public abstract String getZutatenName();
}
