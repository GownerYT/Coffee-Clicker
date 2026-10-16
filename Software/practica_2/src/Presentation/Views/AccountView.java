package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * AccountView class represents the user interface for managing account actions.
 * This class extends JPanel to provide functionality for logging out, deleting the account, and canceling actions with his confirmation dialogues.
 */
public class AccountView extends JPanel {

    private final JButton logoutButton;
    private final JButton deleteAccountButton;
    private final JButton cancelButton;
    public static final String ACCOUNT_LOGOUT = "account_logout", ACCOUNT_DELETE = "account_delete", ACCOUNT_CANCEL = "cancel";

    /**
     * Constructs an instance of AccountView.
     */
    public AccountView() {
        // DEFINE ATTRIBUTES
        logoutButton = new JButton("Logout");
        deleteAccountButton = new JButton("Delete Account");
        cancelButton = new JButton("Cancel");
        JLabel description = new JLabel("<html><center>We are sad to see you go, but we understand your decision.<br>" +
                "If you decide to log out or delete your account, please note<br>that you will lose your progress and all " +
                "accumulated<br>rewards. Are you sure you want to continue?</center></html>");

        setLayout(new BorderLayout());
        String imagePath = "Images/CoffeeClicker_Background.jpg";
        ConfigImage backgroundImage = new ConfigImage(imagePath);
        add(backgroundImage, BorderLayout.CENTER);

        // TEXT
        description.setForeground(Color.WHITE);
        Font descriptionFont = new Font("Arial", Font.BOLD, 24);
        description.setFont(descriptionFont);
        description.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setOpaque(false);
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 50, 50));
        descriptionPanel.add(description, BorderLayout.NORTH);
        backgroundImage.add(descriptionPanel, BorderLayout.CENTER);

        // BUTTONS
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 40));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 200, 100, 200));
        Dimension buttonSize = new Dimension(250, 50);
        logoutButton.setPreferredSize(buttonSize);
        deleteAccountButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);

        buttonPanel.add(logoutButton);
        buttonPanel.add(deleteAccountButton);
        buttonPanel.add(cancelButton);
        backgroundImage.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Displays a confirmation dialogue for logging out the account.
     * @return true if the user confirms logout, false otherwise.
     */
    public boolean showLogoutAccount() {
        String windowTitle = "Logout Account";
        String errorMessage = "Are you sure you want to log out?";
        int option = JOptionPane.showConfirmDialog(null, errorMessage, windowTitle, JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    /**
     * Displays a confirmation dialogue for deleting the account.
     * @return true if the user confirms deletion, false otherwise.
     */
    public boolean showDeleteAccount() {
        String windowTitle = "Delete Account";
        String errorMessage = "Are you sure you want to delete the account?";
        int option = JOptionPane.showConfirmDialog(null, errorMessage, windowTitle, JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
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
     * Sets up the ActionListener for the logout button.
     * @param actionListener the ActionListener to be added to the logout button.
     */
    public void logoutController(ActionListener actionListener) {
        logoutButton.setActionCommand(ACCOUNT_LOGOUT);
        logoutButton.addActionListener(actionListener);
    }

    /**
     * Sets up the ActionListener for the delete account button.
     * @param actionListener the ActionListener to be added to the delete account button.
     */
    public void deleteController(ActionListener actionListener) {
        deleteAccountButton.setActionCommand(ACCOUNT_DELETE);
        deleteAccountButton.addActionListener(actionListener);
    }

    /**
     * Sets up the ActionListener for the cancel button.
     * @param actionListener the ActionListener to be added to the cancel button.
     */
    public void cancelController(ActionListener actionListener) {
        cancelButton.setActionCommand(ACCOUNT_CANCEL);
        cancelButton.addActionListener(actionListener);
    }
}
