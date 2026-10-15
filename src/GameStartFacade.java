package facade;

import java.util.List;

// Facade. The client calls ONE method instead of coordinating six
// subsystem classes in the right order. The facade only coordinates -
// all real logic stays inside the subsystem classes, and they do not
// know that the facade exists.
public class GameStartFacade {
    private final StatsService statsService = new StatsService();
    private final InventoryService inventoryService = new InventoryService();
    private final SkillService skillService = new SkillService();
    private final QuestService questService = new QuestService();
    private final WorldService worldService = new WorldService();
    private final SaveService saveService = new SaveService();

    public String createNewPlayer(String characterName, String characterClass) {
        try {
            checkName(characterName);

            // Order matters: stats first (items depend on them), saving last.
            // Steps that can fail (unknown class, duplicate player) come before
            // saving, so a failed creation never leaves a half-saved player.
            CharacterStats stats = statsService.createStats(characterClass);
            List<String> items = inventoryService.giveStartingItems(characterClass, stats);
            List<String> skills = skillService.unlockStartingSkills(characterClass);
            String quest = questService.assignFirstQuest(characterName, characterClass);
            String spawnPoint = worldService.spawnPlayer(characterName);

            String summary = buildSummary(characterName, characterClass, stats, items, skills, quest, spawnPoint);
            saveService.saveProfile(characterName, summary);
            return summary;
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new PlayerCreationException("Could not create player: " + e.getMessage(), e);
        }
    }

    public boolean playerExists(String characterName) {
        return saveService.hasSave(characterName);
    }

    private void checkName(String characterName) {
        if (characterName == null || characterName.isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }
    }

    private String buildSummary(String name, String characterClass, CharacterStats stats,
                                List<String> items, List<String> skills, String quest, String spawnPoint) {
        return characterClass + " " + name + " (" + stats.describe() + ")\n"
                + "  Items:  " + items + "\n"
                + "  Skills: " + skills + "\n"
                + "  Quest:  " + quest + "\n"
                + "  Spawn:  " + spawnPoint;
    }
}
