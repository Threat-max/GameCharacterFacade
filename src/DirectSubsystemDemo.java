package facade;

// An "advanced client" that bypasses the facade. This works because the
// subsystem classes do not depend on the facade - it is a normal part of
// the Facade pattern, not a bug.
public class DirectSubsystemDemo {
    public static void main(String[] args) {
        StatsService statsService = new StatsService();
        SkillService skillService = new SkillService();

        CharacterStats stats = statsService.createStats(CharacterClasses.MAGE);
        System.out.println("Mage stats: " + stats.describe());
        System.out.println("Mage skills: " + skillService.unlockStartingSkills(CharacterClasses.MAGE));
    }
}
