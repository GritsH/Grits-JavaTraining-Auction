package by.grits.dao.inMemoryDao;

import by.grits.dao.ItemDao;
import by.grits.dao.UserDao;
import by.grits.dao.exception.DaoException;
import by.grits.entities.enums.ItemType;
import by.grits.entities.enums.RoleType;
import by.grits.entities.items.Item;
import by.grits.entities.people.User;

/** This is class initializer which puts some data to the simulated database. */
public class DaoInitializer {

  public void initializeUsers(UserDao userDao) throws DaoException {
    userDao.add(new User( "admin@gmail.com", "admin", RoleType.ADMIN));
    userDao.add(new User( "name@gmail.com", "namepassword", RoleType.USER));
    userDao.add(new User( "name2@mail.com", "password", RoleType.USER));
    userDao.add(
        new User(
             "somename@gmail.com", "simplePassword", RoleType.USER));
    userDao.add(new User( "aaaaa@mail.com", "qwerty", RoleType.USER));
  }

  public void initializeItems(ItemDao itemDao) throws DaoException {
    itemDao.add(new Item("item", "description", "name@gmail.com", ItemType.BOOK));
    itemDao.add(new Item("chair", "description of chair", "name@gmail.com", ItemType.FURNITURE));
    itemDao.add(
        new Item("guitar", "description of guitar", "name2@mail.com", ItemType.MUSICAL_INSTRUMENT));
    itemDao.add(
        new Item("painting", "description of painting", "aaaaa@mail.com", ItemType.PAINTING));
    itemDao.add(new Item("nadoelo", "description of nadoelo", "aaaaa@mail.com", ItemType.OTHER));
    itemDao.add(new Item("vase", "description vase", "somename@gmail.com", ItemType.DECOR));
    itemDao.add(
        new Item("cdsacdsa", "description of cdsacdsa", "somename@gmail.com", ItemType.OTHER));
  }
}
