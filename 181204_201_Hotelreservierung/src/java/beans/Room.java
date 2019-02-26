package beans;

public class Room {
    private String name;
    private int roomSize;
    private double price;

    public Room(String name, int roomSize, double price) {
        this.name = name;
        this.roomSize = roomSize;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public double getPrice() {
        return price;
    }
}
