package by.grits.controllers;

import by.grits.entities.enums.ItemType;
import by.grits.entities.item.Item;
import by.grits.services.ItemService;
import by.grits.utils.Session;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Scanner;

public class ItemController {
    private Scanner scanner;
    private ItemService itemService;
    private final Logger logger;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
        scanner = new Scanner(System.in);
        logger = LogManager.getLogger("ItemController.class");
    }


    public void addItem() {
        scanner.nextLine();

        logger.info("Item name: ");
        String name = scanner.nextLine();
        logger.info("Item description: ");
        String description = scanner.nextLine();
        logger.info("Choose item type");
        boolean addItem = true;
        while (addItem) {
            for(int i = 0; i < 6; i++){
                logger.info(i + " " + ItemType.getByIndex(i) + "\n");
            }
            int temp = scanner.nextInt();
            ItemType itemType = ItemType.getByIndex(temp);
            if(itemType!=null){
                itemService.addItem(name, description, Session.getUser().getPhoneNumber(), itemType);
                addItem = false;
            } else {
                logger.info("No such type");
            }

        }

    }

    public void showAllItems() {
        Map<Integer, Item> items = itemService.getAllItems(Session.getUser().getPhoneNumber());
        logger.info("\tid" + "\t\t" + "name" + "\t\t" + "description");
        for (Item i : items.values()) {
            logger.info("\t" + itemService.getId(i) + "\t\t" + i.getName() + "\t\t" + i.getDescription());
        }
    }


    public void removeItem() {
        showAllItems();
        logger.info("enter id: ");
        itemService.removeItem(scanner.nextInt());
    }

    public void removeAllItems() {
        itemService.removeAll( Session.getUser().getPhoneNumber());
    }

    public void showInfo() {
        Map<Integer, Item> items = itemService.getAllItems( Session.getUser().getPhoneNumber());
        for (Item i : items.values()) {
            logger.info(itemService.getId(i) + "\t\t" + i.getName());
        }
        logger.info("Enter item's id: ");
        int input = scanner.nextInt();
        while (!items.containsKey(input)) {
            logger.info("No such id, try again: ");
            input = scanner.nextInt();
        }
        Item item = items.get(input);
        logger.info("Name: " + item.getName() + "\nDescription: " + item.getDescription() +
                "\nType: " + item.getType() + "\nOwner phone: " + item.getOwnersPhone());
    }

    public void itemCommands() {
        logger.info("1. Add Item");
        logger.info("2. Show items");
        logger.info("3. Remove item");
        logger.info("4. Remove all items");
        logger.info("5. Show item's info");
        logger.info("6. Exit");
    }

    public void itemMenu() {
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
                default -> logger.info("No such command");
            }
        }
    }
}
