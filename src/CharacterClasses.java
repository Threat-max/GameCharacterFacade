package facade;

// Clean Code: class names live in one place, no magic strings.
public final class CharacterClasses {
    public static final String WARRIOR = "Warrior";
    public static final String MAGE = "Mage";

    private CharacterClasses() {
        // utility class, should not be instantiated
    }
}
