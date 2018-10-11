/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leimab;

/**
 *
 * @author Manuel
 */
public class Start {
    public static void main(String[] args) {
        WeatherData wd = new WeatherData();
        Anzeige a1 = new Anzeige(wd);
        Anzeige a2 = new Anzeige(wd);
        Anzeige a3 = new Anzeige(wd);
        //Testing:
        wd.registerObserver(a1);
        wd.registerObserver(a2);
        wd.changeValues(1,2,3);
        wd.notifyObserver();
        wd.unregisterObserver(a1);
        wd.registerObserver(a3);
        wd.changeValues(4, 5, 6);
        wd.notifyObserver();
        wd.unregisterObserver(a3);
        wd.unregisterObserver(a2);
    }
}
