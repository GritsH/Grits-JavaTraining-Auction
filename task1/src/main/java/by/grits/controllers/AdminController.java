package by.grits.controllers;

import by.grits.dao.inMemoryDao.ItemDao;
import by.grits.dao.inMemoryDao.UserDao;
import by.grits.entities.items.Item;
import by.grits.entities.people.User;
import by.grits.services.AdminService;
import by.grits.services.UserService;
import by.grits.services.UserServiceImpl;
import by.grits.utils.Session;
import by.grits.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.List;
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
  private final UserService userService;
  boolean isEmpty = false;

  public AdminController(ItemDao itemDao, UserDao userDao) {
    adminService = new AdminService(itemDao, userDao);
    userService = new UserServiceImpl(userDao);
    scanner = new Scanner(System.in, StandardCharsets.UTF_8);
  }

  /**
   * Method for displaying all users and their basic information. Only ADMIN can access this
   * information.
   *
   * @throws DaoException due to calling a service method with such exception
   */
  public void showAllUsers() throws DaoException {
    List<User> allUsers = adminService.getAllUsers();
    if (!allUsers.isEmpty()) {
      LOGGER.info("id\t\tname\t\temail\t\tphone\t\t");
      for (User user : allUsers) {
        int id = user.getId();
        String name = user.getName();
        String email = user.getEmailAddress();
        String phoneNumber = user.getPhoneNumber();
        LOGGER.info("{}\t{}\t{}\t{}", id, name, email, phoneNumber);
        isEmpty = false;
      }
    }
  }

  /**
   * This method displays all information of the specific user.
   *
   * @param user user whose information admin wants to display.
   * @throws DaoException due to calling a service method with such exception
   */
  public void showUserInfo(User user) throws DaoException {
    List<Item> items = adminService.getUsersItems(user.getEmailAddress());
    LOGGER.info("Name: " + user.getName());
    LOGGER.info("Email: " + user.getEmailAddress());
    LOGGER.info("Phone number: " + user.getPhoneNumber());
    LOGGER.info("Items: ");
    for (Item item : items) {
      showItemInfo(item);
      LOGGER.info("\t------------------------------------------");
    }
  }

  /**
   * Method for displaying all items and their basic information.
   *
   * @throws DaoException due to calling a service method with such exception
   */
  private void showAllItems() throws DaoException {
    List<Item> allItems = adminService.getAllItems();
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

  /**
   * Method for displaying all information if the specific item.
   *
   * @param item desired item which information is wanted.
   */
  private void showItemInfo(Item item) {
    LOGGER.info("\tname: " + item.getName());
    LOGGER.info("\tdescription: " + item.getDescription());
    LOGGER.info("\ttype: " + item.getType());
    LOGGER.info("\towner email: " + item.getOwnersEmail());
    scanner.nextLine();
  }

  /**
   * Method to confirm if admin wants information of any specific item. Admin has to specify desired
   * item's id in console input.
   */
  private void specificItemMenu() {
    boolean run = true;
    while (run) {
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

  /**
   * Method to confirm if admin wants information of any specific user. Admin has to specify desired
   * user's id in console input.
   *
   * @throws DaoException due to calling a service method with such exception
   */
  private void specificUserMenu() throws DaoException {
    boolean run = true;
    while (run) {
      LOGGER.info("Show info of the specific user? [y/n]");
      switch (scanner.nextLine().toLowerCase(Locale.ROOT)) {
        case "y":
          LOGGER.info("Enter id: ");
          try {
            showUserInfo(adminService.getSpecificUser(scanner.nextInt()));
          } catch (InputMismatchException e) {
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

  /** This method displays allowed operations for admin. */
  private void adminCommands() {
    LOGGER.info("1. Show all users");
    LOGGER.info("2. Show all items");
    LOGGER.info("3. Exit");
  }

  /**
   * This method displays admin menu and reads console input.
   *
   * @throws DaoException due to calling methods with such exception
   */
  public void adminMenu() throws DaoException {
    boolean runMenu = true;
    while (runMenu) {
      adminCommands();
      switch (scanner.nextLine()) {
        case "1":
          showAllUsers();
          if (!isEmpty) {
            specificUserMenu();
          }
          break;
        case "2":
          showAllItems();
          if (!isEmpty) {
            specificItemMenu();
          }
          break;
        case "3":
          Session.setUser(null);
          userService.logOut();
          runMenu = false;
          break;
        default:
          LOGGER.info("No such command");
          break;
      }
    }
  }
}
