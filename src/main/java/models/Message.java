package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;

@Entity (name = "message_details")
@XmlRootElement
public class Message implements Serializable {

    /**
     *
     * @author Oona
     */
    @Id
    @GeneratedValue
    private int messageId;

    private static int messageIdCounter;
    private int senderId;
    private String messageContent;
    private String sender;
    private String receiver;
    private transient Date sentDate;

    public Message() {
    }

    public Message(String sender, String receiver, String messageContent) {
        messageIdCounter++;
        this.messageId = messageIdCounter;
        //this.senderId = senderId;
        this.sender = sender;
        this.receiver = receiver;
        this.messageContent = messageContent;
        this.sentDate = new Date();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.sentDate);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.sentDate = (Date) in.readObject();
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }
}
