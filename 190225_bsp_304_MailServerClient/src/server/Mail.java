package server;

import java.io.Serializable;

/**
 *
 * @author Manuel
 */
public class Mail implements Serializable{
    
    private final String receiver;
    private final String sender;
    private final String message;
    private final String subject;

    public Mail(String receiver, String sender, String message, String subject) {
        this.receiver = receiver;
        this.sender = sender;
        this.message = message;
        this.subject = subject;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }
}