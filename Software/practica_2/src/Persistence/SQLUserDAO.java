package Persistence;

import Business.Entities.User;
import Persistence.Exceptions.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The SQLUserDAO class implements the UserDAO interface for interacting with user data stored in a database.
 */
public class SQLUserDAO implements UserDAO {

    /**
     * Constructs a new SQLUserDAO instance.
     */
    public SQLUserDAO() {
    }

    /**
     * Inserts a user into the database.
     *
     * @param user The User object to be inserted.
     * @throws PersistenceException if an error occurs during the insertion process.
     */
    @Override
    public void insertUser(User user) throws PersistenceException {
        String query = "INSERT INTO user (userId, username, email, password) VALUES (" + user.getUserId() + ", '"
                + user.getUsername() + "', '" + user.getEmail() + "', '" + user.getPassword() + "')";
        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.insertQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error inserting user: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object corresponding to the specified username.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public User getUserByUsername(String username) throws PersistenceException {
        String query = "SELECT * FROM user WHERE username = '" + username + "'";
        SQLConnector connector = SQLConnector.getInstance();
        ResultSet resultSet = connector.selectQuery(query);
        try {
            if (resultSet != null && resultSet.next()) {
                return new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new PersistenceException("Problem getting user by username: " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user to retrieve.
     * @return The User object corresponding to the specified email.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public User getUserByEmail(String email) throws PersistenceException {
        String query = "SELECT * FROM user WHERE email = '" + email + "'";
        SQLConnector connector = SQLConnector.getInstance();
        ResultSet resultSet = connector.selectQuery(query);
        try {
            if (resultSet != null && resultSet.next()) {
                return new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new PersistenceException("Problem getting user by email: " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all User objects stored in the database.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public List<User> getAllUsers() throws PersistenceException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        SQLConnector connector = SQLConnector.getInstance();
        ResultSet resultSet = connector.selectQuery(query);
        try {
            while (resultSet != null && resultSet.next()) {
                User user = new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Problem getting all users: " + e.getMessage(), e);
        }
        return users;
    }

    /**
     * Retrieves the maximum user ID from the database.
     *
     * @return The maximum user ID.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public int getMaxUserId() throws PersistenceException {
        String query = "SELECT MAX(userId) AS maxUserId FROM user";
        SQLConnector connector = SQLConnector.getInstance();
        ResultSet resultSet = connector.selectQuery(query);
        try {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt("maxUserId");
            }
        } catch (SQLException e) {
            throw new PersistenceException("Problem getting max user id: " + e.getMessage(), e);
        }
        return 0;
    }

    /**
     * Deletes a user from the database by their ID.
     *
     * @param userId The ID of the user to delete.
     * @throws PersistenceException if an error occurs during the deletion process.
     */
    @Override
    public void deleteUser(int userId) throws PersistenceException {
        String query = "DELETE FROM user WHERE userId = " + userId;

        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.deleteQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error deleting user: " + e.getMessage(), e);
        }
    }
}
