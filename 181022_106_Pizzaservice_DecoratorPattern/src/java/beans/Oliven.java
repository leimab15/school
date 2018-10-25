package beans;

/**
 *
 * @author Manuel
 */
public class Oliven extends PizzaZutaten {

    public Oliven(Pizza pizza) {
        this.zuzatenPreis = 1.5;
        this.zutatenName = "Oliven";
        this.pizza = pizza;
        this.name = "Oliven " + pizza.getPizzaName();
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
