package servlets;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.*;
import security.HashPass;

public class Validate {

    public static boolean checkUser(String email, String pass) throws IOException {
        try {
            File fXmlFile = new File("C:/Users/Oona/Documents/NetBeansProjects/SuperTeamApp/SuperTeamAppWithLogin2/src/main/webapp/users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
                        
                        //get this user's salt from the xml file
                        String salt = eElement.getElementsByTagName("salt").item(0).getTextContent();
                        
                        //hash the given password
                        HashPass hashpass = new HashPass();
                        String hashedPass = hashpass.hashPassword(pass, salt);
                        
                        //comparing the hashed password to the one in the file
                        if (hashedPass.equals(eElement.getElementsByTagName("password").item(0).getTextContent())) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static String fetchFirstName(String email) {
        String firstName = "User";
        try {
            File fXmlFile = new File("C:/Users/Oona/Documents/NetBeansProjects/SuperTeamApp/SuperTeamAppWithLogin2/src/main/webapp/users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
                        firstName = eElement.getElementsByTagName("first_name").item(0).getTextContent();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firstName;
    }
    
    public static String fetchLastName(String email) {
        String lastName = "User";
        try {

            File fXmlFile = new File("C:/Users/Oona/Documents/NetBeansProjects/SuperTeamApp/SuperTeamAppWithLogin2/src/main/webapp/users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
                        lastName = eElement.getElementsByTagName("last_name").item(0).getTextContent();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastName;
    }
}