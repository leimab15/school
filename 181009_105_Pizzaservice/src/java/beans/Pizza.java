package beans;

/**
 *
 * @author Manuel
 */
public class Pizza {
    private String name;
    private String pictureName;
    private double price;
    private int menge;

    public Pizza(String name, String pictureName, double price) {
        this.name = name;
        this.pictureName = pictureName;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPictureName() {
        return pictureName;
    }

    public double getPrice() {
        return price;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }
}
