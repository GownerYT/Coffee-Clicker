package Presentation.Controllers;

import Business.Entities.Statistics;
import Business.Exceptions.BusinessException;
import Business.Managers.GameManager;
import Business.Managers.StatisticsManager;
import Presentation.Views.StatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The StatisticsController class is responsible for handling user actions
 * in the statistics view and triggering appropriate actions in response to those actions.
 */
public class StatisticsController implements ActionListener {

    private final StatisticsView staticsView;
    private final MainController mainController;
    private final StatisticsManager statisticsManager;
    private final GameManager gameManager;
    private List<Statistics> userStatisticsList;
    private List<String> gamesList;

    /**
     * Constructs a new StatisticsController.
     * @param staticsView The StatisticsView associated with this controller.
     * @param mainController The MainController associated with this controller.
     */
    public StatisticsController(StatisticsView staticsView, MainController mainController, StatisticsManager statisticsManager, GameManager gameManager) {
        this.staticsView = staticsView;
        this.mainController = mainController;
        this.statisticsManager = statisticsManager;
        this.gameManager = gameManager;
        this.userStatisticsList = new ArrayList<>();
        this.gamesList = new ArrayList<>();

        this.staticsView.userDropdownController(this);
        this.staticsView.gameDropdownController(this);
    }

    /**
     * Invoked when an action occurs. Processes the action event and performs
     * appropriate actions based on the action command.
     * @param actionEvent The action event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        mainController.setCurrentView("statistics");

        switch (actionCommand) {
            case StatisticsView.STATICS_BACk:
                mainController.showMenu();
                break;
            case StatisticsView.STATICS_ACCOUNT:
                mainController.showAccount();
                break;
            case StatisticsView.STATICS_USER_DROPDOWN:
                String selectedUser = staticsView.getSelectedUser();
                if (selectedUser != null) {
                    try {
                        loadStatisticsByUsername(selectedUser);
                    } catch (BusinessException e) {
                        staticsView.showMessage(e.getMessage());
                    }
                }
                break;
            case StatisticsView.STATICS_GAME_DROPDOWN:
                String selectedGame = staticsView.getSelectedGame();
                if (selectedGame != null) {
                    updateLineChart(selectedGame);
                }
                break;
            default:
                break;
        }
    }

    public void loadStatisticsByUsername(String username) throws BusinessException {
        userStatisticsList = statisticsManager.getStatisticsByUsername(username);
        if (!userStatisticsList.isEmpty()) {
            gamesList = new ArrayList<>();
            for (Statistics statistics : userStatisticsList) {
                int gameId = statistics.getGameId();
                if (gameManager.getGameById(gameId) == null) {
                    String gameName = statistics.getNameGame();
                    if (!gamesList.contains(gameName)) {
                        gamesList.add(gameName);
                    }
                }
            }

            String[] usersArray = {username};
            staticsView.updateUsersDropdown(usersArray);

            String[] gamesArray = gamesList.toArray(new String[0]);
            String[] gamesArrayWithEmptyRow = new String[gamesArray.length + 1];
            gamesArrayWithEmptyRow[0] = "Choose a game";
            System.arraycopy(gamesArray, 0, gamesArrayWithEmptyRow, 1, gamesArray.length);
            staticsView.updateGameDropdown(gamesArrayWithEmptyRow);

        } else {
            staticsView.updateGameDropdown(new String[0]);
            staticsView.updateLineChart(new int[0], new int[0]);
            staticsView.updateUsersDropdown(new String[0]);
        }
    }

    public void updateLineChart(String selectedGame) {
        String selectedUser = staticsView.getSelectedUser();
        List<Statistics> gameStatistics = userStatisticsList.stream().filter(statistics -> statistics.getNameGame().equals(selectedGame)
                        && statistics.getUsername().equals(selectedUser)).toList();

        if (!gameStatistics.isEmpty()) {
            int[] timeData = gameStatistics.stream().mapToInt(Statistics::getTime).toArray();
            int[] coffeesData = gameStatistics.stream().mapToInt(Statistics::getCoffeesAvailable).toArray();

            staticsView.updateLineChart(timeData, coffeesData);
        } else {
            staticsView.updateLineChart(new int[0], new int[0]);
        }
    }
}
