package Persistence;

import Business.Entities.Statistics;
import Persistence.Exceptions.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The SQLStatisticsDAO class implements the StatisticsDAO interface for interacting with the statistics data stored in a database.
 */
public class SQLStatisticsDAO implements StatisticsDAO {

    /**
     * Constructs a new SQLStatisticsDAO instance.
     */
    public SQLStatisticsDAO() {

    }

    /**
     * Inserts statistics data into the database.
     *
     * @param statistics The Statistics object containing the data to be inserted.
     * @throws PersistenceException if an error occurs during the insertion process.
     */
    @Override
    public void insertStatistics(Statistics statistics) throws PersistenceException {
        String query = "INSERT INTO statistics (userId, username, gameId, nameGame, coffeesAvailable, time) VALUES ("
                + statistics.getUserId() + ", '"
                + statistics.getUsername() + "', "
                + statistics.getGameId() + ", '"
                + statistics.getNameGame() + "', "
                + statistics.getCoffeesAvailable() + ", "
                + statistics.getTime() + ")";

        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.insertQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error inserting statistics: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves statistics by username.
     *
     * @param username The username associated with the statistics to retrieve.
     * @return A list of Statistics objects associated with the specified username.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public List<Statistics> getStatisticsByUsername(String username) throws PersistenceException {
        List<Statistics> statisticsList = new ArrayList<>();
        String query = "SELECT * FROM statistics WHERE username = '" + username + "'";
        SQLConnector connector = SQLConnector.getInstance();
        ResultSet resultSet = connector.selectQuery(query);
        try {
            while (resultSet != null && resultSet.next()) {
                Statistics statistics = new Statistics(
                        resultSet.getInt("userId"),
                        resultSet.getString("username"),
                        resultSet.getInt("gameId"),
                        resultSet.getString("nameGame"),
                        resultSet.getInt("coffeesAvailable"),
                        resultSet.getInt("time")
                );
                statisticsList.add(statistics);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Problem getting statistics by username: " + e.getMessage(), e);
        }
        return statisticsList;
    }

    /**
     * Deletes statistics data from the database based on user identifier, game identifier, and minimum time.
     *
     * @param userId The identifier of the user associated with the statistics to delete.
     * @param gameId The identifier of the game associated with the statistics to delete.
     * @param min    The minimum time associated with the statistics to delete.
     * @throws PersistenceException if an error occurs during the deletion process.
     */
    @Override
    public void deleteStatistics(int userId, int gameId, int min) throws PersistenceException {
        String query = "DELETE FROM statistics WHERE userId = " + userId +
                " AND gameId = " + gameId +
                " AND time = " + min;

        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.deleteQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error deleting statistics: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes statistics data from the database based on user identifier.
     *
     * @param userId The identifier of the user associated with the statistics to delete.
     * @throws PersistenceException if an error occurs during the deletion process.
     */
    @Override
    public void deleteStatisticsByUser(int userId) throws PersistenceException {
        String query = "DELETE FROM statistics WHERE userId = " + userId;

        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.deleteQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error deleting all statistics by user: " + e.getMessage(), e);
        }
    }
}
