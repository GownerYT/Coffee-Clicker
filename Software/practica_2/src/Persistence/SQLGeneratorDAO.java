package Persistence;

import Business.Entities.Generator;
import Persistence.Exceptions.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The SQLGeneratorDAO class implements the GeneratorDAO interface for interacting with the generator data stored in a MySQL database.
 */
public class SQLGeneratorDAO implements GeneratorDAO {

    /**
     * Constructs a new SQLGeneratorDAO instance.
     */
    public SQLGeneratorDAO() {
    }

    /**
     * Retrieves all generators from the database.
     *
     * @return A list of Generator objects.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public List<Generator> getAllGenerators() throws PersistenceException {
        List<Generator> generators = new ArrayList<>();
        String query = "SELECT * FROM generator";
        SQLConnector connector = SQLConnector.getInstance();
        ResultSet resultSet = connector.selectQuery(query);
        try {
            while (resultSet != null && resultSet.next()) {
                Generator generator = new Generator(
                        resultSet.getString("name"),
                        resultSet.getInt("baseCost"),
                        resultSet.getFloat("baseProduction"),
                        resultSet.getFloat("incrementCost"),
                        resultSet.getString("image")
                );
                generators.add(generator);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Problem getting all generators: " + e.getMessage(), e);
        }
        return generators;
    }
}
