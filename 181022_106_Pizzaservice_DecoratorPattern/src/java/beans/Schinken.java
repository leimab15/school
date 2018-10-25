package beans;

/**
 *
 * @author Manuel
 */
public class Schinken extends PizzaZutaten{
    
    public Schinken(Pizza pizza) {
        this.zuzatenPreis = 6.5;
        this.zutatenName = "Schinken";
        this.pizza = pizza;
        this.name = "Schinken " + pizza.getPizzaName();
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
