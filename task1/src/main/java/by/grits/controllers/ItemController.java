package by.grits.controllers;

import by.grits.dao.DaoException;
import by.grits.entities.enums.ItemType;
import by.grits.entities.item.Item;
import by.grits.services.ItemService;
import by.grits.utils.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
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
            for(int i = 0; i < 6; i++){
                LOGGER.info(i + " " + ItemType.getByIndex(i));
            }
            String temp = scanner.nextLine();
            if(Pattern.matches("^\\d$", temp)){
                ItemType itemType = ItemType.getByIndex(Integer.parseInt(temp));
                if(itemType!=null){
                    itemService.addItem(name, description, Session.getUser().getPhoneNumber(), itemType);
                    addItem = false;
                }
            }
             else {
                LOGGER.info("No such type");
            }

        }

    }

    public void showAllItems() throws DaoException {
        Map<Integer, Item> items = itemService.getAllItems(Session.getUser().getPhoneNumber());
        LOGGER.info("\tid" + "\t\t" + "name" + "\t\t" + "description");
        for (Item i : items.values()) {
            LOGGER.info("\t" + i.getId() + "\t\t" + i.getName() + "\t\t" + i.getDescription());
        }
    }


    public void removeItem() throws DaoException {
        showAllItems();
        LOGGER.info("enter id: ");
        itemService.removeItem(scanner.nextInt());
    }

    public void removeAllItems() throws DaoException {
        itemService.removeAll( Session.getUser().getPhoneNumber());
    }

    public void showInfo() throws DaoException {
        Map<Integer, Item> items = itemService.getAllItems( Session.getUser().getPhoneNumber());
        for (Item i : items.values()) {
            LOGGER.info(i.getId() + "\t\t" + i.getName());
        }
        LOGGER.info("Enter item's id: ");
        int input = scanner.nextInt();
        while (!items.containsKey(input)) {
            LOGGER.info("No such id, try again: ");
            input = scanner.nextInt();
        }
        Item item = items.get(input);
        LOGGER.info("Name: " + item.getName() + "\nDescription: " + item.getDescription() +
                "\nType: " + item.getType() + "\nOwner phone: " + item.getOwnersEmail());
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
                case 1 -> addItem();
                case 2 -> showAllItems();
                case 3 -> removeItem();
                case 4 -> removeAllItems();
                case 5 -> showInfo();
                case 6 -> {
                    Session.setUser(null);
                    runMenu = false;
                }
                default -> LOGGER.info("No such command");
            }
        }
    }
}
