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
public class Task implements Serializable {

    /**
     *
     * @author Oona
     */
    private transient Date taskGivenDate;
    private String taskCompleteDate;
    private String sentBy;
    private int senderId;

    @Id
    @GeneratedValue
    private int taskId;

    private static int taskIdCounter;
    private String receiverName;
    private int receiverId;
    private String taskContent;
    private boolean taskCompleted;

    public Task() {
    }

    public Task(String sentBy, int senderId, String receiverName, int receiverId, String taskContent) {
        this("", sentBy, senderId, receiverName, receiverId, taskContent);
    }

    public Task(String taskCompleteDate, String sentBy, int senderId, String receiverName, int receiverId,
            String taskContent) {
        taskIdCounter++;
        this.taskId = taskIdCounter;
        this.taskGivenDate = new Date();
        this.taskCompleteDate = taskCompleteDate;
        this.taskCompleted = false;
        this.taskContent = taskContent;
        this.receiverName = receiverName;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.sentBy = sentBy;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.taskGivenDate);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.taskGivenDate = (Date) in.readObject();
    }

    public Date getTaskGivenDate() {
        return taskGivenDate;
    }

    public void setTaskGivenDate(Date taskGivenDate) {
        this.taskGivenDate = taskGivenDate;
    }

    public String getTaskCompleteDate() {
        return taskCompleteDate;
    }

    public void setTaskCompleteDate(String taskCompleteDate) {
        this.taskCompleteDate = taskCompleteDate;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public boolean isTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

}
