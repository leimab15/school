package server;

import java.net.Socket;

/**
 *
 * @author Manuel
 */
public class Client {
    public String name = "";
    public Socket socket;

    public Client(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
    }

    public Client(Socket socket) {
        this.socket = socket;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }
}
