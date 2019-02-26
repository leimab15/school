package server;

import mailserver.MailServer;

/**
 *
 * @author Manuel
 */
public class StartServer {
    public static void main(String[] args) {
        MailServer ms = new MailServer(9999);
        ms.startServer();
    }
}
