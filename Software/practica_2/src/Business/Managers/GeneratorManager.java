package Business.Managers;

import Persistence.SQLGeneratorDAO;


/**
 * Manages operations related to generators, including creation, retrieval, updating, and deletion.
 * This class interacts with the data access object (DAO) for generators to perform database operations.
 */
public class GeneratorManager {
    private final SQLGeneratorDAO sqlGeneratorDAO;

    /**
     * Constructor for the GeneratorManager class.
     *
     * @param sqlGeneratorDAO The data access object for generator operations.
     */
    public GeneratorManager(SQLGeneratorDAO sqlGeneratorDAO) {
        this.sqlGeneratorDAO = sqlGeneratorDAO;
    }


}
