package Presentation.Controllers;

import Business.Entities.Statistics;
import Business.Exceptions.BusinessException;
import Business.Managers.GameManager;
import Business.Managers.StatisticsManager;
import Business.Managers.UserManager;
import Presentation.Views.GameView;
import Presentation.Views.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The MenuController class is responsible for handling user actions
 * in the menu view and triggering appropriate actions in response to those actions.
 */
public class MenuController implements ActionListener {

    private final MenuView menuView;
    private final GameView gameView;
    private final GameManager gameManager;
    private final UserManager userManager;
    private final StatisticsManager statisticsManager;
    private final MainController mainController;
    private final GameController gameController;
    private final StatisticsController statisticsController;

    /**
     * Constructs a new MenuController with the specified MenuView and MainController.
     * @param menuView The MenuView associated with this controller.
     * @param mainController The MainController associated with this controller.
     */
    public MenuController(MenuView menuView, GameView gameView, GameManager gameManager, UserManager userManager, StatisticsManager statisticsManager, MainController mainController, GameController gameController, StatisticsController statisticsController) {
        this.menuView = menuView;
        this.gameView = gameView;
        this.gameManager = gameManager;
        this.userManager = userManager;
        this.statisticsManager = statisticsManager;
        this.mainController = mainController;
        this.gameController = gameController;
        this.statisticsController = statisticsController;
    }

    /**
     * Invoked when an action occurs. Processes the action event and performs
     * appropriate actions based on the action command.
     * @param actionEvent The action event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        mainController.setCurrentView("menu");
        int userId;
        boolean hasExistingGame = false;

        switch (actionCommand) {
            case MenuView.MENU_NEW_GAME:
                userId = userManager.getUserId();
                try {
                    hasExistingGame = gameManager.getGameByUserId(userId) != null;
                } catch (BusinessException e) {
                    menuView.showMessage(e.getMessage());
                }
                if (hasExistingGame) {
                    menuView.showGameInProgress();
                } else {
                    boolean nameExists = false;
                    String nameGame;
                    do {
                        nameGame = menuView.requestNameGame();
                        if (nameGame == null) {
                            return;
                        }
                        if (!nameGame.trim().isEmpty()) {
                            try {
                                String username = userManager.getUsername();
                                List<Statistics> userStatistics = statisticsManager.getStatisticsByUsername(username);
                                String finalNameGame = nameGame;
                                nameExists = userStatistics.stream().anyMatch(stats -> stats.getNameGame().equalsIgnoreCase(finalNameGame));
                                if (nameExists) {
                                    menuView.showGameNameExists();
                                }
                            } catch (BusinessException e) {
                                menuView.showMessage(e.getMessage());
                            }
                        } else {
                            return;
                        }
                    } while (nameExists);
                    try {
                        gameView.resetAllLabels();
                        gameManager.initializeGame(nameGame, userId, 0, 0);
                        mainController.showGame();
                        gameController.startTimer();
                        gameManager.startTimer();
                        gameManager.resetAppliedCurrentMinutes();
                    } catch (BusinessException e) {
                        menuView.showMessage(e.getMessage());
                    }
                }
                break;
            case MenuView.MENU_LOAD_GAME:
                userId = userManager.getUserId();
                try {
                    hasExistingGame = gameManager.getGameByUserId(userId) != null;
                } catch (BusinessException e) {
                    menuView.showMessage(e.getMessage());
                }
                if (hasExistingGame) {
                    try {
                        gameManager.initializeGame(gameManager.getGameByUserId(userId).getNameGame(), userId, gameManager.getGameByUserId(userId).getCafesAvailable(), gameManager.getGameByUserId(userId).getElapsedTime());
                    } catch (BusinessException e) {
                        menuView.showMessage(e.getMessage());
                    }
                    mainController.showGame();
                    gameController.loadGame();
                    gameController.startTimer();
                    gameManager.startTimer();
                    gameManager.resetAppliedCurrentMinutes();
                } else {
                    menuView.showNoGameStarted();
                }
                break;
            case MenuView.MENU_STATICS:
                mainController.showStatics();
                try {
                    statisticsController.loadStatisticsByUsername(userManager.getUsername());
                } catch (BusinessException e) {
                    menuView.showMessage(e.getMessage());                }
                break;
            case MenuView.MENU_ACCOUNT:
                mainController.showAccount();
                break;
            default:
                mainController.showMenu();
                break;
        }
    }
}
