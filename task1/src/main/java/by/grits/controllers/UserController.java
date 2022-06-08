package by.grits.controllers;

import by.grits.entities.enums.RoleType;
import by.grits.entities.people.User;
import by.grits.services.UserService;
import by.grits.utils.Session;
import by.grits.validation.Validator;
import by.grits.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * This is class controller which provides console input\output. This class helps with creation of
 * new user and authorization by collecting input data and passing this data to the responsible
 * service.
 */
public class UserController {
  private static final Logger LOGGER = LogManager.getLogger(UserController.class);
  private Scanner scanner;
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
    scanner = new Scanner(System.in, StandardCharsets.UTF_8);
  }

  /**
   * This method is for registration of the new user. Requires console input from user. If input
   * information do not pass validation, method will display correspond message If user with
   * specific email already exists, method will display correspond message. If user with such email
   * do not exist, method will display correspond message.
   *
   * @throws DaoException due to calling a service method with such exception.
   */
  public void signUp() throws DaoException {
    LOGGER.info("Your name: ");
    String name = scanner.nextLine();

    LOGGER.info("Your phone number: ");
    String phoneNumber = scanner.nextLine();

    LOGGER.info("Email: ");
    String email = scanner.nextLine();

    LOGGER.info("Password: ");
    String inputPassword = scanner.nextLine();

    boolean phoneValid = Validator.validatePhoneInput(phoneNumber);
    boolean emailValid = Validator.validateEmailInput(email);

    if (!phoneValid) {
      LOGGER.info("User cannot be registered, invalid phone format");
    }
    if (!emailValid) {
      LOGGER.info("User cannot be registered, invalid email format");
    }

    if (phoneValid && emailValid) {
      if (userService.userExists(email)) {
        LOGGER.info("User with such email exists");
      } else {
        User user = new User(name, phoneNumber, email, inputPassword, RoleType.USER);
        userService.addNewUser(user);
        LOGGER.info("Successfully signed up!!");
      }
    }
  }

  /**
   * Method for logging an existing user. Requires user's console input.
   *
   * @throws DaoException due to calling a service method with such exception.
   */
  public boolean logIn() throws DaoException {

    LOGGER.info("Enter email: ");
    String email = scanner.nextLine();

    LOGGER.info("Enter password: ");
    String password = scanner.nextLine();

    User foundUser = userService.logIn(email, password);
    if (foundUser == null) {
      LOGGER.info("No such user");
      return false;
    }

    Session.setUser(foundUser);
    LOGGER.info("Logged in as {}", foundUser.getName());
    return true;
  }

  /** This method displays menu for unregistered user */
  public void userCommands() {
    LOGGER.info("1. Sign up");
    LOGGER.info("2. Log in");
    LOGGER.info("3. Exit");
  }

  /**
   * This method reads user's console input and calls a corresponding method
   *
   * @throws DaoException due to calling methods with such exception.
   */
  public void userMenu() throws DaoException {
    boolean runMenu = true;
    while (runMenu) {
      userCommands();
      String input = scanner.nextLine();
      switch (input) {
        case "1":
          signUp();
          break;
        case "2":
          if (logIn()) {
            runMenu = false;
          }
          break;
        case "3":
          System.exit(0);
          break;
        default:
          LOGGER.info("No such command");
          break;
      }
    }
  }
}
