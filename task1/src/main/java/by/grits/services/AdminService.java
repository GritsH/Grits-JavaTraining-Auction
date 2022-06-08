package by.grits.services;

import by.grits.dao.inMemoryDao.UserDao;
import by.grits.entities.items.Item;
import by.grits.entities.people.User;
import by.grits.dao.DaoException;
import by.grits.dao.inMemoryDao.ItemDao;

import java.util.Collections;
import java.util.List;

/** Class service which works with different dao to get necessary information from them */
public class AdminService {

  private final ItemDao itemDao;
  private final UserDao userDao;

  public AdminService(ItemDao itemDao, UserDao userDao) {
    this.itemDao = itemDao;
    this.userDao = userDao;
  }

  public List<User> getAllUsers() throws DaoException {
    List<User> foundUsers = userDao.getAll();
    Collections.sort(foundUsers);
    return foundUsers;
  }

  public List<Item> getAllItems() throws DaoException {
    List<Item> foundItems = itemDao.getAll();
    Collections.sort(foundItems);
    return foundItems;
  }

  public List<Item> getUsersItems(String emailAddress) throws DaoException {
    List<Item> foundUserItems = itemDao.getUserItems(emailAddress);
    Collections.sort(foundUserItems);
    return foundUserItems;
  }

  public Item getSpecificItem(int id) {
    return itemDao.getEntityById(id);
  }

  public User getSpecificUser(int inputId) throws DaoException {
    return userDao.get(inputId);
  }
}
