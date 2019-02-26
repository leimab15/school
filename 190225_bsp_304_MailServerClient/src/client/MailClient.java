package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Mail;

/**
 *
 * @author Manuel
 */
public class MailClient {
    
    private String mailAddress = "leimab15@htl-kaindorf.at";
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private final int PORT_NR = 9999;
    
    private void connect() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), PORT_NR);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            output.writeObject(mailAddress);
            output.flush();
            
            
            Thread thread = new Thread(new ReciveMail());
            thread.start();
            Thread thread2 = new Thread(new SendMail());
            thread2.start();
            
        } catch (IOException ex) {
            Logger.getLogger(MailClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class SendMail implements Runnable {

        Scanner sc = new Scanner(System.in);
        
        @Override
        public void run() {
            while(true)
            {
                String userInput = sc.nextLine();
                switch(userInput)
                {
                    case "#send":
                        try {
                            sendMail();
                        } catch (IOException ex) {
                            System.out.println("Error @ sending Mail, pls try again!");
                        }
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
            }
        }
        
        private void sendMail() throws IOException
        {
            System.out.println("To: ");
            String receiver = sc.nextLine();
            System.out.println("Subject: ");
            String subject = sc.nextLine();
            System.out.println("Message: ");
            String message = sc.nextLine();
            Mail mail = new Mail(receiver, mailAddress, message, subject);
            output.writeObject(mail);
            output.flush();
            System.out.println("Mail send to: " + receiver);
        }
        
    }
    
    class ReciveMail implements Runnable
    {

        @Override
        public void run()
        {
            while(true)
            {
                try {
                    Object receive = input.readObject();
                    if(receive instanceof Mail)
                    {
                        Mail mail = (Mail) receive;
                        System.out.println("New Mail From :" + mail.getSender());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MailClient.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
    }
    
    public static void main(String[] args) {
        MailClient mc = new MailClient();
        mc.connect();
    }
}
