package by.grits.io;

import by.grits.controllers.AdminController;
import by.grits.controllers.ItemController;
import by.grits.controllers.UserController;
import by.grits.entities.people.Admin;

import java.util.Scanner;

public class Menu {
    public void startMenu(){
        UserController userController = new UserController();
        ItemController itemController = new ItemController();
        AdminController adminController = new AdminController(userController, itemController);
        Admin admin = new Admin(null, null, null, "admin", "admin");
        boolean runApp = true;
        while(runApp){
            userController.userMenu();
            if(userController.getCurrentUser().equals(admin)){
                adminController.adminMenu();
                userController.userMenu();
            }
            itemController.setCurrentUser(userController.getCurrentUser());
            itemController.itemMenu();
        }
    }
}
