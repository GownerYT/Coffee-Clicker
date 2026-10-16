package Business.Entities;

/**
 * Represents a generator entity with attributes and functionalities.
 */
public class Generator {
    private final String name;
    private final int baseCost;
    private final float baseProduction;
    private final float incrementCost;
    private final String image;

    /**
     * Constructs a Generator object with the specified parameters.
     *
     * @param name           The name of the generator.
     * @param baseCost       The base cost of the generator.
     * @param baseProduction The base production of the generator.
     * @param incrementCost  The increment cost of the generator.
     * @param image          The image associated with the generator.
     */
    public Generator(String name, int baseCost, float baseProduction, float incrementCost, String image) {
        this.name = name;
        this.baseCost = baseCost;
        this.baseProduction = baseProduction;
        this.incrementCost = incrementCost;
        this.image = image;
    }

    /**
     * Gets the name of the generator.
     *
     * @return The name of the generator.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the base cost of the generator.
     *
     * @return The base cost of the generator.
     */
    public int getBaseCost() {
        return baseCost;
    }

    /**
     * Gets the base production of the generator.
     *
     * @return The base production of the generator.
     */
    public float getBaseProduction() {
        return baseProduction;
    }

    /**
     * Gets the increment cost of the generator.
     *
     * @return The increment cost of the generator.
     */
    public float getIncrementCost() {
        return incrementCost;
    }

}
