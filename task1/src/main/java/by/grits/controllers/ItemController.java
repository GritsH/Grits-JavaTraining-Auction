package by.grits.controllers;

import by.grits.entities.items.Item;
import by.grits.services.ItemService;
import by.grits.services.UserService;
import by.grits.dao.exception.DaoException;
import by.grits.entities.enums.ItemType;
import by.grits.utils.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This is class controller which provides console input\output. Mostly works with users whose role
 * type is USER. Such users can add new items, remove them or get all the information about it.
 */
public class ItemController {
  private static final Logger LOGGER = LogManager.getLogger(ItemController.class);
  private Scanner scanner;
  private ItemService itemService;
  private UserService userService;

  public ItemController(ItemService itemService, UserService userService) {
    this.itemService = itemService;
    this.userService = userService;
    scanner = new Scanner(System.in, StandardCharsets.UTF_8);
  }

  /**
   * This method is for adding new item. Basically user enters basic item's information and new item
   * is added to imitated database.
   *
   * @throws DaoException due to calling a service method with such exception.
   */
  public void addItem() throws DaoException {
    LOGGER.info("Item name: ");
    String name = scanner.nextLine();
    LOGGER.info("Item description: ");
    String description = scanner.nextLine();
    LOGGER.info("Choose item type");
    boolean addItem = true;
    while (addItem) {
      for (int i = 0; i < 6; i++) {
        LOGGER.info(i + " " + ItemType.getByIndex(i));
      }
      try {
        int temp = scanner.nextInt();
        ItemType itemType = ItemType.getByIndex(temp);
        if (itemType != null) {
          Item addedItem =
              new Item(name, description, Session.getUser().getEmailAddress(), itemType);
          itemService.addItem(addedItem);
          LOGGER.info("Item added");
          scanner.nextLine();
          addItem = false;
        }
      } catch (InputMismatchException e) {
        scanner.nextLine();
        LOGGER.info("Wrong input type");
      }
    }
  }

  /**
   * This method displays items information
   *
   * @throws DaoException due to calling a service method with such exception.
   */
  public void showAllItems() throws DaoException {
    List<Item> items = itemService.getAllItems(Session.getUser().getEmailAddress());
    LOGGER.info("\tid" + "\t\t" + "name" + "\t\t" + "description");
    for (Item i : items) {
      LOGGER.info("\t" + i.getId() + "\t\t" + i.getName() + "\t\t" + i.getDescription());
    }
  }

  /**
   * This method is for removing item. User has to enter item's id, then item will be removed from
   * imitated database.
   *
   * @throws DaoException due to calling a service method with such exception.
   */
  public void removeItem() throws DaoException {
    showAllItems();
    LOGGER.info("enter id: ");
    itemService.removeItem(scanner.nextInt());
    LOGGER.info("Item removed");
    scanner.nextLine();
  }

  /**
   * This method is for removing all user's items from database
   *
   * @throws DaoException due to calling a service method with such exception.
   */
  public void removeAllItems() throws DaoException {
    itemService.removeAll(Session.getUser().getEmailAddress());
    LOGGER.info("All items removed");
  }

  /**
   * This method displays information of the specific user's item. Requires item's id
   *
   * @throws DaoException due to calling a service method with such exception.
   */
  public void showInfo() throws DaoException {
    Collection<Item> items = itemService.getAllItems(Session.getUser().getEmailAddress());
    for (Item i : items) {
      LOGGER.info(i.getId() + "\t\t" + i.getName());
    }

    try {
      LOGGER.info("Enter item's id: ");
      int id = scanner.nextInt();
      boolean itemIsNotFound = true;
      while (itemIsNotFound) {
        for (Item item : items) {
          if (item.getId() == id) {
            itemIsNotFound = false;
            LOGGER.info(
                "Name: "
                    + item.getName()
                    + "\nDescription: "
                    + item.getDescription()
                    + "\nType: "
                    + item.getType()
                    + "\nOwner email: "
                    + item.getOwnersEmail());
            scanner.nextLine();
            break;
          }
        }
        if (itemIsNotFound) {
          scanner.nextLine();
          LOGGER.info("No such id");
          break;
        }
      }
    } catch (InputMismatchException e) {
      scanner.nextLine();
      LOGGER.info("Wrong input type");
    }
  }

  /** Basic method for displaying menu of allowed operations for user. */
  public void itemCommands() {
    LOGGER.info("1. Add Item");
    LOGGER.info("2. Show items");
    LOGGER.info("3. Remove item");
    LOGGER.info("4. Remove all items");
    LOGGER.info("5. Show item's info");
    LOGGER.info("6. Exit");
  }

  /**
   * Method displays menu commands and reading user's input.
   *
   * @throws DaoException due to calling methods with such exception.
   */
  public void itemMenu() throws DaoException {
    boolean runMenu = true;
    while (runMenu) {
      itemCommands();
      switch (scanner.nextLine()) {
        case "1":
          addItem();
          break;
        case "2":
          showAllItems();
          break;
        case "3":
          removeItem();
          break;
        case "4":
          removeAllItems();
          break;
        case "5":
          showInfo();
          break;
        case "6":
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
