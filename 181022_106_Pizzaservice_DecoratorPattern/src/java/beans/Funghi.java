package beans;

/**
 *
 * @author Manuel
 */
public class Funghi extends Pizza {

    public Funghi() {
        this.name = "Funghi";
        this.pathToPicture = "funghi.jpg";
    }

    @Override
    public double getPrice() {
        return 15.0;
    }

}
