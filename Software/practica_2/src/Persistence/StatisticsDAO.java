package Persistence;

import Business.Entities.Statistics;
import Persistence.Exceptions.PersistenceException;

import java.util.List;

/**
 * The StatisticsDAO interface defines methods for interacting with statistics data stored in a database.
 */
public interface StatisticsDAO {

    /**
     * Inserts statistics data into the database.
     *
     * @param statistics The Statistics object to be inserted.
     * @throws PersistenceException if an error occurs during the insertion process.
     */
    void insertStatistics(Statistics statistics) throws PersistenceException;

    /**
     * Retrieves statistics by username.
     *
     * @param username The username associated with the statistics to retrieve.
     * @return A list of Statistics objects corresponding to the specified username.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    List<Statistics> getStatisticsByUsername(String username) throws PersistenceException;

    /**
     * Deletes statistics from the database based on user ID, game ID, and time.
     *
     * @param userId The ID of the user associated with the statistics to delete.
     * @param gameId The ID of the game associated with the statistics to delete.
     * @param min    The time associated with the statistics to delete.
     * @throws PersistenceException if an error occurs during the deletion process.
     */
    void deleteStatistics(int userId, int gameId, int min) throws PersistenceException;

    /**
     * Deletes statistics from the database based on user ID.
     *
     * @param userId The ID of the user associated with the statistics to delete.
     * @throws PersistenceException if an error occurs during the deletion process.
     */
    void deleteStatisticsByUser(int userId) throws PersistenceException;
}
