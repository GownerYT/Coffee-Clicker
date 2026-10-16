package Persistence;

import Business.Entities.Game;
import Business.Entities.Generator;
import Business.Entities.Upgrade;
import Persistence.Exceptions.PersistenceException;

import java.util.List;

/**
 * The GameDAO interface provides methods to interact with the database for Game-related operations.
 */
public interface GameDAO {

    /**
     * Creates a new game entry in the database.
     *
     * @param game The game object to be created.
     * @throws PersistenceException if an error occurs during the creation process.
     */
    void createGame(Game game) throws PersistenceException;

    /**
     * Inserts a generator associated with a game into the database.
     *
     * @param gameId        The ID of the game.
     * @param generatorName The name of the generator to be inserted.
     * @throws PersistenceException if an error occurs during the insertion process.
     */
    void insertGameGenerators(int gameId, String generatorName) throws PersistenceException;

    /**
     * Inserts an upgrade associated with a game into the database.
     *
     * @param gameId       The ID of the game.
     * @param upgradeName The name of the upgrade to be inserted.
     * @throws PersistenceException if an error occurs during the insertion process.
     */
    void insertGameUpgrades(int gameId, String upgradeName) throws PersistenceException;

    /**
     * Retrieves a game from the database by its ID.
     *
     * @param gameId The ID of the game to retrieve.
     * @return The Game object corresponding to the given ID, or null if not found.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    Game getGameById(int gameId) throws PersistenceException;

    /**
     * Retrieves a game from the database by its user ID.
     *
     * @param userId The user ID associated with the game.
     * @return The Game object corresponding to the given user ID, or null if not found.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    Game getGameByUserId(int userId) throws PersistenceException;

    /**
     * Retrieves the maximum game ID from the database.
     *
     * @return The maximum game ID.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    int getMaxGameId() throws PersistenceException;

    /**
     * Retrieves all generators associated with a game from the database.
     *
     * @param gameId The ID of the game.
     * @return A list of generators associated with the given game ID.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    List<Generator> getAllGeneratorsByGameId(int gameId) throws PersistenceException;

    /**
     * Retrieves all upgrades associated with a game from the database.
     *
     * @param gameId The ID of the game.
     * @return A list of upgrades associated with the given game ID.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    List<Upgrade> getAllUpgradesByGameId(int gameId) throws PersistenceException;

    /**
     * Updates an existing game entry in the database.
     *
     * @param game The game object to be updated.
     * @throws PersistenceException if an error occurs during the update process.
     */
    void updateGame(Game game) throws PersistenceException;

    /**
     * Deletes a game entry from the database by its ID.
     *
     * @param gameId The ID of the game to be deleted.
     * @throws PersistenceException if an error occurs during the deletion process.
     */
    void deleteGame(int gameId) throws PersistenceException;

}
