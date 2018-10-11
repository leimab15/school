package leimab;

/**
 *
 * @author Manuel
 */
public interface Subject {
    
    public abstract void registerObserver(Anzeige a);
    public abstract void unregisterObserver(Anzeige a);   
    public abstract void notifyObserver();
}
