package facade;

// Client. Uses ONLY the facade - it never creates or calls
// StatsService, InventoryService, etc. directly.
public class FacadeDemo {
    public static void main(String[] args) {
        GameStartFacade game = new GameStartFacade();

        System.out.println(game.createNewPlayer("Conan", CharacterClasses.WARRIOR));
        System.out.println();
        System.out.println(game.createNewPlayer("Gandalf", CharacterClasses.MAGE));
        System.out.println();

        // Error handling: coordination failures come out as one clear exception.
        tryToCreate(game, "Conan", CharacterClasses.WARRIOR);
        tryToCreate(game, "Robin", "Archer");
        tryToCreate(game, "", CharacterClasses.MAGE);
    }

    private static void tryToCreate(GameStartFacade game, String name, String characterClass) {
        try {
            game.createNewPlayer(name, characterClass);
        } catch (PlayerCreationException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }
}
