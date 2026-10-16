package Business.Entities;

/**
 * Represents a user entity with basic information such as user ID, username, email, and password.
 */
public class User {
    private final int userId;
    private final String username;
    private final String email;
    private final String password;

    /**
     * Constructs a new User object with the specified attributes.
     *
     * @param userId   The unique identifier for the user.
     * @param username The username of the user.
     * @param email    The email address of the user.
     * @param password The password associated with the user's account.
     */
    public User(int userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the user ID.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password associated with the user's account.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
}
