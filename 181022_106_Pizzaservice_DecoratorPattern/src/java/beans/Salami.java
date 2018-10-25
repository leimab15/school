package beans;

/**
 *
 * @author Manuel
 */
public class Salami extends PizzaZutaten{
    
    public Salami(Pizza pizza) {
        this.zuzatenPreis = 3.4;
        this.zutatenName = "Salami";
        this.pizza = pizza;
        this.name = "Salami " + pizza.getPizzaName();
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
