package by.grits.services;

import by.grits.dao.DaoException;
import by.grits.dao.ItemDao;
import by.grits.dao.UserDao;
import by.grits.entities.items.Item;
import by.grits.entities.people.User;

import java.util.Collection;

public class AdminService {

  private final ItemDao itemDao;
  private final UserDao userDao;

  public AdminService(ItemDao itemDao, UserDao userDao) {
    this.itemDao = itemDao;
    this.userDao = userDao;
  }

  public Collection<User> getAllUsers() throws DaoException {
    return userDao.getAll();
  }

  public Collection<Item> getAllItems() throws DaoException {
    return itemDao.getAll();
  }

  public Collection<Item> getUsersItems(String emailAddress) throws DaoException {
    return itemDao.getUserItems(emailAddress);
  }

  public Item getSpecificItem(int id) {
    return itemDao.getEntityById(id);
  }

  public User getSpecificUser(int inputId) throws DaoException {
    return userDao.get(inputId);
  }
}
