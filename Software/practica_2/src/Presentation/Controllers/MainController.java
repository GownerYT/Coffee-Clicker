package Presentation.Controllers;

import Presentation.Views.*;
/**
 * The MainController class is responsible for controlling the flow of the application
 * by managing transitions between different views.
 */
public class MainController {
    private final MainView mainView;
    private String currentView;

    /**
     * Constructs a new MainController with the specified MainView.
     * Initializes the MainView and sets this controller as its controller.
     * @param mainView The MainView associated with this controller.
     */
    public MainController(MainView mainView) {
        this.mainView = mainView;

        mainView.initialize();
    }

    /**
     * Displays the home view.
     */
    public void showHome() {
        mainView.showHome();
    }

    /**
     * Displays the login view.
     */
    public void showLogin() {
        mainView.showLogin();
    }

    /**
     * Displays the register view.
     */
    public void showRegister() {
        mainView.showRegister();
    }

    /**
     * Displays the menu view.
     */
    public void showMenu() {
        mainView.showMenu();
    }

    /**
     * Displays the account view.
     */
    public void showAccount() {
        mainView.showAccount();
    }

    /**
     * Displays the game view.
     */
    public void showGame() {
        mainView.showGame();
    }

    /**
     * Displays the statistics view.
     */
    public void showStatics() {
        mainView.showStatics();
    }

    /**
     * Sets the current view.
     *
     * @param view The name of the current view.
     */
    public void setCurrentView(String view) {
        this.currentView = view;
    }

    /**
     * Retrieves the name of the current view.
     *
     * @return The name of the current view.
     */
    public String getCurrentView() {
        return this.currentView;
    }

}
