package leimab15;

/**
 *
 * @author Manuel
 */
public class Start {
    public static void main(String[] args) {
        Drinks drink = new Espresso();
        drink = new Schokostreusel(drink);
        drink = new Milk(drink);
        
        System.out.println("Price: " + drink.cost());
        System.out.println("Description: " + drink.getDescription());
    }
}
