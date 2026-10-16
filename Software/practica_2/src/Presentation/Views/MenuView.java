package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * MenuView class represents the user interface for the main menu of the Coffee Clicker game.
 * This class extends JPanel and contains buttons for starting a new game, loading a saved game, accessing statistics, and managing the account.
 */
public class MenuView extends JPanel {
    private final JButton newGameButton;
    private final JButton loadGameButton;
    private final JButton staticsButton;
    private final JButton accountButton;
    public static final String MENU_NEW_GAME = "menu_new_game", MENU_LOAD_GAME = "menu_load_game", MENU_STATICS = "menu_statics", MENU_ACCOUNT = "menu_account";

    /**
     * Constructs an instance of MenuView.
     */
    public MenuView() {
        // DEFINE ATTRIBUTES
        newGameButton = new JButton("New Game");
        loadGameButton = new JButton("Load Game");
        staticsButton = new JButton("Statistics");
        accountButton = new JButton("Account");
        JLabel title = new JLabel("<html><u> COFFEE CLICKER</u></html>");

        setLayout(new BorderLayout());
        String imagePath = "Images/CoffeeClicker_Menu.jpg";
        ConfigImage backgroundImage = new ConfigImage(imagePath);
        add(backgroundImage);

        // TITLE
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(100, 300, 10, 10));
        Font titleFont = new Font("Arial", Font.BOLD | Font.ITALIC, 60);
        title.setFont(titleFont);
        title.setForeground(Color.WHITE);
        backgroundImage.add(titlePanel);

        // BUTTONS
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setOpaque(false);
        Dimension buttonSize = new Dimension(250, 50);
        newGameButton.setPreferredSize(buttonSize);
        loadGameButton.setPreferredSize(buttonSize);
        staticsButton.setPreferredSize(buttonSize);
        accountButton.setPreferredSize(buttonSize);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(40, 0, 10, 0);
        buttonsPanel.add(newGameButton, constraints);
        constraints.gridy++;
        buttonsPanel.add(loadGameButton, constraints);
        constraints.gridy++;
        buttonsPanel.add(staticsButton, constraints);
        constraints.gridy++;
        buttonsPanel.add(accountButton, constraints);

        // ALIGN BUTTONS
        newGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        loadGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        staticsButton.setHorizontalAlignment(SwingConstants.CENTER);
        accountButton.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(buttonsPanel, BorderLayout.CENTER);
    }

    /**
     * Displays a message requesting the name of the new game.
     * @return The name entered for the new game.
     */
    public String requestNameGame() {
        String windowTitle = "Enter game name";
        String inputMessage = "Please, enter a desired name for the game";
        return JOptionPane.showInputDialog(null, inputMessage, windowTitle, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an error message indicating that the entered game name already exists.
     */
    public void showGameNameExists() {
        String windowTitle = "Game name already exists";
        String errorMessage = "The game name you entered already exists. Please choose a different name.";
        JOptionPane.showMessageDialog(null, errorMessage, windowTitle, JOptionPane.ERROR_MESSAGE);
    }


    /**
     * Displays a message indicating that a game is in progress.
     */
    public void showGameInProgress() {
        String windowTitle = "Game in progress";
        String errorMessage = "You have a game underway, please load a game";
        JOptionPane.showMessageDialog(null, errorMessage, windowTitle, JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Displays a message indicating that no game is currently in progress.
     */
    public void showNoGameStarted() {
        String windowTitle = "No game in progress";
        String errorMessage = "You don't have any game in progress, please start a new game";
        JOptionPane.showMessageDialog(null, errorMessage, windowTitle, JOptionPane.ERROR_MESSAGE);

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
     * Sets the action listener for the new game button.
     * @param actionListener the action listener to be set.
     */
    public void gameController(ActionListener actionListener) {
        newGameButton.setActionCommand(MENU_NEW_GAME);
        newGameButton.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the load game button.
     * @param actionListener the action listener to be set.
     */
    public void loadGameController(ActionListener actionListener) {
        loadGameButton.setActionCommand(MENU_LOAD_GAME);
        loadGameButton.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the statistics button.
     * @param actionListener the action listener to be set.
     */
    public void statisticsController(ActionListener actionListener) {
        staticsButton.setActionCommand(MENU_STATICS);
        staticsButton.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the account button.
     * @param actionListener the action listener to be set.
     */
    public void accountController(ActionListener actionListener) {
        accountButton.setActionCommand(MENU_ACCOUNT);
        accountButton.addActionListener(actionListener);
    }
}
