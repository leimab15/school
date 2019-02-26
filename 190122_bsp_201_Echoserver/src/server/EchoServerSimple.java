package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
public class EchoServerSimple {
    
    
    private static final int PORT_NR = 9999;
    
    public void communicate() throws IOException
    {
        ServerSocket server = new ServerSocket(PORT_NR);
        System.out.println("Server wating on port: " + PORT_NR);
        Socket socket = server.accept();
        System.out.println(socket.getRemoteSocketAddress().toString());
        
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        
        String line = "";
        do {            
            line = br.readLine();
            pw.println(line);
            pw.flush();
        } while (!line.equals("EXIT"));
        
        pw.close();
        br.close();
        socket.close();
        server.close();
        System.out.println("Server has finished!");
    }
    
    public static void main(String[] args) {
        try {
            EchoServerSimple server = new EchoServerSimple();
            server.communicate();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
}
