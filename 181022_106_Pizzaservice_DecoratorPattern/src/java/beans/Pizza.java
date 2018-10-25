package beans;

/**
 *
 * @author Manuel
 */
public abstract class Pizza {
    protected String name;
    protected double price;
    protected String pathToPicture;
    
    public abstract double getPrice();
    
    public String getPizzaName() {
        return name;
    }

    public String getPathToPicture() {
        return pathToPicture;
    }
}
