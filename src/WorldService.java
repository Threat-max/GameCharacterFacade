package facade;

import java.util.HashSet;
import java.util.Set;

// Subsystem class #5: places the player into the game world.
public class WorldService {
    private static final String SPAWN_POINT = "Village Square";

    private final Set<String> playersInWorld = new HashSet<>();

    public String spawnPlayer(String characterName) {
        if (!playersInWorld.add(characterName)) {
            throw new IllegalStateException("Player " + characterName + " is already in the world");
        }
        return SPAWN_POINT;
    }
}
