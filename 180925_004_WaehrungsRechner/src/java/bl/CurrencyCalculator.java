package bl;

/**
 *
 * @author Manuel
 */
public class CurrencyCalculator {
    public static double calcualte(String baseCurrency, String targetCurrency, double amount)
    {
        if(baseCurrency.equals("EUR"))
        {
            switch(targetCurrency)
            {
                case "EUR": return amount;
                case "USD": return amount * 1.17;
            }
        }
        if(baseCurrency.equals("USD"))
        {
            switch(targetCurrency)
            {
                case "EUR": return amount / 1.;
                case "USD": return amount;
            }
        }
        return 0.0;
    }
}
