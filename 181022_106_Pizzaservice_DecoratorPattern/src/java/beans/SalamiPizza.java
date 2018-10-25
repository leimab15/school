package beans;

/**
 *
 * @author Manuel
 */
public class SalamiPizza extends Pizza{

    public SalamiPizza() {
        this.name = "SalamiPizza";
        this.pathToPicture = "salami.jpg";
    }
    
    @Override
    public double getPrice() {
        return 12.5;
    }
}
