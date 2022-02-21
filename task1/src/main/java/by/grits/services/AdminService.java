package by.grits.services;

import by.grits.dao.DaoException;
import by.grits.dao.ItemDao;
import by.grits.dao.UserDao;
import by.grits.entities.item.Item;
import by.grits.entities.people.User;

import java.util.Collection;
import java.util.Map;

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

  public Map<Integer, Item> getAllItems()  {
    return itemDao.getAll();
  }

  public Map<Integer, Item> getUsersItems(String phoneNumber){
    return itemDao.getUserItems(phoneNumber);
  }

  public Item getSpecificItem(int id) {
    return itemDao.getEntityById(id);
  }

  public User getSpecificUser(int id) {
    return userDao.get(id);
  }
}
