package Presentation.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * RegisterView class represents the user interface for user registration in the Coffee Clicker game.
 * This class extends JPanel and contains fields for entering username, email, password, and confirming the password.
 */
public class RegisterView extends JPanel {

    private final JButton loginButton;
    private final JButton registerButton;
    private final JButton backButton;
    private final JTextField usernameField;
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JPasswordField confirmPasswordField;
    public static final String SCREEN_REGISTER_LOGIN = "screen_register_login", SCREEN_REGISTER_REGISTER = "screen_register_register", SCREEN_REGISTER_BACK = "screen_register_back";

    /**
     * Constructs an instance of RegisterView.
     */
    public RegisterView() {
        // DEFINE ATTRIBUTES
        loginButton = new JButton("LOGIN");
        registerButton = new JButton("Register");

        usernameField = new JTextField(15);
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);
        confirmPasswordField = new JPasswordField(15);

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
        container.setBorder(new EmptyBorder(60, 50, 50, 50));
        setTransparent(container);
        JPanel square = new JPanel();
        square.setLayout(new GridBagLayout());
        square.setPreferredSize(new Dimension(750, 500));
        square.setBackground(Color.white);

        // SPLIT RECTANGLE
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 2; // (2/3)
        gbc.weighty = 1;
        square.add(createLeftPanel(), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1; // (1/3)
        square.add(createRightPanel(), gbc);

        container.add(square, BorderLayout.CENTER);
        backgroundImage.add(container, BorderLayout.CENTER);

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
        Insets margin = new Insets(10, 10, 550, 10);
        backButton.setMargin(margin);

        return backButton;
    }

    /**
     * Creates and returns the left panel containing registration form elements.
     * @return A JPanel instance representing the left panel.
     */
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(30, 20, 30, 20);

        // TITLE "REGISTER"
        JLabel registerTitleLabel = new JLabel("REGISTER");
        registerTitleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftPanel.add(registerTitleLabel, gbc);

        // USERNAME
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 20, 5, 20);
        leftPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(10, 0, 5, 20);
        leftPanel.add(usernameField, gbc);

        // EMAIL
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 20, 5, 20);
        leftPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 0, 5, 20);
        leftPanel.add(emailField, gbc);

        // PASSWORD
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 20, 5, 20);
        leftPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 0, 5, 20);
        leftPanel.add(passwordField, gbc);

        // CONFIRM PASSWORD
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 20, 10, 20);
        leftPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 0, 10, 20);
        leftPanel.add(confirmPasswordField, gbc);

        // REGISTER BUTTON
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 20, 5, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(registerButton, gbc);

        // ADD TEXT
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.setBackground(Color.WHITE);
        JLabel normalTextLabel = new JLabel("Already have an account?");
        normalTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(normalTextLabel);

        JLabel loginLinkLabel = new JLabel("Login");
        loginLinkLabel.setForeground(Color.BLUE);
        loginLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginButton.doClick();
            }
        });
        loginLinkLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(loginLinkLabel);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 20, 30, 20);
        leftPanel.add(textPanel, gbc);

        return leftPanel;
    }

    /**
     * Creates and returns the right panel containing descriptive text.
     * @return A JPanel instance representing the right panel.
     */
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.lightGray);

        // FIRST TEXT
        JLabel text1 = new JLabel("Coffee Clicker");
        text1.setFont(new Font("Arial", Font.BOLD, 24));
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(0, 0, 10, 0);
        rightPanel.add(text1, gbc1);

        // SECOND TEXT
        JLabel text2 = new JLabel("<html><center>Join Now!</center></html>");
        text2.setFont(new Font("Arial", Font.PLAIN, 18));
        text2.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.insets = new Insets(10, 0, 0, 0);
        rightPanel.add(text2, gbc2);

        return rightPanel;
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
     * Clears the registration fields.
     */
    public void clearFields() {
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    /**
     * Retrieves the entered username.
     * @return the username entered by the user.
     */
    public String getUsername() {
        if (usernameField != null) {
            return usernameField.getText();
        } else {
            return null;
        }
    }

    /**
     * Retrieves the entered email.
     * @return the email entered by the user.
     */
    public String getEmail() {
        if (emailField != null) {
            return emailField.getText();
        } else {
            return null;
        }
    }

    /**
     * Retrieves the entered password.
     * @return the password entered by the user.
     */
    public String getPassword() {
        if (passwordField != null) {
            char[] passwordChars = passwordField.getPassword();
            return new String(passwordChars);
        } else {
            return null;
        }
    }


    /**
     * Retrieves the entered confirmation password.
     * @return the confirmation password entered by the user.
     */
    public String getConfirmPassword() {
        if (confirmPasswordField != null) {
            char[] confirmPasswordChars = confirmPasswordField.getPassword();
            return new String(confirmPasswordChars);
        } else {
            return null;
        }
    }


    /**
     * Displays an error message indicating an invalid username.
     */
    public void showUserNameError() {
        String windowTitle = "Username Error - System Error";
        String errorMessage = "The entered username is not valid. Please try again.";
        JOptionPane.showMessageDialog(null, errorMessage, windowTitle, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an error message indicating an invalid email.
     */
    public void showEmailError() {
        String windowTitle = "Email Error - System Error";
        String errorMessage = "The entered email is not valid. Please try again.";
        JOptionPane.showMessageDialog(null, errorMessage, windowTitle, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an error message indicating an invalid password.
     */
    public void showPasswordError() {
        String windowTitle = "Password Error - System Error";
        String errorMessage = "The entered password is not valid. Please try again.";
        JOptionPane.showMessageDialog(null, errorMessage, windowTitle, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an error message indicating an invalid confirmation password.
     */
    public void showConfirmPasswordError() {
        String windowTitle = "Confirm Password Error - System Error";
        String errorMessage = "The entered confirm password is not valid. Please try again.";
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
     * Sets the action listener for the register button.
     * @param actionListener the action listener to be set.
     */
    public void registerController(ActionListener actionListener) {
        registerButton.setActionCommand(SCREEN_REGISTER_REGISTER);
        registerButton.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the login button.
     * @param actionListener the action listener to be set.
     */
    public void loginController(ActionListener actionListener) {
        loginButton.setActionCommand(SCREEN_REGISTER_LOGIN);
        loginButton.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the back button.
     * @param actionListener the action listener to be set.
     */
    public void backController(ActionListener actionListener) {
        backButton.setActionCommand(SCREEN_REGISTER_BACK);
        backButton.addActionListener(actionListener);
    }
}
