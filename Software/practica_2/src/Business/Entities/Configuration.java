package Business.Entities;

/**
 * Represents the configuration parameters for a database connection.
 */
public class Configuration {
    private final int databasePort;
    private final String databaseIP;
    private final String databaseName;
    private final String databaseUser;
    private final String databasePassword;

    /**
     * Constructs a Configuration object with the specified parameters.
     *
     * @param databasePort     The port number of the database server.
     * @param databaseIP       The IP address of the database server.
     * @param databaseName     The name of the database.
     * @param databaseUser     The username for accessing the database.
     * @param databasePassword The password for accessing the database.
     */
    public Configuration(int databasePort, String databaseIP, String databaseName, String databaseUser, String databasePassword) {
        this.databasePort = databasePort;
        this.databaseIP = databaseIP;
        this.databaseName = databaseName;
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;
    }

    /**
     * Gets the port number of the database server.
     *
     * @return The port number.
     */
    public int getDatabasePort() {
        return databasePort;
    }

    /**
     * Gets the IP address of the database server.
     *
     * @return The IP address.
     */
    public String getDatabaseIP() {
        return databaseIP;
    }

    /**
     * Gets the name of the database.
     *
     * @return The database name.
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Gets the username for accessing the database.
     *
     * @return The database username.
     */
    public String getDatabaseUser() {
        return databaseUser;
    }

    /**
     * Gets the password for accessing the database.
     *
     * @return The database password.
     */
    public String getDatabasePassword() {
        return databasePassword;
    }
}
