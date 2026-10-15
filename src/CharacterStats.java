package facade;

// Simple data holder produced by StatsService and used by other subsystem classes.
public class CharacterStats {
    private final int health;
    private final int attack;

    public CharacterStats(int health, int attack) {
        if (health <= 0 || attack <= 0) {
            throw new IllegalArgumentException("Stats must be positive");
        }
        this.health = health;
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public String describe() {
        return "HP: " + health + ", ATK: " + attack;
    }
}
