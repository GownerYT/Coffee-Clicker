import Business.Exceptions.BusinessException;
import Business.Managers.*;
import Persistence.*;
import Presentation.Views.*;
import Presentation.Controllers.*;

import javax.swing.*;

/**
 * The application's main class containing the main method to start the program.
 *
 * @author David Cutanda, Pau Salip, Marc Sánchez, Gabriel Sánchez, Alvaro Duelo.
 * @version 1.0
 */
public class Main {

    /**
     * Main method to start the program.
     *
     * @param args The program's arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            try {
                // INITIALIZE DAOS
                SQLUserDAO sqlUserDAO = new SQLUserDAO();
                SQLGameDAO sqlGameDAO = new SQLGameDAO();
                SQLGeneratorDAO sqlGeneratorDAO = new SQLGeneratorDAO();
                SQLUpgradeDAO sqlUpgradeDAO = new SQLUpgradeDAO();
                SQLStatisticsDAO sqlStatisticsDAO = new SQLStatisticsDAO();

                // INITIALIZE MANAGERS
                UserManager userManager = new UserManager(sqlUserDAO, sqlGameDAO, sqlStatisticsDAO);
                GameManager gameManager = new GameManager(sqlGameDAO);
                GeneratorManager generatorManager = new GeneratorManager(sqlGeneratorDAO);
                UpgradeManager upgradeManager = new UpgradeManager(sqlUpgradeDAO);
                ShopManager shopManager = new ShopManager(sqlGeneratorDAO, sqlUpgradeDAO);
                StatisticsManager statisticsManager = new StatisticsManager(sqlStatisticsDAO);

                // INITIALIZE VIEWS
                HomeView homeView = new HomeView();
                LoginView loginView = new LoginView();
                RegisterView registerView = new RegisterView();
                MenuView menuView = new MenuView();
                AccountView accountView = new AccountView();
                StatisticsView statisticsView = new StatisticsView();
                GameView gameView = new GameView();
                MainView mainView = new MainView(homeView, loginView, registerView, menuView, accountView, gameView, statisticsView);

                // INITIALIZE CONTROLLERS
                MainController mainController = new MainController(mainView);
                HomeController homeController = new HomeController(mainController);
                LoginController loginController = new LoginController(loginView, mainController, userManager);
                RegisterController registerController = new RegisterController(registerView, mainController, userManager);
                StatisticsController statisticsController = new StatisticsController(statisticsView, mainController, statisticsManager, gameManager);
                GameController gameController = new GameController(gameView, shopManager, mainController, userManager, gameManager, statisticsManager);
                MenuController menuController = new MenuController(menuView, gameView,gameManager, userManager, statisticsManager, mainController, gameController, statisticsController);
                AccountController accountController = new AccountController(accountView, mainController, userManager, gameManager, gameController);

                // SET UP VIEW-CONTROLLER INTERACTIONS
                homeView.homeController(homeController);
                homeView.registerController(homeController);
                loginView.registerController(loginController);
                loginView.menuController(loginController);
                loginView.backController(loginController);
                registerView.loginController(registerController);
                registerView.registerController(registerController);
                registerView.backController(registerController);
                menuView.gameController(menuController);
                menuView.loadGameController(menuController);
                menuView.statisticsController(menuController);
                menuView.accountController(menuController);
                accountView.logoutController(accountController);
                accountView.deleteController(accountController);
                accountView.cancelController(accountController);
                gameView.accountController(gameController);
                gameView.backMenuController(gameController);
                gameView.endGameController(gameController);
                statisticsView.backController(statisticsController);
                statisticsView.accountController(statisticsController);

            } catch (BusinessException e) {
                String windowTitle = "Error - Xampp";
                String errorMessage = "Xampp application not started.";
                JOptionPane.showMessageDialog(null, errorMessage, windowTitle, JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}