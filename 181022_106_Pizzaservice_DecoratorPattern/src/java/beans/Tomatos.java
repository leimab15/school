package beans;

/**
 *
 * @author Manuel
 */
public class Tomatos extends PizzaZutaten{
    
    public Tomatos(Pizza pizza) {
        this.zuzatenPreis = 2.3;
        this.zutatenName = "Tomato";
        this.pizza = pizza;
        this.name = "Tomato " + pizza.getPizzaName();
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
