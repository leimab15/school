package beans;

/**
 *
 * @author Manuel
 */
public class Margarita extends Pizza{

    public Margarita() {
        this.name = "Margarita";
        this.pathToPicture = "margarita.jpeg";
    }
    
    @Override
    public double getPrice() {
        return 12.0;
    }
    
}
