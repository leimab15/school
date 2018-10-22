package beans;

/**
 *
 * @author Manuel
 */
public class Lieferadresse {
    private String strasse;
    private String plz;

    public Lieferadresse(String strasse, String plz) {
        this.strasse = strasse;
        this.plz = plz;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getPlz() {
        return plz;
    }
}
