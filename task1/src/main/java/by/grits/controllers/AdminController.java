package by.grits.controllers;

import by.grits.entities.item.Item;
import by.grits.entities.people.CommonUser;
import by.grits.services.ItemDao;
import by.grits.services.UserDao;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AdminController {
    private Scanner scanner;
    private UserDao userDAO;
    private ItemDao itemDAO;
    boolean isEmpty = false;

    public AdminController(UserController userController, ItemController itemController) {
        userDAO = userController.getUserDAO();
        itemDAO = itemController.getItemDAO();
        scanner = new Scanner(System.in);
    }

    public void showAllUsers() {
        List<CommonUser> allUsers = userDAO.getAll();
        if (!allUsers.isEmpty()) {
            int index = 0;
            System.out.println("id\t\tlogin\t\temail\t\tphone\t\tcard");
            for (CommonUser commonUser : allUsers) {
                index++;
                System.out.println(index + "\t" + commonUser.getLogin() + "\t" + commonUser.getEmailAddress() +
                        "\t" + commonUser.getPhoneNumber() + "\t" + commonUser.getParticipantCard());
                isEmpty = false;
            }
        }else{
            System.out.println("No users yet");
            isEmpty = true;
        }
    }

    public void showUserInfo(CommonUser commonUser) {
        List<Item> items = commonUser.getItems();
        System.out.println("Name: " + commonUser.getName());
        System.out.println("Email: " + commonUser.getEmailAddress());
        System.out.println("Phone number: " + commonUser.getPhoneNumber());
        System.out.println("Login: " + commonUser.getLogin());
        System.out.println("Items: ");
        int id = 0;
        for (Item i :
                items) {
            showItemInfo(i);
            System.out.println("\t------------------------------------------");
        }
    }

    private void showAllItems() {
        List<Item> allItems = itemDAO.getAll();
        if(!allItems.isEmpty()){
            System.out.println("id\t\tname\t\towner card");
            int index = 0;
            for (Item i :
                    allItems) {
                index++;
                System.out.println(index + "\t\t" + i.getName() + "\t\t" + i.getOwnerCard());
                isEmpty = false;
            }
        }else{
            System.out.println("No items yet");
            isEmpty = true;
        }
    }

    private void showItemInfo(Item item) {
        System.out.println("\tname: " + item.getName());
        System.out.println("\tdescription: " + item.getDescription());
        System.out.println("\ttype: " + item.getType());
        System.out.println("\towner card: " + item.getOwnerCard());

    }

    private void specificItemMenu() {
        boolean run = true;
        while (run) {
            scanner.nextLine();
            System.out.println("Show info of the specific item? [y/n]");
            switch (scanner.nextLine().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    System.out.println("Enter id: ");
                    showItemInfo(itemDAO.getById(scanner.nextInt() - 1));
                }
                case "n" -> run = false;
                default -> System.out.println("No such command");
            }
        }
    }

    private void specificUser() {
        boolean run = true;
        while (run) {
            scanner.nextLine();
            System.out.println("Show info of the specific user? [y/n]");
            switch (scanner.nextLine().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    System.out.println("Enter id: ");
                    showUserInfo(userDAO.getById(scanner.nextInt() - 1));
                }
                case "n" -> run = false;
                default -> System.out.println("No such command");
            }
        }
    }

    private void adminCommands() {
        System.out.println("1. Show all users");
        System.out.println("2. Show all items");
        System.out.println("3. Exit");
    }

    public void adminMenu() {
        boolean runMenu = true;
        while (runMenu) {
            adminCommands();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    showAllUsers();
                    if(!isEmpty) {
                        specificUser();
                    }
                }
                case 2 -> {
                    showAllItems();
                    if(!isEmpty){
                        specificItemMenu();
                    }

                }
                case 3 -> runMenu = false;
                default -> System.out.println("No such command");
            }
        }
    }

}
