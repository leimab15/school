package beans;

/**
 *
 * @author Manuel
 */
public class PizzaOhne extends Pizza{
    
    public PizzaOhne() {
        this.name = "PizzaOhne";
        this.pathToPicture = "pizzaOhne.jpg";
    }
    
    @Override
    public double getPrice() {
        return 8.5;
    }
}
