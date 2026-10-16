package Business.Managers;

import Business.Entities.Game;
import Business.Entities.Generator;
import Business.Entities.Upgrade;
import Business.Exceptions.BusinessException;
import Persistence.Exceptions.PersistenceException;
import Persistence.SQLGameDAO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * Manages game-related operations and interactions.
 */
public class GameManager {
    private final SQLGameDAO sqlGameDAO;
    private Game currentGame;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private int currentPastGameMinutes ;
    private int currentPastGameSeconds ;
    private boolean appliedCurrentMinutes = false;

    /**
     * Constructs a GameManager object with the specified SQLGameDAO.
     *
     * @param sqlGameDAO The data access object for game-related operations.
     */
    public GameManager(SQLGameDAO sqlGameDAO) {
        this.sqlGameDAO = sqlGameDAO;
    }

    /**
     * Initializes a new game or retrieves an existing one for the specified user.
     *
     * @param nameGame       The name of the game.
     * @param userId         The ID of the user.
     * @param cafesAvailable The number of cafes available in the game.
     * @param elapsedTime    The elapsed time in the game.
     */
    public void initializeGame(String nameGame, int userId, int cafesAvailable, int elapsedTime) throws BusinessException {
        Game existingGame = getGameByUserId(userId);
        currentGame = existingGame;

        if (existingGame == null) {
            int maxGameId = getMaxGameId();
            int gameId = maxGameId + 1;
            Game newGame = new Game(nameGame, gameId, userId, cafesAvailable, elapsedTime);
            currentGame = newGame;
            createGame(newGame);
        }
    }

    /**
     * Starts the timer for tracking game duration.
     */
    public void startTimer() {
        startTime = LocalDateTime.now();
    }

    /**
     * Stops the timer for tracking game duration.
     */
    public void stopTimer() {
        stopTime = LocalDateTime.now();
    }

    /**
     * Calculates the elapsed time since the game started.
     */
    public void calculateElapsedTime() {
        if (!appliedCurrentMinutes) {
            currentPastGameSeconds = currentGame.getElapsedTime();
            currentPastGameMinutes = currentPastGameSeconds / 60;
            appliedCurrentMinutes = true;
        }

        if (startTime != null) {
            int seconds = (int) ChronoUnit.SECONDS.between(startTime, LocalDateTime.now());
            int totalSeconds = currentPastGameSeconds + seconds;
            currentPastGameMinutes = totalSeconds / 60 + 1;
            getCurrentGame().setElapsedTime(totalSeconds);
        } else {
            System.err.println("Start time is null or already applied current minutes.");
        }
    }

    /**
     * Resets the flag indicating whether current minutes have been applied.
     */
    public void resetAppliedCurrentMinutes() {
        appliedCurrentMinutes = false;
    }

    /**
     * Retrieves the production upgrade value.
     *
     * @param production The production value.
     * @return The production upgrade value.
     */
    public int getProductionUpgrade(int production) {
        return production;
    }

    /**
     * Calculates the production value for a generator based on the number of generators,
     * number of upgrades, and production upgrade value.
     *
     * @param production        The base production value of the generator.
     * @param numGenerators     The number of generators.
     * @param numUpgrades       The number of upgrades applied to the generator.
     * @param productionUpgrade The production upgrade value.
     * @return The calculated production value for the generator.
     */
    public int getProductionGenerator(int production, int numGenerators, int numUpgrades, int productionUpgrade) {
        if (numGenerators == 0) {
            return 0;
        } else if (numUpgrades == 0) {
            return production;
        } else {
            return production * numUpgrades * productionUpgrade;
        }
    }

    /**
     * Calculates the total production value for a generator.
     *
     * @param countFirstGenerator      The count of the first generator.
     * @param productionFirstGenerator The production value of the first generator.
     * @return The total production value for the generator.
     */
    public int getTotalProductionGenerator(int countFirstGenerator, int productionFirstGenerator) {
        return countFirstGenerator * productionFirstGenerator;
    }

    /**
     * Calculates the total production percentage for a generator based on its total production
     * and the total productions of other generators.
     *
     * @param totalProduction   The total production value of the generator.
     * @param totalProductions A map containing the total productions of all generators.
     * @return The total production percentage of the generator.
     */
    public int getTotalProductionPercentageGenerator(int totalProduction, Map<String, Integer> totalProductions) {
        if (totalProduction <= 0 || totalProductions.isEmpty()) {
            return 0;
        }

        int totalProductionSum = totalProductions.values().stream().mapToInt(Integer::intValue).sum();
        if (totalProductionSum <= 0) {
            return 0;
        }

        return (totalProduction * 100) / totalProductionSum;
    }

    /**
     * Retrieves the current game.
     *
     * @return The current game.
     */
    public Game getCurrentGame() {
        return currentGame;
    }

    /**
     * Creates a new game in the database.
     *
     * @param game The game to be created.
     * @throws BusinessException if an error occurs during the creation process.
     */
    public void createGame(Game game) throws BusinessException {
        try {
            sqlGameDAO.createGame(game);
        } catch (PersistenceException e) {
            throw new BusinessException("Error creating game", e);
        }
    }

    /**
     * Inserts generators for a specific game into the database.
     *
     * @param gameId        The ID of the game.
     * @param generatorName The name of the generator to insert.
     * @throws BusinessException if an error occurs during the insertion process.
     */
    public void insertGameGenerators(int gameId, String generatorName) throws BusinessException {
        try {
            sqlGameDAO.insertGameGenerators(gameId, generatorName);
        } catch (PersistenceException e) {
            throw new BusinessException("Error inserting game generators", e);
        }
    }

    /**
     * Inserts upgrades for a specific game into the database.
     *
     * @param gameId      The ID of the game.
     * @param upgradeName The name of the upgrade to insert.
     * @throws BusinessException if an error occurs during the insertion process.
     */
    public void insertGameUpgrades(int gameId, String upgradeName) throws BusinessException {
        try {
            sqlGameDAO.insertGameUpgrades(gameId, upgradeName);
        } catch (PersistenceException e) {
            throw new BusinessException("Error inserting game upgrades", e);
        }
    }

    /**
     * Retrieves a game by its ID from the database.
     *
     * @param gameId The ID of the game to retrieve.
     * @return The game with the specified ID, or null if not found.
     * @throws BusinessException if an error occurs during the retrieval process.
     */
    public Game getGameById(int gameId) throws BusinessException {
        try {
            return sqlGameDAO.getGameById(gameId);
        } catch (PersistenceException e) {
            throw new BusinessException("Error retrieving game by ID", e);
        }
    }

    /**
     * Retrieves a game by the user ID from the database.
     *
     * @param userId The ID of the user associated with the game.
     * @return The game associated with the specified user ID, or null if not found.
     * @throws BusinessException if an error occurs during the retrieval process.
     */
    public Game getGameByUserId(int userId) throws BusinessException {
        try {
            return sqlGameDAO.getGameByUserId(userId);
        } catch (PersistenceException e) {
            throw new BusinessException("Error retrieving game by user ID", e);
        }
    }

    /**
     * Retrieves the maximum game ID from the database.
     *
     * @return The maximum game ID.
     * @throws BusinessException if an error occurs during the retrieval process.
     */
    public int getMaxGameId() throws BusinessException {
        try {
            return sqlGameDAO.getMaxGameId();
        } catch (PersistenceException e) {
            throw new BusinessException("Error retrieving max game ID", e);
        }
    }

    /**
     * Retrieves all generators associated with a specific game from the database.
     *
     * @param gameId The ID of the game.
     * @return A list of generators associated with the specified game.
     * @throws BusinessException if an error occurs during the retrieval process.
     */
    public List<Generator> getAllGeneratorsByGameId(int gameId) throws BusinessException {
        try {
            return sqlGameDAO.getAllGeneratorsByGameId(gameId);
        } catch (PersistenceException e) {
            throw new BusinessException("Error retrieving all generators by game ID", e);
        }
    }

    /**
     * Retrieves all upgrades associated with a specific game from the database.
     *
     * @param gameId The ID of the game.
     * @return A list of upgrades associated with the specified game.
     * @throws BusinessException if an error occurs during the retrieval process.
     */
    public List<Upgrade> getAllUpgradesByGameId(int gameId) throws BusinessException {
        try {
            return sqlGameDAO.getAllUpgradesByGameId(gameId);
        } catch (PersistenceException e) {
            throw new BusinessException("Error retrieving all upgrades by game ID", e);
        }
    }

    /**
     * Updates a game in the database.
     *
     * @param game The game to update.
     * @throws BusinessException if an error occurs during the update process.
     */
    public void updateGameInDatabase(Game game) throws BusinessException {
        try {
            sqlGameDAO.updateGame(game);
        } catch (PersistenceException e) {
            throw new BusinessException("Error updating game", e);
        }
    }

    /**
     * Deletes a game from the database.
     *
     * @param gameId The ID of the game to delete.
     * @throws BusinessException if an error occurs during the deletion process.
     */
    public void deleteGame(int gameId) throws BusinessException {
        try {
            sqlGameDAO.deleteGame(gameId);
        } catch (PersistenceException e) {
            throw new BusinessException("Error deleting game", e);
        }
    }
}

