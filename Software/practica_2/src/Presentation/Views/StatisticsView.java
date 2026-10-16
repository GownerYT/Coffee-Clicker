package Presentation.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

/**
 * StatisticsView class represents the user interface for displaying statistics in the Coffee Clicker game.
 * This class extends JPanel and includes options to view user-specific statistics and game-specific statistics.
 */
public class StatisticsView extends JPanel {

    private static JButton backButton;
    private static JButton accountButton;
    private static JLabel title;
    private static JComboBox<String> userDropdown;
    private static JComboBox<String> gameDropdown;
    public static final String STATICS_BACk = "statics_statics", STATICS_ACCOUNT = "statics_account", STATICS_USER_DROPDOWN = "statics_user_dropdown", STATICS_GAME_DROPDOWN = "statics_game_dropdown";
    private JPanel rightPanel;

    /**
     * Constructs a new StatisticsView object.
     * Initializes the components and sets up the layout of the statistics view.
     */
    public StatisticsView() {
        // DEFINE ATTRIBUTES
        backButton = new JButton();
        accountButton = new JButton("Account");
        title = new JLabel("STATISTICS");
        userDropdown = new JComboBox<>();
        gameDropdown = new JComboBox<>();
        rightPanel = new JPanel();

        setLayout(new BorderLayout());
        String imagePath = "Images/CoffeeClicker_Background.jpg";
        ConfigImage backgroundImage = new ConfigImage(imagePath);
        add(backgroundImage, BorderLayout.CENTER);

        // BACK ARROW ICON
        String backArrowPath = "Images/CoffeeClicker_BackArrow.png";
        ImageIcon backArrowIcon = new ImageIcon(backArrowPath);
        Image scaledBackArrowImage = backArrowIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        backArrowIcon = new ImageIcon(scaledBackArrowImage);
        backButton = createBackButton(backArrowIcon);
        backgroundImage.add(backButton, BorderLayout.NORTH);

        // WHITE RECTANGLE
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(new EmptyBorder(70, 10, 50, 10));
        setTransparent(container);
        JPanel square = new JPanel();
        square.setLayout(new GridBagLayout());
        square.setPreferredSize(new Dimension(800, 500));
        square.setBackground(Color.white);

        // SPLIT RECTANGLE
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5; // (1/4)
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        square.add(createLeftPanel(), gbc);
        gbc.gridx = 1;
        gbc.weightx = 2.0; // (3/4)
        square.add(createRightPanel(), gbc);

        container.add(square, BorderLayout.CENTER);
        backgroundImage.add(container, BorderLayout.CENTER);
    }

    /**
     * Creates and returns the left panel containing user selection and account button.
     * @return A JPanel instance representing the left panel.
     */
    private static JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setLayout(new GridLayout(8, 1));

        // TITLE "STATISTICS"
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(60, 10, 10, 0));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalGlue());
        leftPanel.add(titlePanel);

        // BLANK SPACE
        JPanel blankPanel1 = new JPanel();
        blankPanel1.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(blankPanel1);

        // DROPDOWN USER
        String[] user = {""};
        userDropdown = new JComboBox<>(user);
        userDropdown.setFont(new Font("Arial", Font.PLAIN, 12));
        userDropdown.setOpaque(false);
        userDropdown.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.add(userDropdown);

        // BLANK SPACE
        JPanel blankPanel2 = new JPanel();
        blankPanel2.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(blankPanel2);

        // DROPDOWN GAME
        String[] game = {""};
        gameDropdown = new JComboBox<>(game);
        gameDropdown.setFont(new Font("Arial", Font.PLAIN, 12));
        gameDropdown.setOpaque(false);
        gameDropdown.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.add(gameDropdown);

        // BLANK SPACE
        JPanel blankPanel3 = new JPanel();
        blankPanel3.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(blankPanel3);

        // BUTTON
        accountButton = new JButton("Account");
        accountButton.setFont(new Font("Arial", Font.PLAIN, 12));
        Dimension buttonSize = new Dimension(100, 30);
        accountButton.setPreferredSize(buttonSize);
        JPanel accountPanel = new JPanel();
        accountPanel.setBackground(Color.LIGHT_GRAY);
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.X_AXIS));
        accountPanel.add(Box.createHorizontalGlue());
        accountPanel.add(accountButton);
        accountPanel.add(Box.createHorizontalGlue());
        accountButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        leftPanel.add(accountPanel);

        return leftPanel;
    }

    /**
     * Creates and returns a right panel for the graphical interface.
     * This panel is used for certain drawing operations.
     *
     * @return The JPanel created for the right panel.
     */
    private JPanel createRightPanel() {
        rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        return rightPanel;
    }

    /**
     * Updates the line chart on the right panel with provided time and coffees data.
     * @param timeData Array representing time data.
     * @param coffeesData Array representing coffees data.
     */
    public void updateLineChart(int[] timeData, int[] coffeesData) {
        Graphics2D g2d = (Graphics2D) rightPanel.getGraphics();

        int panelWidth = rightPanel.getWidth();
        int panelHeight = rightPanel.getHeight();
        int padding = 80;

        if (timeData.length == 0 || coffeesData.length == 0) {
            g2d.clearRect(0, 0, panelWidth, panelHeight);
            return;
        }
        int maxTime = Arrays.stream(timeData).max().orElse(0);
        int maxCoffees = Arrays.stream(coffeesData).max().orElse(0);

        g2d.clearRect(0, 0, panelWidth, panelHeight);

        int maxYLabel;
        int yLabelIncrement = 1;
        while ((maxCoffees / yLabelIncrement) > 10) {
            yLabelIncrement *= 10;
        }
        maxYLabel = ((maxCoffees / yLabelIncrement) + 1) * yLabelIncrement;

        for (int i = 0; i < timeData.length; i++) {
            int x = map(timeData[i], maxTime, padding, panelWidth - padding);
            int y = map(coffeesData[i], maxYLabel, panelHeight - padding, padding);

            g2d.drawString(String.valueOf(timeData[i]), x, panelHeight - 50);
            g2d.fillOval(x - 2, y - 2, 4, 4);

            if (i > 0) {
                int xPrev = map(timeData[i - 1], maxTime, padding, panelWidth - padding);
                int yPrev = map(coffeesData[i - 1], maxYLabel, panelHeight - padding, padding);

                g2d.setColor(Color.BLUE);
                g2d.drawLine(xPrev, yPrev, x, y);
                g2d.setColor(Color.BLACK);
            }
        }

        g2d.setColor(Color.BLACK);
        g2d.drawLine(padding, panelHeight - padding, panelWidth - padding, panelHeight - padding);
        g2d.drawLine(padding, padding, padding, panelHeight - padding);

        String labelX = "Time (min)";
        String labelY = "Number of coffees";

        int labelXWidth = g2d.getFontMetrics().stringWidth(labelX);
        int labelXPosition = (panelWidth - labelXWidth) / 2;
        g2d.drawString(labelX, labelXPosition, panelHeight - 20);

        Font oldFont = g2d.getFont();
        Font rotatedFont = oldFont.deriveFont(AffineTransform.getRotateInstance(-Math.PI / 2));
        g2d.setFont(rotatedFont);

        int max = (maxYLabel / yLabelIncrement);
        for (int i = 0; i <= max ; i++) {
            String yLabel = String.valueOf(i * yLabelIncrement);
            int labelYHeight = g2d.getFontMetrics().stringWidth(yLabel);
            int labelYPosition = map(i * yLabelIncrement, maxYLabel, panelHeight - padding, padding) + labelYHeight / 2;
            g2d.drawString(yLabel, 60, labelYPosition);

            g2d.setColor(new Color(0, 0, 0, 50));
            g2d.drawLine(padding, labelYPosition, panelWidth - padding, labelYPosition);
            g2d.setColor(Color.BLACK);
        }

        int labelYHeight = g2d.getFontMetrics().stringWidth(labelY);
        int labelYPosition = (panelHeight + labelYHeight) / 2;
        g2d.drawString(labelY, 25, labelYPosition + 50);

        g2d.setFont(oldFont);
    }

    /**
     * Maps a value from one range to another.
     *
     * @param value    The value to be mapped.
     * @param fromHigh The high end of the original range.
     * @param toLow    The low end of the target range.
     * @param toHigh   The high end of the target range.
     * @return The mapped value.
     */
    private int map(int value, int fromHigh, int toLow, int toHigh) {
        return (value) * (toHigh - toLow) / (fromHigh) + toLow;
    }

    /**
     * Sets the action listener for the user dropdown.
     * @param actionListener The ActionListener to be set for the user dropdown.
     */
    public void userDropdownController(ActionListener actionListener) {
        userDropdown.setActionCommand(STATICS_USER_DROPDOWN);
        userDropdown.addActionListener(actionListener);
    }

    /**
     * Retrieves the selected user from the user dropdown.
     * @return The selected user as a String.
     */
    public String getSelectedUser() {
        return (String) userDropdown.getSelectedItem();
    }

    /**
     * Sets the action listener for the game dropdown.
     * @param actionListener The ActionListener to be set for the game dropdown.
     */
    public void gameDropdownController(ActionListener actionListener) {
        gameDropdown.setActionCommand(STATICS_GAME_DROPDOWN);
        gameDropdown.addActionListener(actionListener);
    }

    /**
     * Retrieves the selected game from the game dropdown.
     * @return The selected game as a String.
     */
    public String getSelectedGame() {
        return (String) gameDropdown.getSelectedItem();
    }

    /**
     * Updates the user dropdown with the provided array of users.
     * @param users Array of usernames.
     */
    public void updateUsersDropdown(String[] users) {
        userDropdown.removeAllItems();
        if (users.length > 0) {
            for (String user : users) {
                userDropdown.addItem(user);
            }
        } else {
            userDropdown.addItem("No users available");
        }
    }

    /**
     * Updates the game dropdown with the provided array of games.
     * @param games Array of game names.
     */
    public void updateGameDropdown(String[] games) {
        gameDropdown.removeAllItems();
        if (games.length > 0) {
            for (String game : games) {
                gameDropdown.addItem(game);
            }
        } else {
            gameDropdown.addItem("No games available");
        }
    }

    /**
     * Creates and returns a back button with the provided arrow icon.
     * @param backArrowIcon The ImageIcon representing the back arrow icon.
     * @return A JButton instance representing the back button.
     */
    private static JButton createBackButton(ImageIcon backArrowIcon) {
        JButton backButton = new JButton();
        backButton.setIcon(backArrowIcon);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        Insets margin = new Insets(10, 0, 550, 10);
        backButton.setMargin(margin);

        return backButton;
    }

    /**
     * Sets the provided component to be transparent.
     * @param component The component to be set transparent.
     */
    private void setTransparent(JComponent component) {
        component.setOpaque(false);
        component.setBackground(new Color(0, 0, 0, 0));

        if (component instanceof JPanel) {
            for (Component child : component.getComponents()) {
                setTransparent((JComponent) child);
            }
        }
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
     * Sets the action listener for the back button.
     * @param actionListener The ActionListener to be set for the back button.
     */
    public void backController(ActionListener actionListener) {
        backButton.setActionCommand(STATICS_BACk);
        backButton.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the account button.
     * @param actionListener The ActionListener to be set for the account button.
     */
    public void accountController(ActionListener actionListener) {
        accountButton.setActionCommand(STATICS_ACCOUNT);
        accountButton.addActionListener(actionListener);
    }
}