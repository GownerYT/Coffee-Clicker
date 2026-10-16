package Business.Managers;

import Business.Entities.Generator;
import Business.Entities.Shop;
import Business.Entities.Upgrade;
import Business.Exceptions.BusinessException;
import Persistence.SQLGeneratorDAO;
import Persistence.SQLUpgradeDAO;

import java.util.List;

/**
 * The ShopManager class manages the creation, retrieval, and updating of shops.
 * It interacts with the database through DAOs to access and manipulate shop-related data.
 */
public class ShopManager {
    private final SQLGeneratorDAO sqlGeneratorDAO;
    private final SQLUpgradeDAO sqlUpgradeDAO;
    private final Shop currentShop;

    /**
     * Constructs a ShopManager with the specified DAOs and initializes the current shop.
     *
     * @param sqlGeneratorDAO The Data Access Object for generator entities.
     * @param sqlUpgradeDAO   The Data Access Object for upgrade entities.
     */
    public ShopManager(SQLGeneratorDAO sqlGeneratorDAO, SQLUpgradeDAO sqlUpgradeDAO) throws BusinessException {
        this.sqlGeneratorDAO = sqlGeneratorDAO;
        this.sqlUpgradeDAO = sqlUpgradeDAO;
        this.currentShop = createShop();
    }

    /**
     * Creates a new shop by retrieving all generators and upgrades from the database.
     *
     * @return The newly created shop.
     */
    private Shop createShop() throws BusinessException {
        List<Generator> generators;
        List<Upgrade> upgrades;

        try {
            generators = sqlGeneratorDAO.getAllGenerators();
            upgrades = sqlUpgradeDAO.getAllUpgrades();
        } catch (Exception e) {
            throw new BusinessException("Error creating shop",e);
        }
        return new Shop(generators, upgrades);
    }

    /**
     * Retrieves the current shop managed by the ShopManager.
     *
     * @return The current shop.
     */
    public Shop getCurrentShop() {
        return currentShop;
    }
}
