package by.grits.controllers;

import by.grits.dao.DaoException;
import by.grits.entities.enums.ItemType;
import by.grits.entities.item.Item;
import by.grits.services.ItemService;
import by.grits.utils.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ItemController {
  private static final Logger LOGGER = LogManager.getLogger(ItemController.class);
  private Scanner scanner;
  private ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
    scanner = new Scanner(System.in);
  }

  public void addItem() throws DaoException {
    scanner.nextLine();
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
      String temp = scanner.nextLine();
      if (Pattern.matches("^\\d$", temp)) {
        ItemType itemType = ItemType.getByIndex(Integer.parseInt(temp));
        if (itemType != null) {
          Item addedItem =
              new Item(name, description, Session.getUser().getEmailAddress(), itemType);
          itemService.addItem(addedItem);
          addItem = false;
        }
      } else {
        LOGGER.info("No such type");
      }
    }
  }

  public void showAllItems() throws DaoException {
    Collection<Item> items = itemService.getAllItems(Session.getUser().getEmailAddress());
    LOGGER.info("\tid" + "\t\t" + "name" + "\t\t" + "description");
    for (Item i : items) {
      LOGGER.info("\t" + i.getId() + "\t\t" + i.getName() + "\t\t" + i.getDescription());
    }
  }

  public void removeItem() throws DaoException {
    showAllItems();
    LOGGER.info("enter id: ");
    itemService.removeItem(scanner.nextInt());
  }

  public void removeAllItems() throws DaoException {
    itemService.removeAll(Session.getUser().getEmailAddress());
  }

  public void showInfo() throws DaoException {
    Collection<Item> items = itemService.getAllItems(Session.getUser().getEmailAddress());
    for (Item i : items) {
      LOGGER.info(i.getId() + "\t\t" + i.getName());
    }
    scanner.nextLine();
    LOGGER.info("Enter item's id: ");
    while (true) {
      String input = scanner.nextLine();
      if (Pattern.matches("^\\d$", input)) {
        int id = Integer.parseInt(input);
        boolean itemExists = false;
        for (Item item : items) {
          if (item.getId() == id) {
            itemExists = true;
            LOGGER.info(
                "Name: "
                    + item.getName()
                    + "\nDescription: "
                    + item.getDescription()
                    + "\nType: "
                    + item.getType()
                    + "\nOwner email: "
                    + item.getOwnersEmail());
            break;
          }
        }
        if (!itemExists) {
          LOGGER.info("No such id, try again: ");
        }
      } else {
        LOGGER.info("Invalid input, try again: ");
      }
    }
  }

  public void itemCommands() {
    LOGGER.info("1. Add Item");
    LOGGER.info("2. Show items");
    LOGGER.info("3. Remove item");
    LOGGER.info("4. Remove all items");
    LOGGER.info("5. Show item's info");
    LOGGER.info("6. Exit");
  }

  public void itemMenu() throws DaoException {
    boolean runMenu = true;
    while (runMenu) {
      itemCommands();
      switch (scanner.nextInt()) {
        case 1:
          addItem();
          break;
        case 2:
          showAllItems();
          break;
        case 3:
          removeItem();
          break;
        case 4:
          removeAllItems();
          break;
        case 5:
          showInfo();
          break;
        case 6:
          Session.setUser(null);
          runMenu = false;
          break;
        default:
          LOGGER.info("No such command");
          break;
      }
    }
  }
}
