package Persistence;

import Business.Entities.User;
import Persistence.Exceptions.PersistenceException;

import java.util.List;

/**
 * The UserDAO interface defines methods for interacting with user data stored in a database.
 */
public interface UserDAO {

    /**
     * Inserts a user into the database.
     *
     * @param user The User object to be inserted.
     * @throws PersistenceException if an error occurs during the insertion process.
     */
    void insertUser(User user) throws PersistenceException;

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object corresponding to the specified username.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    User getUserByUsername(String username) throws PersistenceException;

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user to retrieve.
     * @return The User object corresponding to the specified email.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    User getUserByEmail(String email) throws PersistenceException;

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all User objects stored in the database.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    List<User> getAllUsers() throws PersistenceException;

    /**
     * Retrieves the maximum user ID from the database.
     *
     * @return The maximum user ID stored in the database.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    int getMaxUserId() throws PersistenceException;

    /**
     * Deletes a user from the database by their user ID.
     *
     * @param userId The ID of the user to delete.
     * @throws PersistenceException if an error occurs during the deletion process.
     */
    void deleteUser(int userId) throws PersistenceException;
}
