package facade;

import java.util.HashMap;
import java.util.Map;

// Subsystem class #6: stores the finished player profile.
public class SaveService {
    private final Map<String, String> savedProfiles = new HashMap<>();

    public void saveProfile(String characterName, String profile) {
        if (savedProfiles.containsKey(characterName)) {
            throw new IllegalStateException("A save for " + characterName + " already exists");
        }
        savedProfiles.put(characterName, profile);
    }

    public boolean hasSave(String characterName) {
        return savedProfiles.containsKey(characterName);
    }
}
