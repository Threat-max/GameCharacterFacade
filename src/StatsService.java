package facade;

// Subsystem class #1: decides the starting stats of a character class.
public class StatsService {
    private static final int WARRIOR_HEALTH = 120;
    private static final int WARRIOR_ATTACK = 15;
    private static final int MAGE_HEALTH = 80;
    private static final int MAGE_ATTACK = 25;

    public CharacterStats createStats(String characterClass) {
        if (CharacterClasses.WARRIOR.equals(characterClass)) {
            return new CharacterStats(WARRIOR_HEALTH, WARRIOR_ATTACK);
        }
        if (CharacterClasses.MAGE.equals(characterClass)) {
            return new CharacterStats(MAGE_HEALTH, MAGE_ATTACK);
        }
        throw new IllegalArgumentException("Unknown character class: " + characterClass);
    }
}
