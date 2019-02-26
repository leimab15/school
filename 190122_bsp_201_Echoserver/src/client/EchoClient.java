package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
public class EchoClient {
    
    public void sendAndReciveData() throws UnknownHostException, IOException
    {
        //InetAddress addr = InetAddress.getByName("www.htl-kaindorf.at");
        InetAddress addr = InetAddress.getLocalHost();
        //InetAddress addr = InetAddress.getByAddress("10.151.98.176".getBytes());
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());
        Socket socket = new Socket(addr, 9999);
        //create printwrite to send over socket connection 
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);

        //create bufferedwriter to recive over socket connection        
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        ClientThread ct = new ClientThread(br);
        Thread thread = new Thread(ct);
        thread.start();
        
        Scanner sc = new Scanner(System.in);
        String line = "";
        do {            
            line = sc.nextLine();
            pw.println(line);
            pw.flush();
        } while (!line.equals("EXIT"));
        
        br.close();
        pw.close();
        socket.close();
        System.out.println("Client finished");
    }
    
    public static void main(String[] args) {
        try {
            EchoClient client = new EchoClient();
            client.sendAndReciveData();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
