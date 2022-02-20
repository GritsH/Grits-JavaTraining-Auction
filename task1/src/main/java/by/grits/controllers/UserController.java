package by.grits.controllers;

import by.grits.entities.enums.RoleType;
import by.grits.entities.people.User;
import by.grits.services.UserService;
import by.grits.utils.Session;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserController {
    private Scanner scanner;
    private UserService userService;
    private final Logger logger;

    public UserController(UserService userService) {
        this.userService = userService;
        scanner = new Scanner(System.in);
        logger = LogManager.getLogger("UserController.class");
    }

    public void signUp() throws NoSuchAlgorithmException, InvalidKeySpecException {
        scanner.nextLine();

        logger.info("Your name: ");
        String name = scanner.nextLine();

        logger.info("Your phone number: ");
        String phoneNumber = scanner.nextLine();

        logger.info("Email: ");
        String email = scanner.nextLine();

        logger.info("Login: ");
        String login = scanner.nextLine();

        logger.info("Password: ");
        String inputPassword = scanner.nextLine();

        String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (login.contains("admin")) {
            logger.info("Sorry, this login is reserved, try again");
        } else if (userService.userExists(phoneNumber)) {
            logger.info("User with such phone number exists");
        } else if (Pattern.matches("^\\d{12}$", phoneNumber) && Pattern.matches(emailPattern, email)) {
            User user = new User(name, phoneNumber, email, login, inputPassword, RoleType.USER);
            userService.addNewUser(user);
            logger.info("Successfully signed up!!");
        } else if (!Pattern.matches("^\\d{12}$", phoneNumber)) {
            logger.info("User cannot be registered, invalid phone format");
        } else if (!Pattern.matches(emailPattern, email)) {
            logger.info("User cannot be registered, invalid email format");
        }
    }

    public boolean logIn() throws NoSuchAlgorithmException, InvalidKeySpecException {
        scanner.nextLine();

        logger.info("Enter login: ");
        String login = scanner.nextLine();

        logger.info("Enter password: ");
        String password = scanner.nextLine();

        if (login.equals("admin")) {
            Session.setUser(userService.logIn(login, password));
            if(Session.getUser() != null){
                logger.info("Logged in as ADMIN");
                return true;
            }
            return false;
        } else if (userService.logIn(login, password) != null) {
            Session.setUser(userService.logIn(login, password));
            logger.info("You are in!!");
            return true;
        } else {
            logger.info("No such user");
        }
        return false;
    }


    public void userCommands() {
        logger.info("1. Sign up");
        logger.info("2. Log in");
        logger.info("3. Exit");
    }

    public void userMenu() throws NoSuchAlgorithmException, InvalidKeySpecException {
        boolean runMenu = true;
        while (runMenu) {
            userCommands();
            switch (scanner.nextInt()) {
                case 1 -> signUp();
                case 2 -> {
                    Session.setUser(null);
                    if (logIn()) {
                        runMenu = false;
                    }
                }
                case 3 -> System.exit(0);
                default -> logger.info("No such command");
            }
        }
    }
}
