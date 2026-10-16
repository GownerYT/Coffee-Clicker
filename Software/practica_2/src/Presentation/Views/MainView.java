package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * MainView class represents the main user interface of the Coffee Clicker game.
 * This class extends JPanel and utilizes a CardLayout to switch between different views such as HomeView, LoginView, RegisterView, MenuView,
 * AccountView, GameView, and StatisticsView.
 */
public class MainView extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private final JFrame frame;
    private static final String HOME = "home",LOGIN = "login", REGISTER = "register", MENU = "menu",
            ACCOUNT = "account", GAME = "game", STATICS = "statistics";

    /**
     * Constructs an instance of MainView.
     * @param homeView the HomeView instance.
     * @param loginView the LoginView instance.
     * @param registerView the RegisterView instance.
     * @param menuView the MenuView instance.
     * @param accountView the AccountView instance.
     * @param gameView the GameView instance.
     * @param staticsView the StatisticsView instance.
     */
    public MainView(HomeView homeView, LoginView loginView, RegisterView registerView, MenuView menuView,
                    AccountView accountView, GameView gameView, StatisticsView staticsView) {
        ImageIcon coffeeIcon = new ImageIcon("Images/CoffeeClicker_CoffeeCup.jpg");
        this.frame = new JFrame("Coffee Clicker");
        this.frame.setIconImage(coffeeIcon.getImage());

        createMainPanel(homeView, loginView, registerView, menuView, accountView, gameView, staticsView);
    }

    /**
     * Creates the main panel with CardLayout containing different views.
     * @param homeView the HomeView instance.
     * @param loginView the LoginView instance.
     * @param registerView the RegisterView instance.
     * @param menuView the MenuView instance.
     * @param accountView the AccountView instance.
     * @param gameView the GameView instance.
     * @param staticsView the StatisticsView instance.
     */
    private void createMainPanel(HomeView homeView, LoginView loginView, RegisterView registerView, MenuView menuView,
                                 AccountView accountView, GameView gameView, StatisticsView staticsView) {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(homeView, HOME);
        cardPanel.add(loginView, LOGIN);
        cardPanel.add(registerView, REGISTER);
        cardPanel.add(menuView, MENU);
        cardPanel.add(accountView, ACCOUNT);
        cardPanel.add(gameView, GAME);
        cardPanel.add(staticsView, STATICS);

        add(cardPanel);
    }

    /**
     * Displays the HomeView.
     */
    public void showHome() {
        cardLayout.show(cardPanel, HOME);
    }

    /**
     * Displays the LoginView.
     */
    public void showLogin() {
        cardLayout.show(cardPanel, LOGIN);
    }

    /**
     * Displays the RegisterView.
     */
    public void showRegister() {
        cardLayout.show(cardPanel, REGISTER);
    }

    /**
     * Displays the MenuView.
     */
    public void showMenu() {
        cardLayout.show(cardPanel, MENU);
    }

    /**
     * Displays the AccountView.
     */
    public void showAccount() {
        cardLayout.show(cardPanel, ACCOUNT);
    }

    /**
     * Displays the GameView.
     */
    public void showGame() {
        cardLayout.show(cardPanel, GAME);
    }

    /**
     * Displays the StatisticsView.
     */
    public void showStatics() {
        cardLayout.show(cardPanel, STATICS);
    }

    /**
     * Initializes the MainView and displays it in a JFrame.
     */
    public void initialize() {
        frame.add(this);
        frame.setSize(new Dimension(1000, 700));
        frame.setTitle("Coffee Clicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
