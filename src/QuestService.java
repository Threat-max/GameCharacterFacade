package facade;

// Subsystem class #4: gives the first quest of the story.
public class QuestService {
    public String assignFirstQuest(String characterName, String characterClass) {
        if (CharacterClasses.WARRIOR.equals(characterClass)) {
            return characterName + " must defend the Village Gate";
        }
        return characterName + " must find the Lost Spellbook";
    }
}
