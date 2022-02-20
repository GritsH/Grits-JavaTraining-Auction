package by.grits.services;

import by.grits.dao.ItemDao;
import by.grits.dao.UserDao;
import by.grits.entities.item.Item;
import by.grits.entities.people.User;

import java.util.Map;

public class AdminService {

  private final ItemDao itemDao;
  private final UserDao userDao;

  public AdminService(ItemDao itemDao, UserDao userDao) {
    this.itemDao = itemDao;
    this.userDao = userDao;
  }

  public int getItemID(Item item) {
    return itemDao.getId(item);
  }

  public Map<String, User> getAllUsers() {
    return userDao.getAll();
  }

  public Map<Integer, Item> getAllItems() {
    return itemDao.getAll();
  }

  public Map<Integer, Item> getUsersItems(String phoneNumber) {
    return itemDao.getUserItems(phoneNumber);
  }

  public Item getSpecificItem(int id) {
    return itemDao.getEntityById(id);
  }

  public User getSpecificUser(String id) {
    return userDao.get(id);
  }
}
