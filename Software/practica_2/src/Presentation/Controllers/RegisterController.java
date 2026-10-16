package Presentation.Controllers;

import Business.Exceptions.BusinessException;
import Business.Managers.UserManager;
import Persistence.Exceptions.PersistenceException;
import Presentation.Views.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The RegisterController class is responsible for handling user actions
 * in the registration view and triggering appropriate actions in response to those actions.
 */
public class RegisterController implements ActionListener {

    private final RegisterView registerView;
    private final MainController mainController;
    private final UserManager userManager;

    /**
     * Constructs a new RegisterController with the specified RegisterView, MainController, and UserManager.
     * @param registerView The RegisterView associated with this controller.
     * @param mainController The MainController associated with this controller.
     * @param userManager The UserManager associated with this controller.
     */
    public RegisterController(RegisterView registerView, MainController mainController, UserManager userManager) {
        this.registerView = registerView;
        this.mainController = mainController;
        this.userManager = userManager;
    }

    /**
     * Invoked when an action occurs. Processes the action event and performs
     * appropriate actions based on the action command.
     * @param actionEvent The action event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();

        String username = registerView.getUsername();
        String email = registerView.getEmail();
        String password = registerView.getPassword();
        String confirmPassword = registerView.getConfirmPassword();

        switch (actionCommand) {
            case RegisterView.SCREEN_REGISTER_LOGIN:
                mainController.showLogin();
                break;
            case RegisterView.SCREEN_REGISTER_BACK:
                mainController.showHome();
                break;
            case RegisterView.SCREEN_REGISTER_REGISTER:
                UserManager.Status registerStatus = null;
                try {
                    registerStatus = userManager.registerUser(username, email ,password, confirmPassword);
                } catch (BusinessException | PersistenceException e) {
                    registerView.showMessage(e.getMessage());
                }
                if (registerStatus != null) {
                    switch (registerStatus) {
                        case SUCCESS:
                            mainController.showLogin();
                            registerView.clearFields();
                            break;
                        case INCORRECT_USERNAME:
                            registerView.showUserNameError();
                            break;
                        case INCORRECT_EMAIL:
                            registerView.showEmailError();
                            break;
                        case INCORRECT_PASSWORD:
                            registerView.showPasswordError();
                            break;
                        case INCORRECT_MATCH_PASSWORD:
                            registerView.showConfirmPasswordError();
                            break;
                    }
                }
                break;

            default:
                break;
        }
    }
}
