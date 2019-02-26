package client;

import java.net.Socket;

/**
 *
 * @author Manuel
 */
public class Client {
 private String name = "";
    private String email;
    private Socket socket;

    public Client(String email, Socket socket) {
        this.email = email;
        this.socket = socket;
    }

    public Client(Socket socket) {
        this.socket = socket;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Socket getSocket() {
        return socket;
    }
}