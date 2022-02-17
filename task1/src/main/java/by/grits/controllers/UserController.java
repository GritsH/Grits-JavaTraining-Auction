package by.grits.controllers;

import by.grits.dao.Dao;
import by.grits.entities.people.Admin;
import by.grits.entities.people.CommonUser;
import by.grits.entities.people.User;
import by.grits.services.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserController {
    private Scanner scanner;
    private static UserDao userDAO;
    private List<User> users;
    private User currentUser;

    public UserController() {
        userDAO = new UserDao();
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public UserDao getUserDAO(){
        return userDAO;
    }

    public void signUp() {
        String name = "";
        String phoneNumber = "";
        String email = "";
        String login = "";
        String password = "";

        scanner.nextLine();

        System.out.println("Your name: ");
        name = scanner.nextLine();
        System.out.println("Your phone number: ");
        phoneNumber = scanner.nextLine();
        System.out.println("Email: ");
        email = scanner.nextLine();
        System.out.println("Login: ");
        login = scanner.nextLine();
        System.out.println("Password: ");
        password = scanner.nextLine();

        String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (login.contains("admin")) {
            System.out.println("Sorry, this login is reserved, try again");
        } else if (userDAO.userExists(email, phoneNumber)) {
            System.out.println("User with such email/phone number exists");
        } else if (Pattern.matches("^\\d{12}$", phoneNumber) && Pattern.matches(emailPattern, email)) {
            CommonUser commonUser = new CommonUser(name, phoneNumber, email, login, password);
            userDAO.add(commonUser);
            System.out.println("Successfully signed up!!");
        } else if (!Pattern.matches("^\\d{12}$", phoneNumber)) {
            System.out.println("User cannot be registered, invalid phone format");
        } else if (!Pattern.matches(emailPattern, email)) {
            System.out.println("User cannot be registered, invalid email format");
        }
    }

    public boolean logIn() {
        String login = "";
        String password = "";

        scanner.nextLine();

        System.out.println("Enter login: ");
        login = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();

        if (login.equals("admin") && password.equals("admin")) {
            setCurrentUser(new Admin(null, null, null, login, password));
            return true;
        } else if (userDAO.getUserByLoginPassword(login, password) != null) {
            setCurrentUser(userDAO.getUserByLoginPassword(login, password));
            System.out.println("You are in!!");
            return true;
        } else {
            System.out.println("No such user");
        }
        return false;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void userCommands() {
        System.out.println("1. Sign up");
        System.out.println("2. Log in");
        System.out.println("3. exit");
    }

    public void userMenu() {

        boolean runMenu = true;
        while (runMenu) {
            userCommands();
            switch (scanner.nextInt()) {
                case 1 -> signUp();
                case 2 -> {
                    setCurrentUser(null);
                    if (logIn()) {
                        runMenu = false;
                    }
                }
                case 3 -> System.exit(0);
                default -> System.out.println("No such command");
            }
        }
    }
}
