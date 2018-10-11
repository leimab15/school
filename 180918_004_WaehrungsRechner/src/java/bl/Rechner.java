package bl;

/**
 *
 * @author Manuel
 */
public class Rechner {
    
    public static double calcFromEUR(double betrag,String toCurrency)
    {
        double variator = 0;
        switch(toCurrency)
        {
            case "USD": variator = 1.1679; break;
            case "YEN": variator = 130.77; break;
            default: return betrag;
        }
        return betrag * variator;
    }
    
    public static double calcFromYEN(double betrag,String toCurrency)
    {
        double variator = 0;
        switch(toCurrency)
        {
            case "USD": variator = 0.0089; break;
            case "EUR": variator = 0.0076; break;
            default: return betrag;
        }
        return betrag * variator;
    }
    
    public static double calcFromUSD(double betrag,String toCurrency)
    {
        double variator = 0;
        switch(toCurrency)
        {
            case "EUR": variator = 0.86; break;
            case "YEN": variator = 111.94; break;
            default: return betrag;
        }
        return betrag * variator;
    }
}
