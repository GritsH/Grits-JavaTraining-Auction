package by.grits.controllers;

import by.grits.dao.ItemDao;
import by.grits.dao.UserDao;
import by.grits.entities.item.Item;
import by.grits.entities.people.User;
import by.grits.services.AdminService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class AdminController {
    private static final  Logger LOGGER = LogManager.getLogger(AdminController.class);
    private final Scanner scanner;
    private final AdminService adminService;
    boolean isEmpty = false;

    public AdminController(ItemDao itemDao, UserDao userDao) {
        adminService = new AdminService(itemDao, userDao);
        scanner = new Scanner(System.in);
    }

    public void showAllUsers() {
        Map<String, User> allUsers = adminService.getAllUsers();
        if (!allUsers.isEmpty() && allUsers.size() > 1) {
            LOGGER.info("id\t\tlogin\t\temail\t\tphone\t\t");
            for (User user : allUsers.values()) {
                if (user.getPhoneNumber().equals("0")) {
                    continue;
                }
                LOGGER.info(user.getPhoneNumber() + "\t" + user.getLogin() + "\t" + user.getEmailAddress() +
                        "\t" + user.getPhoneNumber() + "\t");
                isEmpty = false;
            }
        } else {
            LOGGER.info("No users yet");
            isEmpty = true;
        }
    }

    public void showUserInfo(User user) {
        Map<Integer, Item> items = adminService.getUsersItems(user.getPhoneNumber());
        LOGGER.info("Name: " + user.getName());
        LOGGER.info("Email: " + user.getEmailAddress());
        LOGGER.info("Phone number: " + user.getPhoneNumber());
        LOGGER.info("Login: " + user.getLogin());
        LOGGER.info("Items: ");
        int id = 0;
        for (Item i :
                items.values()) {
            showItemInfo(i);
            LOGGER.info("\t------------------------------------------");
        }
    }

    private void showAllItems() {
        Map<Integer, Item> allItems = adminService.getAllItems();
        if (!allItems.isEmpty()) {
            LOGGER.info("id\t\tname\t\towner card");
            for (Item i : allItems.values()) {
                LOGGER.info(adminService.getItemID(i) + "\t\t" + i.getName() + "\t\t" + i.getOwnersPhone());
                isEmpty = false;
            }
        } else {
            LOGGER.info("No items yet");
            isEmpty = true;
        }
    }

    private void showItemInfo(Item item) {
        LOGGER.info("\tname: " + item.getName());
        LOGGER.info("\tdescription: " + item.getDescription());
        LOGGER.info("\ttype: " + item.getType());
        LOGGER.info("\towner card: " + item.getOwnersPhone());

    }

    private void specificItemMenu() {
        boolean run = true;
        while (run) {
            scanner.nextLine();
            LOGGER.info("Show info of the specific item? [y/n]");
            switch (scanner.nextLine().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    LOGGER.info("Enter id: ");
                    showItemInfo(adminService.getSpecificItem(scanner.nextInt()));
                }
                case "n" -> run = false;
                default -> LOGGER.info("No such command");
            }
        }
    }

    private void specificUser() {
        boolean run = true;
        while (run) {
            scanner.nextLine();
            LOGGER.info("Show info of the specific user? [y/n]");
            switch (scanner.nextLine().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    LOGGER.info("Enter id: ");
                    showUserInfo(adminService.getSpecificUser(scanner.nextLine()));
                }
                case "n" -> run = false;
                default -> LOGGER.info("No such command");
            }
        }
    }

    private void adminCommands() {
        LOGGER.info("1. Show all users");
        LOGGER.info("2. Show all items");
        LOGGER.info("3. Exit");
    }

    public void adminMenu() {
        boolean runMenu = true;
        while (runMenu) {
            adminCommands();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    showAllUsers();
                    if (!isEmpty) {
                        specificUser();
                    }
                }
                case 2 -> {
                    showAllItems();
                    if (!isEmpty) {
                        specificItemMenu();
                    }

                }
                case 3 -> runMenu = false;
                default -> LOGGER.info("No such command");
            }
        }
    }

}
