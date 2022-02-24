package by.grits.controllers;

import by.grits.dao.DaoException;
import by.grits.entities.enums.RoleType;
import by.grits.entities.people.User;
import by.grits.services.UserService;
import by.grits.utils.Session;
import by.grits.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UserController {
  private static final Logger LOGGER = LogManager.getLogger(UserController.class);
  private Scanner scanner;
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
    scanner = new Scanner(System.in);
  }

  public void signUp() throws DaoException {
    Validator validator = new Validator();
    LOGGER.info("Your name: ");
    String name = scanner.nextLine();

    LOGGER.info("Your phone number: ");
    String phoneNumber = scanner.nextLine();

    LOGGER.info("Email: ");
    String email = scanner.nextLine();

    LOGGER.info("Password: ");
    String inputPassword = scanner.nextLine();

    if (email.equals("admin@gmail.com")) {
      LOGGER.info("Sorry, this email is reserved, try again");
    } else if (userService.userExists(email)) {
      LOGGER.info("User with such email exists");
    } else if (validator.validatePhoneInput(phoneNumber) && validator.validateEmailInput(email)) {
      User user = new User(name, phoneNumber, email, inputPassword, RoleType.USER);
      userService.addNewUser(user);
      LOGGER.info("Successfully signed up!!");
    } else if (!validator.validatePhoneInput(phoneNumber)) {
      LOGGER.info("User cannot be registered, invalid phone format");
    } else if (!validator.validateEmailInput(email)) {
      LOGGER.info("User cannot be registered, invalid email format");
    }
  }

  public boolean logIn() throws DaoException {

    LOGGER.info("Enter email: ");
    String email = scanner.nextLine();

    LOGGER.info("Enter password: ");
    String password = scanner.nextLine();

    if (email.equals("admin@gmail.com")) {
      Session.setUser(userService.logIn(email, password));
      if (Session.getUser() != null) {
        LOGGER.info("Logged in as ADMIN");
        return true;
      }
      return false;
    } else if (userService.logIn(email, password) != null) {
      Session.setUser(userService.logIn(email, password));
      LOGGER.info("You are in!!");
      return true;
    } else {
      LOGGER.info("No such user");
    }
    return false;
  }

  public void userCommands() {
    LOGGER.info("1. Sign up");
    LOGGER.info("2. Log in");
    LOGGER.info("3. Exit");
  }

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
          Session.setUser(null);
          if (logIn()) {
            runMenu = false;
          }
          break;
        case "3":
          System.exit(0);
        default:
          LOGGER.info("No such command");
          break;
      }
    }
  }
}
