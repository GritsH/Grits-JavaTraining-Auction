package by.grits.io;

import by.grits.controllers.AdminController;
import by.grits.controllers.ItemController;
import by.grits.controllers.UserController;
import by.grits.dao.DaoException;
import by.grits.dao.DaoInitializer;
import by.grits.dao.ItemDao;
import by.grits.dao.UserDao;
import by.grits.entities.enums.RoleType;
import by.grits.entities.people.User;
import by.grits.factory.DaoFactory;
import by.grits.serialization.UserSerializer;
import by.grits.services.ItemService;
import by.grits.services.ItemServiceImpl;
import by.grits.services.UserService;
import by.grits.services.UserServiceImpl;
import by.grits.utils.Session;

public class Menu {
  public void startMenu() throws DaoException {
    DaoFactory daoFactory = DaoFactory.getInstance();
    UserDao userDao = daoFactory.getInMemoryUserDaoImpl();
    UserService userService = new UserServiceImpl(userDao);
    UserController userController = new UserController(userService);

    ItemDao itemDao = daoFactory.getInMemoryItemDaoImpl();
    ItemService itemService = new ItemServiceImpl(itemDao);
    ItemController itemController = new ItemController(itemService);

    DaoInitializer daoInitializer = new DaoInitializer();
    daoInitializer.initializeUsers(userDao);
    daoInitializer.initializeItems(itemDao);

    User foundUser = UserSerializer.getUser();
    Session.setUser(foundUser);

    AdminController adminController = new AdminController(itemDao, userDao);

    while (true) {
      User currentUser = Session.getUser();
      if (currentUser != null) {
        if (RoleType.ADMIN.equals(currentUser.getRole())) {
          boolean runAdmin = true;
          while (runAdmin) {
            adminController.adminMenu();
            runAdmin = false;
          }
        } else {
          itemController.itemMenu();
        }
      } else {
        userController.userMenu();
      }
    }
  }
}
