package Business.Managers;

import Persistence.SQLUpgradeDAO;

/**
 * Manages operations related to upgrades, including creation, retrieval, updating, and deletion.
 * This class interacts with the data access object (DAO) for upgrade operations.
 */
public class UpgradeManager {
    private final SQLUpgradeDAO sqlUpgradeDAO;

    /**
     * Constructor for the UpgradeManager class.
     *
     * @param sqlUpgradeDAO The data access object for upgrade operations.
     */
    public UpgradeManager(SQLUpgradeDAO sqlUpgradeDAO) {
        this.sqlUpgradeDAO = sqlUpgradeDAO;
    }

}
