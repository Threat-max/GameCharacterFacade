package facade;

import java.util.ArrayList;
import java.util.List;

// Subsystem class #2: gives starting items. Depends on the stats:
// weaker (low health) characters get more health potions.
public class InventoryService {
    private static final int LOW_HEALTH_LIMIT = 100;
    private static final int EXTRA_POTIONS = 3;
    private static final int BASIC_POTIONS = 1;

    public List<String> giveStartingItems(String characterClass, CharacterStats stats) {
        List<String> items = new ArrayList<>();

        if (CharacterClasses.WARRIOR.equals(characterClass)) {
            items.add("Iron Sword");
        } else {
            items.add("Oak Staff");
        }

        int potions = BASIC_POTIONS;
        if (stats.getHealth() < LOW_HEALTH_LIMIT) {
            potions = EXTRA_POTIONS;
        }
        items.add("Health Potion x" + potions);
        return items;
    }
}
