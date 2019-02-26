package mailserver;

import client.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import server.Mail;

/**
 *
 * @author Manuel
 */
public class MailServer {
    
    private static int PORT_NR = 9999;
    private ServerSocket server;
    private ArrayList<Client> clients = new ArrayList();

    public MailServer(int portNumber) {
        this.PORT_NR = portNumber;
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
              stopServer();
            }
        });
        //getUserInput(); //Make it Work, uff
    }
    
    public void startServer()
    {
        log("Start Server!");
        new ClientCommunication(this);
        //TODO server und client Thread
    }
    
    public void stopServer()
    {
        log("Stop Server!");
        clients.forEach((client) -> {
            try {
                disconnectClient(client);
            } catch (IOException ex) {
                System.out.println("ERROR @ disconnecting Clients! \n" + client.getName() + ":" + client.getEmail() + " was already disconnected!");
            }
        });
    }
    
    private HashMap<String, ObjectOutputStream> mailList = new HashMap<>();
    
    public void getUserInput()
    {
        String menu = 
                "---------------------\n" +
                "1.Start Server\n" + 
                "2.Add User\n" + 
                "3.Delete User\n" + 
                "4.Block User\n" + 
                "---------------------";
        Scanner sc = new Scanner(System.in);
        do {            
            System.out.println(menu);
            try {
                sc.nextInt();
            } catch (Exception e) {
                System.out.println("Bitte eine Zahl eingeben welche die Menuepunkte entspricht.");
            }
        } while (true);
    }
    
    public synchronized void communicate() throws IOException {
        System.out.println("Starting Server!");
        server = new ServerSocket(PORT_NR);
    }
     
    public void addClient() throws IOException {
        Socket socket = server.accept();
        clients.add(new Client(socket));
        System.out.println("Client connected: " + socket.toString());
    }
    
    public void disconnectClient(Client disconnectClient) throws IOException {
        disconnectClient.getSocket().close();
        clients.remove(disconnectClient);
    }
    
    public void reciveData(Client client) throws IOException {
        ObjectInputStream input = (ObjectInputStream) client.getSocket().getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = "";
        do {
            line = reader.readLine();
        } while (client.getSocket().isClosed());
    }
      
    public void log (String message) {
        System.out.println(LocalTime.now()+":"+message);
    }
    
    class ClientCommunication implements Runnable {

        MailServer server;
        public ClientCommunication(MailServer server) {
            this.server = server;
        }
        
        @Override
        public void run() {
            try {
                //wait for clients
                server.addClient();
                new ClientCommunication(MailServer.this);
                //sendAndRecive(clients.get(threadCounter - 1));
            } catch (IOException ex) {
                System.out.println("ERROR @ connecting Client \n"+ex);
            }
        }
    }
    
    class ServerThread extends Thread {
        private ServerSocket serverSocket;
        
        public ServerThread() throws IOException {
            serverSocket = new ServerSocket(PORT_NR);
            log("Server is running ...");
        }
        
        @Override
        public void run() {
            while(true) {
                try {
                    Socket socket = serverSocket.accept();
                    log("New Client Requested " + socket.getInetAddress());
                    Thread thread = new Thread(new ClientThread(socket));
                    thread.start();
                } catch (IOException ex) {
                    log(ex);
                }
            }
        }
    }
    
    class ClientThread implements Runnable {

        private Socket socket;
        
        public ClientThread(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream output  = new ObjectOutputStream(socket.getOutputStream());
                
                Object recived;
                recived = input.readObject();
                if(recived instanceof String) {
                    String registratinAddress = (String) recived;
                    mailList.put(registratinAddress, output);
                }
                
               /* Mail mail = new Mail("leimab15@htl-kaindorf.at" ,"leimab15@htl-kaindorf.at", "GAG2", "GAG");
                output.writeObject(mail);
                output.flush();*/
                
                while (true) {
                    Object received = input.readObject();
                    if(received instanceof Mail)
                    {
                        Mail recivedMail = (Mail) received;
                        log("Mailaddress recieced from " + socket.getInetAddress());
                        System.out.println(recivedMail.getMessage());
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                log(ex);
            }
        }
        
    }
    
    public static void main(String[] args) {
        MailServer s = new MailServer(9999);
        s.startServer();
    }
}
