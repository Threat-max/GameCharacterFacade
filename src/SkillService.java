package facade;

import java.util.ArrayList;
import java.util.List;

// Subsystem class #3: unlocks the first skills of a character.
public class SkillService {
    private static final String BASIC_SKILL = "Basic Attack";

    public List<String> unlockStartingSkills(String characterClass) {
        List<String> skills = new ArrayList<>();
        skills.add(BASIC_SKILL);

        if (CharacterClasses.WARRIOR.equals(characterClass)) {
            skills.add("Power Strike");
        } else {
            skills.add("Fireball");
        }
        return skills;
    }
}
