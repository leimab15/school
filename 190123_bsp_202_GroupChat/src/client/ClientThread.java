package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
public class ClientThread implements Runnable{

public BufferedReader br;
private ArrayList<String> names = new ArrayList();
private boolean readNames = false;
private javax.swing.JTextArea room;

    public ClientThread(BufferedReader br,javax.swing.JTextArea room) {
        this.br = br;
        this.room = room;
    }
    
    @Override
    public void run() {
        String line = "";
        //TODO change to Switch??
        do {            
            try {
                line = br.readLine();
                if (line.equals("---SHOW CLIENTS---")) 
                {
                    readNames = true;
                    names.clear();
                }
                else
                {
                    if(readNames)
                    {
                        if(line.equals("---SHOWED CLIENTS---"))
                        {
                            readNames = false;
                            String allNames = "";
                            allNames = names.stream().map((name) -> name + "\n").reduce(allNames, String::concat);
                            room.setText(allNames);
                        }
                        else
                        {
                            names.add(line);
                        }
                    }
                    else
                    {
                       System.out.println(line); 
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!line.equals("EXIT"));
        System.out.println("Client thread finished!");
    }

    public ArrayList<String> getNames() {
        return names;
    }
}
