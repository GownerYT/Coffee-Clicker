package Business.Entities;

/**
 * Represents statistics associated with a user's game.
 */
public class Statistics {
    private final int userId;
    private final String username;
    private final int gameId;
    private final String nameGame;
    private final int coffeesAvailable;
    private final int time;

    /**
     * Constructs a Statistics object with the specified parameters.
     *
     * @param userId           The ID of the user.
     * @param username         The username of the user.
     * @param gameId           The ID of the game.
     * @param nameGame         The name of the game.
     * @param coffeesAvailable The number of coffees available in the game.
     * @param time             The time spent in the game.
     */
    public Statistics(int userId, String username, int gameId, String nameGame, int coffeesAvailable, int time) {
        this.userId = userId;
        this.username = username;
        this.gameId = gameId;
        this.nameGame = nameGame;
        this.coffeesAvailable = coffeesAvailable;
        this.time = time;
    }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the ID of the game.
     *
     * @return The ID of the game.
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Gets the name of the game.
     *
     * @return The name of the game.
     */
    public String getNameGame() {
        return nameGame;
    }

    /**
     * Gets the number of coffees available in the game.
     *
     * @return The number of coffees available.
     */
    public int getCoffeesAvailable() {
        return coffeesAvailable;
    }

    /**
     * Gets the time spent in the game.
     *
     * @return The time spent in the game.
     */
    public int getTime() {
        return time;
    }
}
