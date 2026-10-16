package Business.Entities;

import java.util.List;

/**
 * Represents a shop where players can purchase generators and upgrades.
 */
public class Shop {
    private final List<Generator> availableGenerators;
    private final List<Upgrade> availableUpgrades;

    /**
     * Constructs a Shop object with the specified lists of generators and upgrades.
     *
     * @param generators The list of available generators in the shop.
     * @param upgrades   The list of available upgrades in the shop.
     */
    public Shop(List<Generator> generators, List<Upgrade> upgrades) {
        this.availableGenerators = generators;
        this.availableUpgrades = upgrades;
    }

    /**
     * Gets the list of available generators in the shop.
     *
     * @return The list of available generators.
     */
    public List<Generator> getAvailableGenerators() {
        return availableGenerators;
    }

    /**
     * Gets the list of available upgrades in the shop.
     *
     * @return The list of available upgrades.
     */
    public List<Upgrade> getAvailableUpgrades() {
        return availableUpgrades;
    }

    /**
     * Gets the base production of the generator with the specified name.
     *
     * @param name The name of the generator.
     * @return The base production of the generator.
     */
    public int getGeneratorProductionByName(String name) {
        int production = 0;

        for (Generator generator : availableGenerators) {
            if (generator.getName().equals(name)) {
                production = (int) generator.getBaseProduction();
            }
        }
        return production;
    }

    /**
     * Gets the production multiplier of the upgrade with the specified name.
     *
     * @param name The name of the upgrade.
     * @return The production multiplier of the upgrade.
     */
    public int getUpgradeProductionByName(String name) {
        int production_multiplier = 1;

        for (Upgrade upgrade : availableUpgrades) {
            if (upgrade.getUpgradeName().equals(name)) {
                production_multiplier = (int) upgrade.getProductionMultiplier();
            }
        }
        return production_multiplier;
    }
}

