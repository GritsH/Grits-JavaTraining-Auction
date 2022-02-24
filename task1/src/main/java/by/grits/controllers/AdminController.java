package by.grits.controllers;

import by.grits.dao.DaoException;
import by.grits.dao.ItemDao;
import by.grits.dao.UserDao;
import by.grits.entities.items.Item;
import by.grits.entities.people.User;
import by.grits.services.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * This is class controller which provides console input and output for user whose role type is
 * ADMIN. User with such role has access to basic users' and items' information.
 */
public class AdminController {
  private static final Logger LOGGER = LogManager.getLogger(AdminController.class);
  private final Scanner scanner;
  private final AdminService adminService;
  boolean isEmpty = false;

  public AdminController(ItemDao itemDao, UserDao userDao) {
    adminService = new AdminService(itemDao, userDao);
    scanner = new Scanner(System.in);
  }

  public void showAllUsers() throws DaoException {
    Collection<User> allUsers = adminService.getAllUsers();
    allUsers.stream().sorted();
    if (!allUsers.isEmpty() && allUsers.size() > 1) {
      LOGGER.info("id\t\tname\t\temail\t\tphone\t\t");
      for (User user : allUsers) {
        if (user.getPhoneNumber().equals("0")) {
          continue;
        }
        LOGGER.info(
            user.getId()
                + "\t"
                + user.getName()
                + "\t"
                + user.getEmailAddress()
                + "\t"
                + user.getPhoneNumber()
                + "\t");
        isEmpty = false;
      }
    } else {
      LOGGER.info("No users yet");
      isEmpty = true;
    }
  }

  public void showUserInfo(User user) throws DaoException {
    Collection<Item> items = adminService.getUsersItems(user.getEmailAddress());
    items.stream().sorted();
    LOGGER.info("Name: " + user.getName());
    LOGGER.info("Email: " + user.getEmailAddress());
    LOGGER.info("Phone number: " + user.getPhoneNumber());
    LOGGER.info("Items: ");
    for (Item item : items) {
      showItemInfo(item);
      LOGGER.info("\t------------------------------------------");
    }
  }

  private void showAllItems() throws DaoException {
    Collection<Item> allItems = adminService.getAllItems();
    allItems.stream().sorted();
    if (!allItems.isEmpty()) {
      LOGGER.info("id\t\tname\t\towner email");
      for (Item item : allItems) {
        LOGGER.info(item.getId() + "\t\t" + item.getName() + "\t\t" + item.getOwnersEmail());
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
    LOGGER.info("\towner email: " + item.getOwnersEmail());
    scanner.nextLine();
  }

  private void specificItemMenu() {
    boolean run = true;
    while (run) {
      // scanner.nextLine();
      LOGGER.info("Show info of the specific item? [y/n]");
      String input = scanner.nextLine().toLowerCase(Locale.ROOT);
      switch (input) {
        case "y":
          LOGGER.info("Enter id: ");
          try {
            showItemInfo(adminService.getSpecificItem(scanner.nextInt()));
          } catch (InputMismatchException e) {
            LOGGER.info("Wrong input, dummy");
          }
          break;
        case "n":
          run = false;
          break;
        default:
          LOGGER.info("No such command");
          break;
      }
    }
  }

  private void specificUser() throws DaoException {
    boolean run = true;
    while (run) {
      LOGGER.info("Show info of the specific user? [y/n]");
      switch (scanner.nextLine().toLowerCase(Locale.ROOT)) {
        case "y":
          LOGGER.info("Enter id: ");
          try {
            showUserInfo(adminService.getSpecificUser(scanner.nextInt()));
          } catch (InputMismatchException e) {
            scanner.nextLine();
            LOGGER.info("Wrong input type, dummy");
          }
          break;
        case "n":
          run = false;
          break;
        default:
          LOGGER.info("No such command");
          break;
      }
    }
  }

  private void adminCommands() {
    LOGGER.info("1. Show all users");
    LOGGER.info("2. Show all items");
    LOGGER.info("3. Exit");
  }

  public void adminMenu() throws DaoException {
    boolean runMenu = true;
    while (runMenu) {
      adminCommands();
      switch (scanner.nextLine()) {
        case "1":
          showAllUsers();
          if (!isEmpty) {
            specificUser();
          }
          break;
        case "2":
          showAllItems();
          if (!isEmpty) {
            specificItemMenu();
          }
          break;
        case "3":
          runMenu = false;
          break;
        default:
          LOGGER.info("No such command");
          break;
      }
    }
  }
}
