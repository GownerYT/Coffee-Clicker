package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * HomeView class represents the user interface for the home screen of the Coffee Clicker game.
 * This class extends JPanel to display the background image, title, description, login button, and register button.
 */
public class HomeView extends JPanel {
    private final JButton loginButton;
    private final JButton registerButton;
    public static final String HOME_REGISTER = "home_register", HOME_LOGIN = "home_login";
    private static final int TITLE_FONT_SIZE = 45;
    private static final int DESCRIPTION_FONT_SIZE = 18;
    private static final String FONT_NAME = "Arial";

    /**
     * Constructs an instance of HomeView.
     */
    public HomeView() {
        // DEFINE ATTRIBUTES
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        JLabel title = new JLabel();
        JLabel description = new JLabel();

        String imagePath = "Images/CoffeeClicker_Home.jpg";
        ConfigImage backgroundImage = new ConfigImage(imagePath);
        backgroundImage.setLayout(new BorderLayout());

        // ADD TEXT
        setLabelProperties(title, "<html><center>TIME TO DISCOVER<br>COFFEE CLICKER</center></html>", TITLE_FONT_SIZE, true);
        setLabelProperties(description, "<html><center>Get ready for an addictive clicking<br>experience with Coffee Clicker: " +
                "the<br>new sensation that will keep you<br>awake and excited!</center></html>", DESCRIPTION_FONT_SIZE, false);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 60, 0, 0));
        titlePanel.add(title, BorderLayout.WEST);
        backgroundImage.add(titlePanel, BorderLayout.NORTH);

        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setOpaque(false);
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(100, 125, 50, 50));
        descriptionPanel.add(description, BorderLayout.NORTH);
        backgroundImage.add(descriptionPanel, BorderLayout.CENTER);

        // BUTTONS
        Dimension buttonSize = new Dimension(150, 50);
        loginButton.setPreferredSize(buttonSize);
        registerButton.setPreferredSize(buttonSize);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 120, 50));

        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(65, 0)));
        buttonPanel.add(registerButton);

        backgroundImage.add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundImage);
    }

    /**
     * Sets the properties of a JLabel.
     *
     * @param label    the JLabel to set properties for.
     * @param text     the text to be displayed.
     * @param fontSize the font size of the text.
     * @param isBold   true if the text should be bold, false otherwise.
     */
    private void setLabelProperties(JLabel label, String text, int fontSize, boolean isBold) {
        label.setText(text);
        label.setForeground(Color.WHITE);
        Font labelFont = new Font(HomeView.FONT_NAME, isBold ? Font.BOLD : Font.PLAIN, fontSize);
        label.setFont(labelFont);
    }

    /**
     * Sets up the ActionListener for the register button.
     * @param actionListener the ActionListener to be added to the register button.
     */
    public void registerController(ActionListener actionListener) {
        if (registerButton != null) {
            registerButton.setActionCommand(HOME_REGISTER);
            registerButton.addActionListener(actionListener);
        }
    }

    /**
     * Sets up the ActionListener for the home button.
     * @param actionListener the ActionListener to be added to the home button.
     */
    public void homeController(ActionListener actionListener) {
        if (loginButton != null) {
            loginButton.setActionCommand(HOME_LOGIN);
            loginButton.addActionListener(actionListener);
        }
    }
}
