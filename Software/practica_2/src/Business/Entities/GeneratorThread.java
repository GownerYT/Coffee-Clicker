package Business.Entities;

/**
 * Represents a thread responsible for generating resources in the game.
 */
public class GeneratorThread implements Runnable {
    private final Game game;
    private final Generator generator;
    private boolean running;

    /**
     * Constructs a GeneratorThread object with the specified game and generator.
     *
     * @param game      The game associated with the thread.
     * @param generator The generator for which the thread is responsible.
     */
    public GeneratorThread(Game game, Generator generator) {
        this.game = game;
        this.generator = generator;
        this.running = true;
    }

    /**
     * Calculates the production multiplier for the generator based on upgrades.
     *
     * @return The production multiplier.
     */
    private float calculateProductionMultiplier() {
        float multiplier = 1.0f;
        Upgrade upgrade = game.getUpgradeByName(generator.getName());

        if (upgrade != null) {
            int upgradeCount = game.getUpgradeCountByName(upgrade.getUpgradeName());
            multiplier = upgrade.getProductionMultiplier() * upgradeCount;
        }
        return multiplier;
    }

    /**
     * Executes the thread's logic for generating resources.
     */
    @Override
    public void run() {
        while (running) {
            try {
                game.getSemaphore().acquire();
                float multiplier = calculateProductionMultiplier();
                int production = (int) (generator.getBaseProduction() * multiplier);
                game.setCafesAvailable(game.getCafesAvailable() + production);
                game.getSemaphore().release();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }

    /**
     * Stops the execution of the thread.
     */
    public void stopThread() {
        running = false;
        game.getSemaphore().release();
    }
}
