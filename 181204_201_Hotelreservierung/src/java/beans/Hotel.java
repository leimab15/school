package beans;

import java.util.ArrayList;

public class Hotel {
    private String name;
    private String pathToPicture;
    private ArrayList<Room> rooms = new ArrayList();

    public Hotel(String name, String pathToPicture) {
        this.name = name;
        this.pathToPicture = pathToPicture;
    }

    public String getName() {
        return name;
    }

    public String getPathToPicture() {
        return pathToPicture;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
