package Business.Entities;

/**
 * Represents an upgrade that can be applied to a generator in the game.
 */
public class Upgrade {
    private final String upgradeName;
    private final String generatorName;
    private final int cost;
    private final float productionMultiplier;

    /**
     * Constructs an Upgrade object with the specified parameters.
     *
     * @param upgradeName          The name of the upgrade.
     * @param generatorName        The name of the generator to which the upgrade applies.
     * @param cost                 The cost of the upgrade.
     * @param productionMultiplier The production multiplier applied by the upgrade.
     */
    public Upgrade(String upgradeName, String generatorName, int cost, float productionMultiplier) {
        this.upgradeName  = upgradeName ;
        this.generatorName = generatorName ;
        this.cost = cost;
        this.productionMultiplier = productionMultiplier;
    }

    /**
     * Gets the name of the upgrade.
     *
     * @return The name of the upgrade.
     */
    public String getUpgradeName() {
        return upgradeName;
    }

    /**
     * Gets the name of the generator to which the upgrade applies.
     *
     * @return The name of the generator.
     */
    public String getGeneratorName() {
        return generatorName;
    }

    /**
     * Gets the cost of the upgrade.
     *
     * @return The cost of the upgrade.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets the production multiplier applied by the upgrade.
     *
     * @return The production multiplier.
     */
    public float getProductionMultiplier() {
        return productionMultiplier;
    }
}

