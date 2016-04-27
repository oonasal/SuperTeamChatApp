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

@Entity
@XmlRootElement
public class Alert implements Serializable {

    /**
     *
     * @author Oona
     */
    private static final long serialVersionUID = -2532752231158282480L;
    private String senderName;
    private int senderId;

    @Id
    @GeneratedValue
    private int alertId;

    private static int alertIdCounter;
    private String receiverName;
    private int receiverId;
    private transient Date sendingDateAndTime;
    private String alertContent;

    public Alert() {

    }

    public Alert(String senderName, String receiverName, String alertContent) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.alertContent = alertContent;
        alertIdCounter++;
        this.sendingDateAndTime = new Date();
    }

    public Alert(String senderName, int senderId, String receiverName, int receiverId, String alertContent) {
        alertIdCounter++;
        this.alertId = alertIdCounter;
        this.sendingDateAndTime = new Date();
        this.receiverName = receiverName;
        this.receiverId = receiverId;
        this.alertContent = alertContent;
        this.senderId = senderId;
        this.senderName = senderName;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.sendingDateAndTime);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.sendingDateAndTime = (Date) in.readObject();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getAlertContent() {
        return alertContent;
    }

    public void setAlertContent(String alertContent) {
        this.alertContent = alertContent;
    }

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public Date getSendingDateAndTime() {
        return sendingDateAndTime;
    }

    public void setSendingDateAndTime(Date sendingDateAndTime) {
        this.sendingDateAndTime = sendingDateAndTime;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
}
