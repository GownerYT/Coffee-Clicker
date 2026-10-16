package Business.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Represents a game entity with various attributes and functionalities.
 */
public class Game {
    private final String nameGame;
    private final int gameId;
    private final int userId;
    private int cafesAvailable;
    private int elapsedTime;
    private final List<Generator> generators;
    private final List<Upgrade> upgrades;
    private final Semaphore semaphore;

    /**
     * Constructs a Game object with the specified parameters.
     *
     * @param nameGame       The name of the game.
     * @param gameId         The ID of the game.
     * @param userId         The ID of the user associated with the game.
     * @param cafesAvailable The number of cafes available in the game.
     * @param elapsedTime    The elapsed time of the game.
     */
    public Game(String nameGame, int gameId, int userId, int cafesAvailable, int elapsedTime) {
        this.nameGame = nameGame;
        this.gameId = gameId;
        this.userId = userId;
        this.cafesAvailable = cafesAvailable;
        this.elapsedTime = elapsedTime;
        this.generators = new ArrayList<>();
        this.upgrades = new ArrayList<>();
        this.semaphore = new Semaphore(1);
    }

    /**
     * Gets the count of generators with the specified name.
     *
     * @param generatorName The name of the generator.
     * @return The count of generators with the specified name.
     */
    public int getGeneratorCountByName(String generatorName) {
        int count = 0;
        for (Generator generator : generators) {
            if (generator.getName().equals(generatorName)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the count of upgrades with the specified name.
     *
     * @param upgradeName The name of the upgrade.
     * @return The count of upgrades with the specified name.
     */
    public int getUpgradeCountByName(String upgradeName) {
        int count = 0;
        for (Upgrade upgrade : upgrades) {
            if (upgrade.getUpgradeName().equals(upgradeName)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the upgrade with the specified name.
     *
     * @param name The name of the upgrade.
     * @return The upgrade with the specified name, or null if not found.
     */
    public Upgrade getUpgradeByName(String name) {
        for (Upgrade upgrade : upgrades) {
            if (upgrade.getGeneratorName().equals(name)) {
                return upgrade;
            }
        }
        return null;
    }

    /**
     * Adds a generator to the game.
     *
     * @param generator The generator to add.
     */
    public void addGenerator(Generator generator) {
        this.generators.add(generator);
    }

    /**
     * Adds an upgrade to the game.
     *
     * @param upgrade The upgrade to add.
     */
    public void addUpgrade(Upgrade upgrade) {
        this.upgrades.add(upgrade);
    }

    /**
     * Gets the name of the game.
     *
     * @return The name of the game.
     */
    public String getNameGame() {
        return nameGame;
    }

    /**
     * Gets the ID of the game.
     *
     * @return The ID of the game.
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Gets the ID of the user associated with the game.
     *
     * @return The ID of the user.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the number of cafes available in the game.
     *
     * @return The number of cafes available.
     */
    public int getCafesAvailable() {
        return cafesAvailable;
    }

    /**
     * Sets the number of cafes available in the game.
     *
     * @param cafesAvailable The number of cafes available.
     */
    public void setCafesAvailable(int cafesAvailable) {
        this.cafesAvailable = cafesAvailable;
    }

    /**
     * Gets the elapsed time of the game in minutes.
     *
     * @return The elapsed time of the game in minutes.
     */
    public int getElapsedTimeInMinutes() {
        return elapsedTime/60 + 1;
    }

    /**
     * Gets the elapsed time of the game.
     *
     * @return The elapsed time of the game.
     */
    public int getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Sets the elapsed time of the game.
     *
     * @param elapsedTime The elapsed time of the game.
     */
    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Gets the semaphore used for synchronization.
     *
     * @return The semaphore.
     */
    public Semaphore getSemaphore() {
        return semaphore;
    }

    /**
     * Gets the list of generators in the game.
     *
     * @return The list of generators.
     */
    public List<Generator> getGenerators() {
        return generators;
    }

    /**
     * Gets the list of upgrades in the game.
     *
     * @return The list of upgrades.
     */
    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    /**
     * Increments the number of cafes available in the game.
     */
    public void incrementCoffee() {
        cafesAvailable++;
    }
}
