package services;

import database.FileLoader;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import models.Profile;

public class ProfileService implements Serializable {

    private transient HashMap<Integer, Profile> profiles = new HashMap<>();
    private transient File file;
    private transient FileLoader fileLoader;

    public ProfileService() {
        file = new File("C:/Users/Oona/Documents/NetBeansProjects/SuperTeamApp/SuperTeamAppWithLogin2/src/main/java/files/profiles.data");
        fileLoader = new FileLoader();
        
        if(!(getReadProfiles(file) == null)) {
            profiles = getReadProfiles(file);
        }
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.profiles);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.profiles = (HashMap<Integer, Profile>) in.readObject();
    }

    public ArrayList<Profile> getAllProfiles() {
        profiles = getReadProfiles(file);
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfile(int id) {
        profiles = getReadProfiles(file);
        return profiles.get(id);
    }
    
    public Profile addProfile(Profile profile) {
        profiles.put(profile.getId(), profile);
        saveProfiles(profiles, file);
        return profile;
    }

    public Profile updateProfile(Profile profile) {

        if (profile.getId() <= 0) {
            return null;
        }
        profiles.put(profile.getId(), profile);
        saveProfiles(profiles, file);
        return profile;
    }

    public Profile removeProfile(int id) {
        return profiles.remove(id);
    }
    
    public void saveProfiles(HashMap<Integer, Profile> profiles, File file) {
        fileLoader.saveProfilesToFile(profiles, file);
    }
    
    private HashMap<Integer, Profile> getReadProfiles(File file) {
        HashMap<Integer, Profile> readProfiles = new HashMap<>();
        readProfiles = fileLoader.readProfilesFromFile(file);
        return readProfiles;
    }

}
