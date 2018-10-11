package leimab;

/**
 *
 * @author Manuel
 */
public class Anzeige implements Observer{

    private WeatherData wd;
    
    public Anzeige(WeatherData wd)
    {
        this.wd = wd;
    }
    
    @Override
    public void update() {
        System.out.println(this.toString() + ": Temp: " + wd.getTemp() + ", Luftfeuchte: " + wd.getHumidity() + ", Luftdurck; " +  wd.getPressure());
    }
}
