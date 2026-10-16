package Business.Managers;

import Business.Entities.Game;
import Business.Entities.User;
import Business.Exceptions.BusinessException;
import Persistence.Exceptions.PersistenceException;
import Persistence.SQLGameDAO;
import Persistence.SQLStatisticsDAO;
import Persistence.SQLUserDAO;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manages user-related operations such as user login, registration, verification, and database interactions.
 */
public class UserManager {
    private final SQLUserDAO sqlUserDAO;
    private final SQLGameDAO sqlGameDAO;
    private final SQLStatisticsDAO sqlStatisticsDAO;
    private int userId;
    private String username;

    public enum Status {
        SUCCESS,
        USER_NOT_FOUND,
        INCORRECT_USERNAME,
        INCORRECT_EMAIL,
        INCORRECT_PASSWORD,
        INCORRECT_MATCH_PASSWORD
    }

    /**
     * Constructs a new UserManager object with the specified SQL data access objects.
     *
     * @param sqlUserDAO       The SQL data access object for user entities.
     * @param sqlGameDAO       The SQL data access object for game entities.
     * @param sqlStatisticsDAO The SQL data access object for statistics entities.
     */
    public UserManager(SQLUserDAO sqlUserDAO, SQLGameDAO sqlGameDAO, SQLStatisticsDAO sqlStatisticsDAO) {
        this.sqlUserDAO = sqlUserDAO;
        this.sqlGameDAO = sqlGameDAO;
        this.sqlStatisticsDAO = sqlStatisticsDAO;
        this.userId = -1;
    }

    /**
     * Attempts to log in a user with the provided username or email and password.
     *
     * @param usernameOrEmail The username or email of the user trying to log in.
     * @param password         The password associated with the user's account.
     * @return A status indicating the result of the login attempt.
     * @throws BusinessException if an error occurs during the login process.
     */
    public Status loginUser(String usernameOrEmail, String password) throws BusinessException {
        try {
            User user = getUserByUsername(usernameOrEmail);
            if (user == null) {
                user = getUserByEmail(usernameOrEmail);
                if (user == null) {
                    return Status.USER_NOT_FOUND;
                }
            }
            if (!user.getPassword().equals(password)) {
                return Status.INCORRECT_PASSWORD;
            }
            userId = user.getUserId();
            username = user.getUsername();

            return Status.SUCCESS;
        } catch (Exception e) {
            throw new BusinessException("Error logging in user: " + e.getMessage(), e);
        }
    }

    /**
     * Registers a new user with the provided username, email, and password.
     *
     * @param username       The username chosen by the user.
     * @param email          The email address provided by the user.
     * @param firstPassword  The password chosen by the user.
     * @param secondPassword The confirmation of the password chosen by the user.
     * @return A status indicating the result of the registration attempt.
     * @throws BusinessException if an error occurs during the registration process.
     * @throws PersistenceException if an error occurs while interacting with the database.
     */
    public Status registerUser(String username, String email, String firstPassword, String secondPassword) throws BusinessException, PersistenceException {
        if (!verifyNameUser(username)) {
            return Status.INCORRECT_USERNAME;
        }

        if (!verifyEmailUser(email)) {
            return Status.INCORRECT_EMAIL;
        }

        if (!verifyPasswordUser(firstPassword)) {
            return Status.INCORRECT_PASSWORD;
        }

        if (!verifyTwoPasswordUser(firstPassword, secondPassword)) {
            return Status.INCORRECT_MATCH_PASSWORD;
        }

        int maxUserId = getMaxUserId();
        int newUserId = maxUserId + 1;
        userId = newUserId;
        User newUser = new User(newUserId, username, email, firstPassword);
        insertUser(newUser);

        return Status.SUCCESS;
    }

    /**
     * Verifies if the given username is available (not already taken by another user).
     *
     * @param username The username to verify.
     * @return True if the username is available; false otherwise.
     * @throws BusinessException if an error occurs during the verification process.
     */
    public boolean verifyNameUser(String username) throws BusinessException {
        try {
            List<User> allUsers = getAllUsers();
            for (User user : allUsers) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            throw new BusinessException("Error verifying username: " + e.getMessage(), e);
        }
    }

    /**
     * Verifies if the given email address is valid and not already associated with another user.
     *
     * @param email The email address to verify.
     * @return True if the email address is valid and available; false otherwise.
     * @throws BusinessException if an error occurs during the verification process.
     */
    public boolean verifyEmailUser(String email) throws BusinessException {
        try {
            String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);

            if (!matcher.matches()) {
                return false;
            }

            List<User> allUsers = getAllUsers();
            for (User user : allUsers) {
                if (user.getEmail().equalsIgnoreCase(email)) {
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            throw new BusinessException("Error verifying email: " + e.getMessage(), e);
        }
    }

    /**
     * Verifies if the given password meets the password requirements.
     *
     * @param password The password to verify.
     * @return True if the password meets the requirements; false otherwise.
     */
    public boolean verifyPasswordUser(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&+\\-^¡¿=#|/]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * Verifies if the two provided passwords match.
     *
     * @param password        The first password.
     * @param confirmPassword The second password for confirmation.
     * @return True if the passwords match; false otherwise.
     */
    public boolean verifyTwoPasswordUser(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    /**
     * Retrieves the user ID of the currently logged-in user.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Retrieves the username of the currently logged-in user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Inserts a new user into the database.
     *
     * @param user The user to insert.
     * @throws BusinessException if an error occurs during the insertion process.
     */
    public void insertUser(User user) throws BusinessException {
        try {
            sqlUserDAO.insertUser(user);
        } catch (PersistenceException e) {
            throw new BusinessException("Error inserting user: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a user from the database by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The user object if found, null otherwise.
     * @throws BusinessException if an error occurs during the retrieval process.
     */
    public User getUserByUsername(String username) throws BusinessException {
        try {
            return sqlUserDAO.getUserByUsername(username);
        } catch (PersistenceException e) {
            throw new BusinessException("Error getting user by username", e);
        }
    }

    /**
     * Retrieves a user from the database by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The user object if found, null otherwise.
     * @throws BusinessException if an error occurs during the retrieval process.
     */
    public User getUserByEmail(String email) throws BusinessException {
        try {
            return sqlUserDAO.getUserByEmail(email);
        } catch (PersistenceException e) {
            throw new BusinessException("Error getting user by email", e);
        }
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all users.
     * @throws BusinessException if an error occurs during the retrieval process.
     */
    public List<User> getAllUsers() throws BusinessException {
        try {
            return sqlUserDAO.getAllUsers();
        } catch (PersistenceException e) {
            throw new BusinessException("Error getting all users", e);
        }
    }

    /**
     * Retrieves the maximum user ID from the database.
     *
     * @return The maximum user ID.
     * @throws BusinessException if an error occurs during the retrieval process.
     */
    public int getMaxUserId() throws BusinessException {
        try {
            return sqlUserDAO.getMaxUserId();
        } catch (PersistenceException e) {
            throw new BusinessException("Error getting max user ID", e);
        }
    }

    /**
     * Deletes the currently logged-in user from the database, along with associated game data and statistics.
     *
     * @throws BusinessException if an error occurs during the deletion process.
     */
    public void deleteUser() throws BusinessException {
        try {
            Game game = sqlGameDAO.getGameByUserId(userId);
            sqlStatisticsDAO.deleteStatisticsByUser(userId);
            if (game != null) {
                sqlGameDAO.deleteGame(game.getGameId());
            }
            sqlUserDAO.deleteUser(userId);

        } catch (PersistenceException e) {
            throw new BusinessException("Error deleting user", e);
        }
    }

    /**
     * Logs out the currently logged-in user by resetting the user ID to zero.
     * After calling this method, the user will be considered logged out.
     */
    public void logoutUser() {
        this.userId = 0;
    }
}

