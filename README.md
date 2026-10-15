# Facade — Game Start for a New Player

Simple game-character themed project for Assignment #4 (Facade pattern).
Topic: creating a new player is a complex process (stats, items, skills,
quest, spawn, save). The Facade hides all of it behind one method.

## How to run

```
javac -d out $(find src -name "*.java")
java -cp out facade.FacadeDemo
java -cp out facade.DirectSubsystemDemo
```

## How it works

| Role | Class(es) |
|---|---|
| Complex subsystem (6 classes) | `StatsService`, `InventoryService`, `SkillService`, `QuestService`, `WorldService`, `SaveService` |
| Facade | `GameStartFacade` - `createNewPlayer(name, class)` and `playerExists(name)` |
| Client | `FacadeDemo` - uses only the facade |
| Advanced client (bypasses facade) | `DirectSubsystemDemo` |

Without the facade, the client would have to create six services and call
them in the correct order (stats first, because items depend on stats;
saving last). With the facade it is one call:

```java
GameStartFacade game = new GameStartFacade();
game.createNewPlayer("Conan", CharacterClasses.WARRIOR);
```

The subsystem classes do not know the facade exists (no back-references),
so `DirectSubsystemDemo` can still use `StatsService` and `SkillService`
directly. This is a defining property of the pattern.

Topic is different from the lecture's Home Theater example.

## Clean Code principles applied

**1. Meaningful names for a self-explanatory API**
The facade has only two public methods: `createNewPlayer(...)` and
`playerExists(...)`. The client does not need to read any subsystem class
to understand what they do.

**2. Single Responsibility: the facade coordinates, it does not reimplement**
`GameStartFacade` has no stat numbers, no item lists and no quest texts.
It only calls the subsystem classes in the right order. All real logic
(for example "low health gets more potions") stays in `InventoryService`.

**3. No leaking of subsystem details through the public interface**
Before: the client received `CharacterStats` and had to know about it.
After: `createNewPlayer` returns a simple `String` summary and the client
never sees `CharacterStats`, `InventoryService`, etc.

**4. Small, focused subsystem classes**
Each service does exactly one job: `StatsService` only makes stats,
`SaveService` only stores profiles, `WorldService` only spawns players.
Magic values are in named constants (`WARRIOR_HEALTH`, `SPAWN_POINT`,
`CharacterClasses.WARRIOR`).

**5. Proper error handling for coordination failures**
Subsystem errors (unknown class, duplicate player, empty name) are caught
in the facade and thrown as one `PlayerCreationException`, so the client
handles one exception type. The order of steps is also chosen so that
steps which can fail come before saving - a failed creation never leaves a
half-saved player.
