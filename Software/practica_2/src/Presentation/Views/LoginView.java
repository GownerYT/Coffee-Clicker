package Presentation.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * LoginView class represents the user interface for the login screen of the Coffee Clicker game.
 * This class extends JPanel to display the background image, login form, registration button, and back button.
 */
public class LoginView extends JPanel {
    private final JButton loginButton;
    private final JButton registerButton;
    private final JButton backButton;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    public static final String SCREEN_LOGIN_REGISTER = "screen_login_register", SCREEN_LOGIN_LOGIN = "screen_login_login",
            SCREEN_LOGIN_BACK = "screen_login_back";

    /**
     * Constructs an instance of LoginView.
     */
    public LoginView() {
        // DEFINE ATTRIBUTES
        registerButton = new JButton("REGISTER");
        loginButton = new JButton("LOGIN");
        usernameField= new JTextField(15);
        passwordField = new JPasswordField(15);

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
     * Creates a JButton with a back arrow icon.
     * @param backArrowIcon the ImageIcon representing the back arrow.
     * @return the JButton with the back arrow icon.
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
     * Creates the left panel containing the login form.
     * @return the JPanel representing the left panel.
     */
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(30, 20, 30, 20);

        // TITLE "LOGIN"
        JLabel loginTitleLabel = new JLabel("LOGIN");
        loginTitleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftPanel.add(loginTitleLabel, gbc);

        // USERNAME
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        leftPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        leftPanel.add(usernameField, gbc);

        // PASSWORD
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        leftPanel.add(passwordField, gbc);

        // LOGIN BUTTON
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 20, 5, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(loginButton, gbc);

        // ADD TEXT
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.setBackground(Color.WHITE);
        JLabel normalTextLabel = new JLabel("Don't have an account?");
        normalTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(normalTextLabel);

        JLabel registerLinkLabel = new JLabel("Register");
        registerLinkLabel.setForeground(Color.BLUE);
        registerLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerButton.doClick();
            }
        });
        registerLinkLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(registerLinkLabel);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 20, 30, 20);
        leftPanel.add(textPanel, gbc);

        return leftPanel;
    }

    /**
     * Creates the right panel containing welcome text.
     * @return the JPanel representing the right panel.
     */
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.lightGray);

        // FIRST TEXT
        JLabel text1 = new JLabel("Welcome back");
        text1.setFont(new Font("Arial", Font.BOLD, 24));
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(0, 0, 10, 0);
        rightPanel.add(text1, gbc1);

        // SECOND TEXT
        JLabel text2 = new JLabel("<html><center>Are you ready to<br>make some coffee?</center></html>");
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
     * Makes the given component transparent.
     * @param component the component to make transparent.
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
     * Clears the username and password fields.
     */
    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    /**
     * Gets the entered username or email.
     * @return the entered username or email.
     */
    public String getUsernameOrEmail() {
        if (usernameField != null) {
            return usernameField.getText();
        } else {
            return null;
        }
    }

    /**
     * Gets the entered password.
     * @return the entered password.
     */
    public String getPassword() {
        if (passwordField != null) {
            char[] passwordChars = passwordField.getPassword();
            if (passwordChars != null) {
                return new String(passwordChars);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    /**
     * Displays an error message for an invalid username.
     */
    public void showUserNameError() {
        String windowTitle = "Username Error - System Error";
        String errorMessage = "The entered username is not valid. Please try again.";
        JOptionPane.showMessageDialog(null, errorMessage, windowTitle, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an error message for an invalid password.
     */
    public void showPasswordError() {
        String windowTitle = "Password Error - System Error";
        String errorMessage = "The entered password is not valid. Please try again.";
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
     * Sets up the ActionListener for the register button.
     * @param actionListener the ActionListener to be added to the register button.
     */
    public void registerController(ActionListener actionListener) {
        registerButton.setActionCommand(SCREEN_LOGIN_REGISTER);
        registerButton.addActionListener(actionListener);
    }

    /**
     * Sets up the ActionListener for the login button.
     * @param actionListener the ActionListener to be added to the login button.
     */
    public void menuController(ActionListener actionListener) {
        loginButton.setActionCommand(SCREEN_LOGIN_LOGIN);
        loginButton.addActionListener(actionListener);
    }

    /**
     * Sets up the ActionListener for the back button.
     * @param actionListener the ActionListener to be added to the back button.
     */
    public void backController(ActionListener actionListener) {
        backButton.setActionCommand(SCREEN_LOGIN_BACK);
        backButton.addActionListener(actionListener);
    }
}
