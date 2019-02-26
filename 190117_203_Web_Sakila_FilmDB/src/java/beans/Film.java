package beans;

import java.util.ArrayList;

public class Film {
    private int filmID;
    private String title;
    private String description;
    private String category;
    private double price;
    private int length;
    private String rating;
    private ArrayList<Actor> actors;

    public Film(int filmID, String title, String description, String category, double price, int length, String rating, ArrayList<Actor> actors) {
        this.filmID = filmID;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.length = length;
        this.rating = rating;
        this.actors = actors;
    }

    public int getFilmID() {
        return filmID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getLength() {
        return length;
    }

    public String getRating() {
        return rating;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }
}
