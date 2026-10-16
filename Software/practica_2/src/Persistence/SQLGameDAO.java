package Persistence;

import Business.Entities.Game;
import Business.Entities.Generator;
import Business.Entities.Upgrade;
import Persistence.Exceptions.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The SQLGameDAO class implements the GameDAO interface for interacting with the game data stored in a MySQL database.
 */
public class SQLGameDAO implements GameDAO {

    /**
     * Constructs a new SQLGameDAO instance.
     */
    public SQLGameDAO() {
    }

    /**
     * Inserts a new game into the database.
     *
     * @param game The game to be inserted.
     * @throws PersistenceException if an error occurs during the insertion process.
     */
    @Override
    public void createGame(Game game) throws PersistenceException {
        String query = "INSERT INTO game (nameGame, gameId, userId, coffeesAvailable, elapsedTime) VALUES ("
                + "'" + game.getNameGame() + "'" + ", " +
                game.getGameId() + ", " +
                game.getUserId() + ", " +
                game.getCafesAvailable() + ", " +
                game.getElapsedTime()+ ")";
        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.insertQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error creating game: " + e.getMessage(), e);
        }
    }

    /**
     * Inserts a generator for a specific game into the database.
     *
     * @param gameId        The ID of the game.
     * @param generatorName The name of the generator.
     * @throws PersistenceException if an error occurs during the insertion process.
     */
    @Override
    public void insertGameGenerators(int gameId, String generatorName) throws PersistenceException {
        String query = "INSERT INTO gamegenerators (gameId, generatorName) VALUES ("
                + gameId + ", '"
                + generatorName + "')";
        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.insertQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error inserting game generator: " + e.getMessage(), e);
        }
    }

    /**
     * Inserts an upgrade for a specific game into the database.
     *
     * @param gameId      The ID of the game.
     * @param upgradeName The name of the upgrade.
     * @throws PersistenceException if an error occurs during the insertion process.
     */
    @Override
    public void insertGameUpgrades(int gameId, String upgradeName) throws PersistenceException {
        String query = "INSERT INTO gameupgrades (gameId, upgradeName) VALUES ("
                + gameId + ", '"
                + upgradeName + "')";
        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.insertQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error inserting game upgrade: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a game by its ID from the database.
     *
     * @param gameId The ID of the game to retrieve.
     * @return The Game object if found, otherwise null.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public Game getGameById(int gameId) throws PersistenceException {
        String query = "SELECT * FROM game WHERE gameId = " + gameId;
        SQLConnector connector = SQLConnector.getInstance();
        ResultSet resultSet = connector.selectQuery(query);
        try {
            if (resultSet != null && resultSet.next()) {
                return new Game(
                        resultSet.getString("nameGame"),
                        resultSet.getInt("gameId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("coffeesAvailable"),
                        resultSet.getInt("elapsedTime")
                );
            }
        } catch (SQLException e) {
            throw new PersistenceException("Problem getting game by id: " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * Retrieves a game by its user ID from the database.
     *
     * @param userId The ID of the user whose game to retrieve.
     * @return The Game object if found, otherwise null.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public Game getGameByUserId(int userId) throws PersistenceException {
        String query = "SELECT * FROM game WHERE userId = " + userId;
        SQLConnector connector = SQLConnector.getInstance();
        ResultSet resultSet = connector.selectQuery(query);
        try {
            if (resultSet != null && resultSet.next()) {
                return new Game(
                        resultSet.getString("nameGame"),
                        resultSet.getInt("gameId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("coffeesAvailable"),
                        resultSet.getInt("elapsedTime")
                );
            }
        } catch (SQLException e) {
            throw new PersistenceException("Problem getting game by user id: " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * Retrieves the maximum game ID from the database.
     *
     * @return The maximum game ID.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public int getMaxGameId() throws PersistenceException {
        String query = "SELECT MAX(id) as max_id FROM (" +
                "SELECT COALESCE(MAX(gameId), 0) as id FROM game " +
                "UNION ALL " +
                "SELECT COALESCE(MAX(gameId), 0) as id FROM statistics" +
                ") as max_ids";
        SQLConnector connector = SQLConnector.getInstance();
        ResultSet resultSet = connector.selectQuery(query);
        try {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            throw new PersistenceException("Problem getting max game id: " + e.getMessage(), e);
        }
        return 0;
    }

    /**
     * Retrieves all generators associated with a specific game from the database.
     *
     * @param gameId The ID of the game.
     * @return A list of Generator objects.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public List<Generator> getAllGeneratorsByGameId(int gameId) throws PersistenceException {
        List<Generator> generators = new ArrayList<>();
        String query = "SELECT g.* " +
                "FROM generator AS g " +
                "JOIN gamegenerators AS gg ON g.name = gg.generatorName " +
                "WHERE gg.gameId = " + gameId;
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
            throw new PersistenceException("Problem getting all generators by gameId: " + e.getMessage(), e);
        }
        return generators;
    }


    /**
     * Retrieves all upgrades associated with a specific game from the database.
     *
     * @param gameId The ID of the game.
     * @return A list of Upgrade objects.
     * @throws PersistenceException if an error occurs during the retrieval process.
     */
    @Override
    public List<Upgrade> getAllUpgradesByGameId(int gameId) throws PersistenceException {
        List<Upgrade> upgrades = new ArrayList<>();
        String query = "SELECT u.* " +
                "FROM upgrade AS u " +
                "JOIN gameupgrades AS gu ON u.upgradeName = gu.upgradeName " +
                "WHERE gu.gameId = " + gameId;
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
            throw new PersistenceException("Problem getting all upgrades by gameId: " + e.getMessage(), e);
        }
        return upgrades;
    }

    /**
     * Updates a game record in the database.
     *
     * @param game The Game object containing updated information.
     * @throws PersistenceException if an error occurs during the update process.
     */
    @Override
    public void updateGame(Game game) throws PersistenceException {
        String query = "UPDATE game SET " + "nameGame = " + "'" + game.getNameGame() + "'"
                + ", userId = " + game.getUserId()
                + ", coffeesAvailable = " + game.getCafesAvailable()
                + ", elapsedTime = " + game.getElapsedTime()
                + " WHERE gameId = " + game.getGameId();
        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.updateQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error updating game: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes generator records associated with a specific game from the database.
     *
     * @param gameId The ID of the game.
     * @throws PersistenceException if an error occurs during the deletion process.
     */
    public void deleteGameGenerators(int gameId) throws PersistenceException {
        String query = "DELETE FROM gamegenerators WHERE gameId = " + gameId;
        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.deleteQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error deleting game generators: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes upgrade records associated with a specific game from the database.
     *
     * @param gameId The ID of the game.
     * @throws PersistenceException if an error occurs during the deletion process.
     */
    public void deleteGameUpgrades(int gameId) throws PersistenceException {
        String query = "DELETE FROM gameupgrades WHERE gameId = " + gameId;
        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.deleteQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error deleting game upgrades: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a game record from the database.
     *
     * @param gameId The ID of the game to delete.
     * @throws PersistenceException if an error occurs during the deletion process.
     */
    @Override
    public void deleteGame(int gameId) throws PersistenceException {
        deleteGameGenerators(gameId);
        deleteGameUpgrades(gameId);

        String query = "DELETE FROM game WHERE gameId = " + gameId;
        try {
            SQLConnector connector = SQLConnector.getInstance();
            connector.deleteQuery(query);
        } catch (Exception e) {
            throw new PersistenceException("Error deleting game: " + e.getMessage(), e);
        }
    }
}
