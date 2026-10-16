package Persistence;

import Business.Entities.Generator;
import Persistence.Exceptions.PersistenceException;

import java.util.List;

/**
 * The GeneratorDAO interface provides methods for accessing and manipulating Generator data in the database.
 */
public interface GeneratorDAO {

    /**
     * Retrieves all Generators from the database.
     *
     * @return A list of all Generator objects in the database.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    List<Generator> getAllGenerators() throws PersistenceException;

}
