package beans;

/**
 *
 * @author Manuel
 */
public class Capricciosa extends Pizza{

    public Capricciosa() {
        this.name = "Capricciosa";
        this.pathToPicture = "capricciosa.jpg";
    }

    @Override
    public double getPrice() {
        return 13.5;
    }

}
