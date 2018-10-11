package leimab;

import java.util.ArrayList;

/**
 *
 * @author Manuel
 */
public class WeatherData implements Subject{

    private ArrayList<Observer> anzeigen = new ArrayList();
    private float temp;
    private float humidity;
    private float pressure;
    
    @Override
    public void registerObserver(Anzeige a) {
        anzeigen.add(a);
    }

    @Override
    public void unregisterObserver(Anzeige a) {
        anzeigen.remove(a);
    }

    @Override
    public void notifyObserver() {
        for (Observer anzeige : anzeigen) {
            anzeige.update();
        }
    }
    
    public void changeValues(float temp,float humidity, float pressure)
    {
       this.temp = temp;
       this.humidity = humidity;
       this.pressure = pressure;
    }

    public float getTemp() {
        return temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
