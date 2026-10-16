package Presentation.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * GameView class represents the user interface for the Coffee Clicker game.
 * This class extends JPanel to display the game elements, such as the coffee counter, generators table, and shop.
 */
public class GameView extends JPanel {

    private int coffeesAvailable = 0;
    private int countFirstGenerator = 0;
    private int countSecondGenerator = 0;
    private int countThirdGenerator = 0;

    private JTable table;

    private final JLabel coffeeTitle;
    private final JLabel shopTitle;
    private final JLabel coffeeCounterLabel;

    private JLabel nameFirstGeneratorLabel;
    private JLabel costFirstGeneratorLabel;
    private JLabel productionFirstGeneratorLabel;
    private JLabel nameSecondGeneratorLabel;
    private JLabel costSecondGeneratorLabel;
    private JLabel productionSecondGeneratorLabel;
    private JLabel nameThirdGeneratorLabel;
    private JLabel costThirdGeneratorLabel;
    private JLabel productionThirdGeneratorLabel;

    private final JButton buyCoffeeButton;
    private final JButton accountButton;
    private static JButton backButton;
    private final JButton endGameButton;
    private final JButton buyFirstGeneratorButton;
    private final JButton buySecondGeneratorButton;
    private final JButton buyThirdGeneratorButton;
    private JButton upgradeCaveButton;
    private JButton upgradeFarmButton;
    private JButton upgradeFactoryButton;

    public static final String GAME_ACCOUNT = "game_account", GAME_BACk_MENU = "game_back_menu",
                                GAME_END_GAME = "game_end_game";
    public static final String GAME_BUY_FIRST_GENERATOR = "game_buy_first_generator",GAME_BUY_SECOND_GENERATOR = "game_buy_second_generator",
                                GAME_BUY_THIRD_GENERATOR = "game_buy_third_generator", GAME_COFFEE = "game_coffee";
    public static final String  GAME_UPGRADE_CAVE = "game_upgrade_cave", GAME_UPGRADE_FARM = "game_upgrade_farm",
                                GAME_UPGRADE_FACTORY = "game_upgrade_factory";

    /**
     * Constructs an instance of GameView.
     */
    public GameView() {
        // DEFINE ATTRIBUTES
        coffeeTitle = new JLabel("COFFEE CLICKER");
        shopTitle = new JLabel("SHOP");
        coffeeCounterLabel = new JLabel();

        nameFirstGeneratorLabel = new JLabel();
        costFirstGeneratorLabel= new JLabel();
        productionFirstGeneratorLabel= new JLabel();
        nameSecondGeneratorLabel= new JLabel();
        costSecondGeneratorLabel= new JLabel();
        productionSecondGeneratorLabel= new JLabel();
        nameThirdGeneratorLabel= new JLabel();
        costThirdGeneratorLabel= new JLabel();
        productionThirdGeneratorLabel= new JLabel();

        accountButton = new JButton("Account");
        buyCoffeeButton = new JButton("Buy Coffee");
        endGameButton = new JButton("End Game");
        backButton = new JButton("Back Menu");
        buyFirstGeneratorButton = new JButton("Buy");
        buySecondGeneratorButton = new JButton("Buy");
        buyThirdGeneratorButton = new JButton("Buy");
        upgradeCaveButton = new JButton();
        upgradeFarmButton = new JButton();
        upgradeFactoryButton = new JButton();

        table = new JTable();

        setLayout(new GridLayout(1, 3));

        setupLeftPanel();
        setupCenterPanel();
        setupRightPanel();

    }

    /**
     * Sets up the left panel of the game interface.
     */
    private void setupLeftPanel() {
        JPanel leftPanel = new JPanel();
        Color customColor = new Color(210, 210, 210);
        leftPanel.setBackground(customColor);
        leftPanel.setLayout(new GridBagLayout());

        // CONSTRAINTS
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 45, 0);

        // ROW 1: TITLE "COFFEE CLICKER"
        coffeeTitle.setHorizontalAlignment(SwingConstants.CENTER);
        coffeeTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        leftPanel.add(coffeeTitle, gbc);

        // ROW 2: DISPLAY COFFEE COUNTER
        gbc.gridy++;
        coffeeCounterLabel.setText("Coffee Count: " + coffeesAvailable);
        coffeeCounterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(coffeeCounterLabel, gbc);

        // ROW 3: COFFEE CUP IMAGE
        gbc.gridy++;
        String imageCoffeePath = "Images/CoffeeClicker_CoffeeCup.jpg";
        ImageIcon coffeeIcon = new ImageIcon(imageCoffeePath);
        Image scaledCoffeeImage = coffeeIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        coffeeIcon = new ImageIcon(scaledCoffeeImage);
        JLabel coffeeLabel = new JLabel(coffeeIcon);
        coffeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(coffeeLabel, gbc);

        // ROW 4: BUY COFFEE BUTTON
        gbc.gridy++;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(210, 210, 210));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buyCoffeeButton.setPreferredSize(new Dimension(200, 60));

        buttonPanel.add(buyCoffeeButton);
        leftPanel.add(buttonPanel, gbc);

        add(leftPanel);
    }

    /**
     * Sets up the center panel of the game interface.
     */
    private void setupCenterPanel() {
        JPanel centerPanel = new JPanel();
        Color customColor = new Color(200, 200, 200);
        centerPanel.setBackground(customColor);
        centerPanel.setLayout(new GridBagLayout());

        // TITLE
        JLabel titleLabel = new JLabel("GENERATORS TABLE");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 25, 30, 0));
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.gridwidth = 1;
        titleConstraints.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(titleLabel, titleConstraints);

        // DATA TABLE
        String[] rowNames = {"Name", "Alpha", "Beta", "Gamma"};
        Object[][] transposedData = {
                {"Quantity", countFirstGenerator, countSecondGenerator, countThirdGenerator},
                {"Production", "0 c/s", "0 c/s", "0 c/s"},
                {"Total", "0 c/s", "0 c/s", "0 c/s"},
                {"% Global", "0 %", "0 %", "0 %"},
        };

        // CREATE TABLE
        table = new JTable(transposedData, rowNames);
        table.setRowHeight(55);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(130, 245));
        GridBagConstraints tableConstraints = new GridBagConstraints();
        tableConstraints.gridx = 0;
        tableConstraints.gridy = 1;
        tableConstraints.gridwidth = 1;
        tableConstraints.fill = GridBagConstraints.BOTH;
        tableConstraints.insets = new Insets(10, 10, 10, 10);
        centerPanel.add(scrollPane, tableConstraints);

        // BUTTON SIZES
        Dimension buttonSize = new Dimension(150, 40);
        accountButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);
        endGameButton.setPreferredSize(buttonSize);

        // BUTTON CONSTRAINTS
        GridBagConstraints button1Constraints = new GridBagConstraints();
        button1Constraints.gridx = 0;
        button1Constraints.gridy = 2;
        button1Constraints.gridwidth = 1;
        button1Constraints.fill = GridBagConstraints.HORIZONTAL;
        button1Constraints.insets = new Insets(100, 0, 0, 0);
        centerPanel.add(accountButton, button1Constraints);

        GridBagConstraints button2Constraints = new GridBagConstraints();
        button2Constraints.gridx = 0;
        button2Constraints.gridy = 3;
        button2Constraints.gridwidth = 1;
        button2Constraints.fill = GridBagConstraints.HORIZONTAL;
        button2Constraints.insets = new Insets(15, 0, 0, 0);
        centerPanel.add(backButton, button2Constraints);

        GridBagConstraints button3Constraints = new GridBagConstraints();
        button3Constraints.gridx = 0;
        button3Constraints.gridy = 4;
        button3Constraints.gridwidth = 1;
        button3Constraints.fill = GridBagConstraints.HORIZONTAL;
        button3Constraints.insets = new Insets(15, 0, 70, 0);
        centerPanel.add(endGameButton, button3Constraints);

        add(centerPanel);
    }

    /**
     * Sets up the right panel of the game interface.
     */
    private void setupRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        Color customColor = new Color(210, 210, 210);
        rightPanel.setBackground(customColor);

        // CONSTRAINTS
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, -140, 0, 20);

        // ROW 1: TITLE "SHOP"
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        shopTitle.setHorizontalAlignment(SwingConstants.CENTER);
        shopTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        shopTitle.setBorder(BorderFactory.createEmptyBorder(45, 0, 5, 0));
        rightPanel.add(shopTitle, gbc);

        // ROW 2: TEXT "UPGRADES"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JLabel upgradesLabel = new JLabel("Upgrades");
        upgradesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        upgradesLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        rightPanel.add(upgradesLabel, gbc);

        // ROW 3: UPGRADES COLUMNS
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        JPanel row3Panel = new JPanel(new GridLayout(1, 3));
        row3Panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // COLUMN 1: UPGRADECAVE
        String imageUpgradeCavePath = "Images/CoffeeClicker_UpgradeCave.png";
        ImageIcon upgradeCaveIcon = new ImageIcon(imageUpgradeCavePath);
        Image upgradeCaveImage = upgradeCaveIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        upgradeCaveButton = new JButton(new ImageIcon(upgradeCaveImage));
        row3Panel.add(upgradeCaveButton);

        upgradeCaveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upgradeCaveButton.setText("<html><p>Alpha<br>Prod: x2<br>Cost: 2500</p></html>");
                upgradeCaveButton.setIcon(null);
            }
        });

        upgradeCaveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                upgradeCaveButton.setIcon(new ImageIcon(upgradeCaveImage));
                upgradeCaveButton.setText(null);
            }
        });

        // COLUMN 2: UPGRADEFARM
        String imageUpgradeFarmPath = "Images/CoffeeClicker_UpgradeFarm.png";
        ImageIcon upgradeFarmIcon = new ImageIcon(imageUpgradeFarmPath);
        Image upgradeFarmImage = upgradeFarmIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        upgradeFarmButton = new JButton(new ImageIcon(upgradeFarmImage));
        row3Panel.add(upgradeFarmButton);

        upgradeFarmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upgradeFarmButton.setText("<html><p>Beta<br>Prod: x2<br>Cost: 5000</p></html>");
                upgradeFarmButton.setIcon(null);
            }
        });

        upgradeFarmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                upgradeFarmButton.setIcon(new ImageIcon(upgradeFarmImage));
                upgradeFarmButton.setText(null);
            }
        });

        // COLUMN 3: UPGRADEFACTORY
        String imageUpgradeFactoryPath = "Images/CoffeeClicker_UpgradeFactory.png";
        ImageIcon upgradeFactoryIcon = new ImageIcon(imageUpgradeFactoryPath);
        Image upgradeFactoryImage = upgradeFactoryIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        upgradeFactoryButton = new JButton(new ImageIcon(upgradeFactoryImage));
        row3Panel.add(upgradeFactoryButton);

        upgradeFactoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upgradeFactoryButton.setText("<html><p>Gamma<br>Prod: x2<br>Cost: 10000</p></html>");
                upgradeFactoryButton.setIcon(null);
            }
        });

        upgradeFactoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                upgradeFactoryButton.setIcon(new ImageIcon(upgradeFactoryImage));
                upgradeFactoryButton.setText(null);
            }
        });


        rightPanel.add(row3Panel, gbc);

        // ROW 4: TEXT "GENERATORS"
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel generatorsLabel = new JLabel("Generators");
        generatorsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        generatorsLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        rightPanel.add(generatorsLabel, gbc);

        // ROW 5: FIRST GENERATOR
        gbc.gridx = 0;
        gbc.gridy = 4;
        JPanel row5Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // FIRST GENERATOR: IMAGE
        String imageCavePath = "Images/CoffeeClicker_Cave.png";
        ImageIcon generatorIcon = new ImageIcon(imageCavePath);
        Image scaledImage = generatorIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel generatorImage = new JLabel(new ImageIcon(scaledImage));
        row5Panel.add(generatorImage);

        // FIRST GENERATOR: INFO
        JPanel generatorInfoPanel = new JPanel(new GridLayout(3, 1));
        generatorInfoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));

        nameFirstGeneratorLabel = new JLabel("Alpha");
        costFirstGeneratorLabel = new JLabel("Cost: 10 coffee");
        productionFirstGeneratorLabel = new JLabel("Coffee/Second: 1.0 ");
        generatorInfoPanel.add(nameFirstGeneratorLabel);
        generatorInfoPanel.add(costFirstGeneratorLabel);
        generatorInfoPanel.add(productionFirstGeneratorLabel);
        row5Panel.add(generatorInfoPanel);

        // FIRST GENERATOR: BUTTON
        buyFirstGeneratorButton.addActionListener(e -> countFirstGenerator++);
        row5Panel.add(buyFirstGeneratorButton);

        rightPanel.add(row5Panel, gbc);

        // ROW 6: SECOND GENERATOR
        gbc.gridx = 0;
        gbc.gridy = 5;
        JPanel row6Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // SECOND GENERATOR: IMAGE
        String imageFarmPath = "Images/CoffeeClicker_Farm.png";
        ImageIcon generatorIcon2 = new ImageIcon(imageFarmPath);
        Image scaledImage2 = generatorIcon2.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel generatorImage2 = new JLabel(new ImageIcon(scaledImage2));
        row6Panel.add(generatorImage2);

        // SECOND GENERATOR: INFO
        JPanel generatorInfoPanel2 = new JPanel(new GridLayout(3, 1));
        generatorInfoPanel2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        nameSecondGeneratorLabel = new JLabel("Beta");
        costSecondGeneratorLabel = new JLabel("Cost: 300 coffee");
        productionSecondGeneratorLabel = new JLabel("Coffee/Second: 5.0");
        generatorInfoPanel2.add(nameSecondGeneratorLabel);
        generatorInfoPanel2.add(costSecondGeneratorLabel);
        generatorInfoPanel2.add(productionSecondGeneratorLabel);
        row6Panel.add(generatorInfoPanel2);

        // SECOND GENERATOR: BUTTON
        buySecondGeneratorButton.addActionListener(e -> countSecondGenerator++);
        row6Panel.add(buySecondGeneratorButton);

        rightPanel.add(row6Panel, gbc);

        // ROW 7: THIRD GENERATOR
        gbc.gridx = 0;
        gbc.gridy = 6;
        JPanel row7Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // THIRD GENERATOR: IMAGE
        String imageFactoryPath = "Images/CoffeeClicker_Factory.png";
        ImageIcon generatorIcon3 = new ImageIcon(imageFactoryPath);
        Image scaledImage3 = generatorIcon3.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel generatorImage3 = new JLabel(new ImageIcon(scaledImage3));
        row7Panel.add(generatorImage3);

        // THIRD GENERATOR: INFO
        JPanel generatorInfoPanel3 = new JPanel(new GridLayout(3, 1));
        generatorInfoPanel3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        nameThirdGeneratorLabel = new JLabel("Gamma");
        costThirdGeneratorLabel = new JLabel("Cost: 3000 coffee");
        productionThirdGeneratorLabel = new JLabel("Coffee/Second: 15.0");
        generatorInfoPanel3.add(nameThirdGeneratorLabel);
        generatorInfoPanel3.add(costThirdGeneratorLabel);
        generatorInfoPanel3.add(productionThirdGeneratorLabel);
        row7Panel.add(generatorInfoPanel3);

        // THIRD GENERATOR: BUTTON
        buyThirdGeneratorButton.addActionListener(e -> countThirdGenerator++);
        row7Panel.add(buyThirdGeneratorButton);

        rightPanel.add(row7Panel, gbc);

        add(rightPanel);
    }

    /**
     * Resets all the labels in the GameView to their default values.
     * This method updates the upgrade labels and generator labels with default values.
     */
    public void resetAllLabels() {
        updateUpgradeLabels("Alpha", 2500, 2f, 0);
        updateUpgradeLabels("Beta", 5500, 2f, 1);
        updateUpgradeLabels("Gamma", 10000, 2f, 2);

        updateGeneratorLabels("Alpha", 10, 1f, 0);
        updateGeneratorLabels("Beta", 300, 5f, 1);
        updateGeneratorLabels("Gamma", 3000, 15f, 2);
    }


    /**
     * Updates the coffee counter on the graphical user interface.
     *
     * @param coffeesAvailable The number of coffees available.
     */
    public void updateCoffeeCounter(int coffeesAvailable) {
        this.coffeesAvailable = coffeesAvailable;
        coffeeCounterLabel.setText("Coffee Count: " + coffeesAvailable);
    }

    /**
     * Updates the generators table on the graphical user interface.
     *
     * @param generatorCounts            Map containing the counts of each generator type.
     * @param generatorProductions       Map containing the production rates of each generator type.
     * @param totalProductions           Map containing the total production rates of each generator type.
     * @param totalProductionPercentages Map containing the percentage of total production for each generator type.
     */
    public void updateGeneratorsTable(Map<String, Integer> generatorCounts,
                                      Map<String, Integer> generatorProductions,
                                      Map<String, Integer> totalProductions,
                                      Map<String, Integer> totalProductionPercentages) {

        String[] rowNames = {"Name", "Alpha", "Beta", "Gamma"};
        Object[][] transposedData = {
                {"Quantity", generatorCounts.getOrDefault("Alpha", 0),
                        generatorCounts.getOrDefault("Beta", 0),
                        generatorCounts.getOrDefault("Gamma", 0)},
                {"Production", generatorProductions.getOrDefault("Alpha", 0) + "c/s",
                        generatorProductions.getOrDefault("Beta", 0) + "c/s",
                        generatorProductions.getOrDefault("Gamma", 0) + "c/s"},
                {"Total", totalProductions.getOrDefault("Alpha", 0) + "c/s",
                        totalProductions.getOrDefault("Beta", 0) + "c/s",
                        totalProductions.getOrDefault("Gamma", 0) + "c/s"},
                {"% Global", totalProductionPercentages.getOrDefault("Alpha", 0) + "%",
                        totalProductionPercentages.getOrDefault("Beta", 0) + "%",
                        totalProductionPercentages.getOrDefault("Gamma", 0) + "%"},
        };

        DefaultTableModel model = new DefaultTableModel(transposedData, rowNames);
        table.setModel(model);
    }

    /**
     * Sets action listeners for the coffee-related buttons on the GUI.
     *
     * @param actionListener The ActionListener to be added to the coffee buttons.
     */
    public void setCoffeeButtonsListeners(ActionListener actionListener) {
        buyCoffeeButton.setActionCommand(GAME_COFFEE);
        buyCoffeeButton.addActionListener(actionListener);

    }

    /**
     * Updates the labels for upgrade buttons on the GUI.
     *
     * @param upgradeName           The name of the upgrade.
     * @param cost                  The cost of the upgrade.
     * @param productionMultiplier  The production multiplier of the upgrade.
     * @param index                 The index of the upgrade button.
     */
    public void updateUpgradeLabels(String upgradeName, int cost, float productionMultiplier, int index) {
        switch (index) {
            case 0:
                upgradeCaveButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        upgradeCaveButton.setText("<html><p>" + upgradeName + "<br>Prod: x" + productionMultiplier + "<br>Cost: " + cost + "</p></html>");
                        upgradeCaveButton.setIcon(null);
                    }
                });
                break;
            case 1:
                upgradeFarmButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        upgradeFarmButton.setText("<html><p>" + upgradeName + "<br>Prod: x" + productionMultiplier + "<br>Cost: " + cost + "</p></html>");
                        upgradeFarmButton.setIcon(null);
                    }
                });
                break;
            case 2:
                upgradeFactoryButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        upgradeFactoryButton.setText("<html><p>" + upgradeName + "<br>Prod: x" + productionMultiplier + "<br>Cost: " + cost + "</p></html>");
                        upgradeFactoryButton.setIcon(null);
                    }
                });
                break;
            default:
                System.out.println("Invalid Index");
                break;
        }
    }

    /**
     * Sets action listeners for the upgrade buttons on the GUI.
     *
     * @param actionListener The ActionListener to be added to the upgrade buttons.
     */
    public void setUpgradeButtonsListeners(ActionListener actionListener) {
        upgradeCaveButton.setActionCommand(GAME_UPGRADE_CAVE);
        upgradeCaveButton.addActionListener(actionListener);

        upgradeFarmButton.setActionCommand(GAME_UPGRADE_FARM);
        upgradeFarmButton.addActionListener(actionListener);

        upgradeFactoryButton.setActionCommand(GAME_UPGRADE_FACTORY);
        upgradeFactoryButton.addActionListener(actionListener);
    }

    /**
     * Updates the labels for generator information on the GUI.
     *
     * @param name   The name of the generator.
     * @param cost   The cost of the generator.
     * @param production The production rate of the generator.
     * @param index  The index of the generator.
     */
    public void updateGeneratorLabels(String name, int cost, float production, int index) {
        DecimalFormat df = new DecimalFormat("#.##");
        switch (index) {
            case 0:
                nameFirstGeneratorLabel.setText(name);
                costFirstGeneratorLabel.setText("Cost: " + cost + " coffee");
                productionFirstGeneratorLabel.setText("Coffee/Second: " + df.format(production));
                break;
            case 1:
                nameSecondGeneratorLabel.setText(name);
                costSecondGeneratorLabel.setText("Cost: " + cost + " coffee");
                productionSecondGeneratorLabel.setText("Coffee/Second: " + df.format(production));
                break;
            case 2:
                nameThirdGeneratorLabel.setText(name);
                costThirdGeneratorLabel.setText("Cost: " + cost + " coffee");
                productionThirdGeneratorLabel.setText("Coffee/Second: " + df.format(production));
                break;
            default:
                System.out.println("Invalid Index");
                break;
        }
    }

    /**
     * Sets action listeners for the generator buttons on the GUI.
     *
     * @param actionListener The ActionListener to be added to the generator buttons.
     */
    public void setGeneratorsButtonsListeners(ActionListener actionListener) {
        buyFirstGeneratorButton.setActionCommand(GAME_BUY_FIRST_GENERATOR);
        buyFirstGeneratorButton.addActionListener(actionListener);

        buySecondGeneratorButton.setActionCommand(GAME_BUY_SECOND_GENERATOR);
        buySecondGeneratorButton.addActionListener(actionListener);

        buyThirdGeneratorButton.setActionCommand(GAME_BUY_THIRD_GENERATOR);
        buyThirdGeneratorButton.addActionListener(actionListener);
    }

    /**
     * Displays an error message if there is not enough coffee to perform an action.
     */
    public void showBuyingError() {
        String windowTitle = "Not enough coffee!";
        String errorMessage = "You need more coffee to buy this";
        JOptionPane.showMessageDialog(null, errorMessage, windowTitle, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a confirmation dialog to confirm if the user wants to end the game.
     * @return An integer representing the option selected by the user.
     */
    public int showConfirmEndGame() {
        String windowTitle = "Confirm End Game";
        String errorMessage = "\"Are you sure you want to end the game?";
        return JOptionPane.showConfirmDialog(null, errorMessage, windowTitle, JOptionPane.YES_NO_OPTION);
    }

    /**
     * Displays a warning message using a dialog box.
     *
     * @param exceptionMessage The message to display.
     */
    public void showMessage(String exceptionMessage) {
        JOptionPane.showMessageDialog(this, exceptionMessage, "Warning", JOptionPane.WARNING_MESSAGE);
    }


    /**
     * Sets up the ActionListener for the account button.
     * @param actionListener the ActionListener to be added to the account button.
     */
    public void accountController(ActionListener actionListener) {
        accountButton.setActionCommand(GAME_ACCOUNT);
        accountButton.addActionListener(actionListener);
    }

    /**
     * Sets up the ActionListener for the back menu button.
     * @param actionListener the ActionListener to be added to the back menu button.
     */
    public void backMenuController(ActionListener actionListener) {
        backButton.setActionCommand(GAME_BACk_MENU);
        backButton.addActionListener(actionListener);
    }

    /**
     * Sets up the ActionListener for the end game button.
     * @param actionListener the ActionListener to be added to the end game button.
     */
    public void endGameController(ActionListener actionListener) {
        endGameButton.setActionCommand(GAME_END_GAME);
        endGameButton.addActionListener(actionListener);
    }
}
