package beans;

/**
 *
 * @author Manuel
 */
public class Konto {

    private String kontoArt;
    private double kontoStand;
    private long kontoNr;
    private String vorname;
    private String nachname;
    private int plz;
    private String ort;

    public Konto(String kontoArt, double kontoStand, long kontoNr, String vorname, String nachname, int plz, String ort) {
        this.kontoArt = kontoArt;
        this.kontoStand = kontoStand;
        this.kontoNr = kontoNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.plz = plz;
        this.ort = ort;
    }

    public String getKontoArt() {
        return kontoArt;
    }

    public void setKontoArt(String kontoArt) {
        this.kontoArt = kontoArt;
    }

    public double getKontoStand() {
        return kontoStand;
    }

    public void setKontoStand(double kontoStand) {
        this.kontoStand = kontoStand;
    }

    public long getKontoNr() {
        return kontoNr;
    }

    public void setKontoNr(long kontoNr) {
        this.kontoNr = kontoNr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
