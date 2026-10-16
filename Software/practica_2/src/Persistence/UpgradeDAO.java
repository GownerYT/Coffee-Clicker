package Persistence;

import Business.Entities.Upgrade;
import Persistence.Exceptions.PersistenceException;

import java.util.List;

/**
 * The UpgradeDAO interface defines methods for interacting with upgrade data stored in a database.
 */
public interface UpgradeDAO {

    /**
     * Retrieves all upgrades from the database.
     *
     * @return A list of all Upgrade objects stored in the database.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    List<Upgrade> getAllUpgrades() throws PersistenceException;

}
