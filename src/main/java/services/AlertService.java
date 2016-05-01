package services;

import database.FileLoader;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import main.HibernateUtil;

import models.Alert;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AlertService implements Serializable {

    private static final long serialVersionUID = -4596245664039001961L;
    private transient FileLoader fileLoader;

    //private HashMap<Integer, Alert> alerts = TestDatabase.getAlerts();
    private transient HashMap<Integer, Alert> alerts = new HashMap<>();
    private transient File file;

    public AlertService() {
        file = new File("C:/Users/Oona/Documents/NetBeansProjects/SuperTeamApp/SuperTeamAppWithLogin2/src/main/java/files/alerts.data");
        fileLoader = new FileLoader();

        if (!(getReadAlerts() == null)) {
            alerts = getReadAlerts();
        }

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.alerts);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.alerts = (HashMap<Integer, Alert>) in.readObject();
    }

    //for testing purposes; can be removed
    public void addAlertsToTestDatabase() {
        Alert alert1 = new Alert("Milja Mammutti", 10, "Haisuli Haisulinen", 7, "Jäätelö loppu!!!!!");
        alerts.put(alert1.getAlertId(), alert1);
        fileLoader.saveAlertsToFile(alerts, file);
        //System.out.println("Added test alerts");

    }

    //returns alerts as an arraylist
    public List<Alert> getAllAlerts() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();

        Criteria criteria = session.createCriteria(Alert.class);
        List<Alert> alertList = (List<Alert>) criteria.list();
        tr.commit();
        session.close();
        return alertList;

        //return new ArrayList<>(alerts.values());
    }

    //returns a particular alert
    public Alert getAlert(int alertId) {
        return alerts.get(alertId);
    }

    //adds an alert to the alerts hashmap and saves it to the file
    public Alert addAlert(Alert alert) {
        /* alert.setAlertId(alerts.size() + 1);
        alerts.put(alert.getAlertId(), alert);
        saveAlerts();
        return alert;*/

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(alert);

        session.getTransaction().commit();
        session.close();
        return alert;

    }

    //updates an existing alert and saves it to the file
    public Alert updateAlert(Alert alert) {
        if (alert.getAlertId() <= 0) {
            return null;
        }
        alerts.put(alert.getAlertId(), alert);
        saveAlerts();
        return alert;
    }

    //removes an alert from the file and returns the alert that was removed
    public Alert removeAlert(int alertId) {
        //Alert removedAlert = alerts.remove(alertId);
        alerts = getReadAlerts();
        //System.out.println(alerts.size());
        Alert removedAlert = alerts.remove(alertId);
        //System.out.println(alerts.size());
        saveAfterAlertRemoval();
        return removedAlert;
    }

    //saves alerts to file
    public void saveAlerts() {
        fileLoader.saveAlertsToFile(alerts, file);
    }

    //saves alerts to file after removing an alert
    public void saveAfterAlertRemoval() {
        fileLoader.saveAlertsAfterRemoval(alerts, file);
    }

    //reads the alerts from file and saves them into a temporary hashmap for use
    private HashMap<Integer, Alert> getReadAlerts() {
        HashMap<Integer, Alert> readAlerts = new HashMap<>();
        readAlerts = fileLoader.readAlertsFromFile(file);
        return readAlerts;
    }
}
