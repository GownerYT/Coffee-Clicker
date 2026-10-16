package Persistence;

import Business.Entities.Upgrade;
import Persistence.Exceptions.PersistenceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The SQLUpgradeDAO class implements the UpgradeDAO interface for interacting with upgrade data stored in a database.
 */
public class SQLUpgradeDAO implements UpgradeDAO {

    /**
     * Constructs a new SQLUpgradeDAO instance.
     */
    public SQLUpgradeDAO() {
    }

    /**
     * Retrieves all upgrades from the database.
     *
     * @return A list of all Upgrade objects stored in the database.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public List<Upgrade> getAllUpgrades() throws PersistenceException{
        List<Upgrade> upgrades = new ArrayList<>();
        String query = "SELECT * FROM upgrade";
        SQLConnector connector = SQLConnector.getInstance();
        ResultSet resultSet = connector.selectQuery(query);
        try {
            while (resultSet != null && resultSet.next()) {
                Upgrade upgrade = new Upgrade(
                        resultSet.getString("upgradeName"),
                        resultSet.getString("generatorName"),
                        resultSet.getInt("cost"),
                        resultSet.getFloat("productionMultiplier")
                );
                upgrades.add(upgrade);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Problem getting all upgrades: " + e.getMessage(), e);
        }
        return upgrades;
    }
}
