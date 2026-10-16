package Business.Managers;

import Business.Entities.Statistics;
import Business.Exceptions.BusinessException;
import Persistence.Exceptions.PersistenceException;
import Persistence.SQLStatisticsDAO;

import java.util.List;

/**
 * Manages operations related to statistics, including creation, retrieval, updating, and deletion.
 * This class interacts with the data access object (DAO) for statistics to perform database operations.
 */
public class StatisticsManager {
    private final SQLStatisticsDAO sqlStatisticsDAO;

    /**
     * Constructor for the StatisticsManager class.
     *
     * @param sqlStatisticsDAO The data access object for statistics operations.
     */
    public StatisticsManager(SQLStatisticsDAO sqlStatisticsDAO) {
        this.sqlStatisticsDAO = sqlStatisticsDAO;
    }

    /**
     * Initializes statistics for a new game session and inserts them into the database.
     *
     * @param userId                The ID of the user associated with the statistics.
     * @param username              The username of the user associated with the statistics.
     * @param gameId                The ID of the game associated with the statistics.
     * @param nameGame              The name of the game associated with the statistics.
     * @param coffeesAvailable      The number of coffees available in the game session.
     * @param timeElapsedInMinutes  The time elapsed in minutes in the game session.
     * @throws BusinessException if an error occurs during the initialization process.
     */
    public void initializeStatistics(int userId, String username, int gameId, String nameGame, int coffeesAvailable, int timeElapsedInMinutes) throws BusinessException {
        Statistics currentStatistics = new Statistics(userId, username, gameId, nameGame, coffeesAvailable, timeElapsedInMinutes);
        createStatistics(currentStatistics);
    }

    /**
     * Inserts statistics into the database.
     *
     * @param statistics The statistics object to insert.
     * @throws BusinessException if an error occurs during the insertion process.
     */
    public void createStatistics(Statistics statistics) throws BusinessException {
        try {
            sqlStatisticsDAO.insertStatistics(statistics);
        } catch (PersistenceException e) {
            throw new BusinessException("Error creating statistics", e);
        }
    }

    /**
     * Retrieves statistics by username from the database.
     *
     * @param username The username to retrieve statistics for.
     * @return A list of statistics associated with the specified username.
     * @throws BusinessException if an error occurs during the retrieval process.
     */
    public List<Statistics> getStatisticsByUsername(String username) throws BusinessException {
        try {
            return sqlStatisticsDAO.getStatisticsByUsername(username);
        } catch (PersistenceException e) {
            throw new BusinessException("Error getting statistics by username", e);
        }
    }

    /**
     * Deletes statistics from the database.
     *
     * @param userId The ID of the user associated with the statistics to delete.
     * @param gameId The ID of the game associated with the statistics to delete.
     * @param min    The minimum time elapsed in minutes for the statistics to delete.
     * @throws BusinessException if an error occurs during the deletion process.
     */
    public void deleteStatistics(int userId, int gameId, int min) throws BusinessException {
        try {
            sqlStatisticsDAO.deleteStatistics(userId, gameId, min);
        } catch (PersistenceException e) {
            throw new BusinessException("Error deleting statistics", e);
        }
    }
}
