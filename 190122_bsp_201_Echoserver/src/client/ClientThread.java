package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
public class ClientThread implements Runnable{

    public BufferedReader br;

    public ClientThread(BufferedReader br) {
        this.br = br;
    }
    
    
    
    @Override
    public void run() {
        String line = "";
        do {            
            try {
                line = br.readLine();
                System.out.println(line);
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        } while (!line.equals("EXIT"));
        System.out.println("Client thread finished!");
    }
    
}
        
