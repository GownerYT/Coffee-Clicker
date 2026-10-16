package Persistence;

import Business.Entities.Configuration;
import Persistence.Exceptions.PersistenceException;

import java.sql.*;

/**
 * The SQLConnector class provides methods for connecting to and interacting with a MySQL database.
 */
public class SQLConnector {
    private final String username;
    private final String password;
    private final String url;
    private Connection connection;
    private static SQLConnector instance = null;

    /**
     * Retrieves the singleton instance of SQLConnector.
     *
     * @return The singleton instance of SQLConnector.
     * @throws PersistenceException if there is an error during the instance retrieval process.
     */
    public static SQLConnector getInstance() throws PersistenceException {
        if (instance == null) {
            ConfigurationDAO configurationDAO = new ConfigurationDAO();
            Configuration configuration = configurationDAO.readConfiguration();
            instance = new SQLConnector(configuration.getDatabaseUser(), configuration.getDatabasePassword(),
                    configuration.getDatabaseIP(), configuration.getDatabasePort(), configuration.getDatabaseName());
            instance.setConnection();
        }
        return instance;
    }

    /**
     * Constructs a SQLConnector object with the specified connection parameters.
     *
     * @param username The username for the database connection.
     * @param password The password for the database connection.
     * @param ip       The IP address of the database server.
     * @param port     The port number of the database server.
     * @param database The name of the database to connect to.
     */
    private SQLConnector(String username, String password, String ip, int port, String database) {
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
    }

    /**
     * Establishes a connection to the MySQL database.
     *
     * @throws PersistenceException if there is an error during the connection process.
     */
    private void setConnection() throws PersistenceException {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            throw new PersistenceException("Couldn't connect to database: " + e.getMessage() , e);
        }
    }

    /**
     * Closes the connection to the MySQL database.
     *
     * @throws PersistenceException if there is an error during the disconnection process.
     */
    public void setDisconnection() throws PersistenceException{
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error disconnecting from the database: " + e.getMessage(), e);
        }
    }

    /**
     * Executes an insert query on the database.
     *
     * @param query The SQL insert query to be executed.
     * @throws PersistenceException if there is an error during the insert query execution.
     */
    public void insertQuery(String query) throws PersistenceException{
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new PersistenceException("Problem when inserting --> " + e.getSQLState() + " (" + e.getMessage() + ")", e);
            }
        } else {
            System.err.println("Connection is null. Cannot execute query.");
        }
    }

    /**
     * Executes an update query on the database.
     *
     * @param query The SQL update query to be executed.
     * @throws PersistenceException if there is an error during the update query execution.
     */
    public void updateQuery(String query) throws PersistenceException{
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                System.err.println(query);
                throw new PersistenceException("Problem when updating --> " + e.getSQLState() + " (" + e.getMessage() + ")", e);
            }
        } else {
            System.err.println("Connection is null. Cannot execute query.");
        }
    }

    /**
     * Executes a select query on the database.
     *
     * @param query The SQL select query to be executed.
     * @return The ResultSet containing the results of the query.
     * @throws PersistenceException if there is an error during the select query execution.
     */
    public ResultSet selectQuery(String query) throws PersistenceException{
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                resultSet = s.executeQuery(query);
            } catch (SQLException e) {
                throw new PersistenceException("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")", e);
            }
        } else {
            System.err.println("Connection is null. Cannot execute query.");
        }
        return resultSet;
    }

    /**
     * Executes a delete query on the database.
     *
     * @param query The SQL delete query to be executed.
     * @throws PersistenceException if there is an error during the delete query execution.
     */
    public void deleteQuery(String query) throws PersistenceException{
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new PersistenceException("Problem when deleting: " + e.getSQLState() + " (" + e.getMessage() + ")", e);
            }
        } else {
            System.err.println("Connection is null. Cannot execute query.");
        }
    }
}
