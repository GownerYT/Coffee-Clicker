package Persistence;

import Business.Entities.Configuration;
import Persistence.Exceptions.PersistenceException;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * The ConfigurationDAO class provides methods to read configuration data from a JSON file.
 */
public class ConfigurationDAO {
    private static final String configPath = "Data/config.json";
    private final Gson gson;
    private final FileReader fileReader;

    /**
     * Constructs a ConfigurationDAO object.
     *
     * @throws PersistenceException if there is an error during the construction process.
     */
    public ConfigurationDAO() throws PersistenceException{
        gson = new Gson();
        try {
            fileReader = new FileReader(configPath);
        } catch (FileNotFoundException e) {
            throw new PersistenceException("Error file not found: " + e.getMessage(), e);
        }
    }

    /**
     * Reads the configuration data from the JSON file.
     *
     * @return the Configuration object containing the configuration data.
     * @throws PersistenceException if there is an error during the reading process.
     */
    public Configuration readConfiguration() throws PersistenceException {
        if (fileReader != null) {
            try {
                return gson.fromJson(fileReader, Configuration.class);
            } catch (Exception e) {
                throw new PersistenceException("Error reading configuration: " + e.getMessage(), e);
            }
        }
        return null;
    }
}
