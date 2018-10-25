package beans;

/**
 *
 * @author Manuel
 */
public class Cheese extends PizzaZutaten{

    
    public Cheese(Pizza pizza) {
        this.zuzatenPreis = 3.5;
        this.zutatenName = "Cheese";
        this.pizza = pizza;
        this.name = "Cheese " + pizza.getPizzaName();
    }

    @Override
    public double getPrice() {
        return zuzatenPreis + pizza.getPrice();
    }

    @Override
    public double getZutatenPreis() {
        return zuzatenPreis;
    }
    
    @Override
    public String getZutatenName() {
        return zutatenName;
    }
}
