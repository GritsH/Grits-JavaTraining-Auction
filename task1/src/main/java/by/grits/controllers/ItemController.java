package by.grits.controllers;

import by.grits.entities.enums.ItemType;
import by.grits.entities.item.Item;
import by.grits.entities.people.CommonUser;
import by.grits.entities.people.User;
import by.grits.services.ItemDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemController {
    private Scanner scanner;
    private static ItemDao itemDAO;
    private List<Item> items;
    private CommonUser currentUser;

    public ItemController() {
        itemDAO = new ItemDao();
        scanner = new Scanner(System.in);
        items = new ArrayList<>();
    }

    public ItemDao getItemDAO() {
        return itemDAO;
    }

//    public CommonUser getCurrentUser() {
//        return currentUser;
//    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = (CommonUser) currentUser;
    }

    public void addItem() {
        String name = "";
        String description = "";
        ItemType itemT = null;

        scanner.nextLine();

        System.out.println("Item name: ");
        name = scanner.nextLine();
        System.out.println("Item description: ");
        description = scanner.nextLine();
        System.out.println("Choose item type");
        boolean addItem = true;
        while (addItem) {
            System.out.print("1 " + ItemType.FURNITURE.name() + '\n' +
                    "2 " + ItemType.BOOK.name() + '\n' +
                    "3 " + ItemType.DECOR.name() + '\n' +
                    "4 " + ItemType.MUSICAL_INSTRUMENT.name() + '\n' +
                    "5 " + ItemType.PAINTING.name() + '\n' +
                    "6 " + ItemType.OTHER.name() + "\n:");
            int temp = scanner.nextInt();

            if (temp > 0 && temp < 7) {
                switch (temp) {
                    case 1 -> itemT = ItemType.FURNITURE;
                    case 2 -> itemT = ItemType.BOOK;
                    case 3 -> itemT = ItemType.DECOR;
                    case 4 -> itemT = ItemType.MUSICAL_INSTRUMENT;
                    case 5 -> itemT = ItemType.PAINTING;
                    case 6 -> itemT = ItemType.OTHER;
                }
                Item item = new Item(name, description, currentUser.getParticipantCard(), itemT);
                itemDAO.add(item);
                currentUser.setItem(item);

                addItem = false;
            } else {
                System.out.println("No such type");
            }

        }

    }

    public void showAllItems() {
        items = itemDAO.getUserItems(currentUser.getParticipantCard());
        int index = 0;
        System.out.println("\tindex" + "\t\t" + "name" + "\t\t" + "description");
        for (Item i :
                items) {
            index++;
            System.out.println("\t" + index + "\t\t" + i.getName() + "\t\t" + i.getDescription());
        }
    }

    public void removeItem() {
        showAllItems();
        System.out.println("enter index: ");
        itemDAO.delete(items.get(scanner.nextInt() - 1));
    }

    public void removeAllItems() {
        items = itemDAO.getUserItems(currentUser.getParticipantCard());
        for (Item i :
                items) {
            itemDAO.delete(i);
        }
    }

    public void showInfo() {
        items = itemDAO.getUserItems(currentUser.getParticipantCard());
        int index = 0;
        for (Item i :
                items) {
            index++;
            System.out.println(index + "\t\t" + i.getName());
        }
        System.out.println("Enter item's index: ");
        int input = scanner.nextInt();
        while (input < 1 || input > index) {
            System.out.println("No such index, try again: ");
            input = scanner.nextInt();
        }
        Item item = items.get(input - 1);
        System.out.println("Name: " + item.getName() + "\nDescription: " + item.getDescription() +
                "\nType: " + item.getType() + "\nOwner ID: " + item.getOwnerCard());
    }

    public void itemCommands() {
        System.out.println("1. Add Item");
        System.out.println("2. Show items");
        System.out.println("3. Remove item");
        System.out.println("4. Remove all items");
        System.out.println("5. Show item's info");
        System.out.println("6. Exit");
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
                    setCurrentUser(null);
                    runMenu = false;
                }
                default -> System.out.println("No such command");
            }
        }
    }
}
