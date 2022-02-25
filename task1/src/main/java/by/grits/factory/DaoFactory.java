package by.grits.factory;

import by.grits.dao.InMemoryItemDao;
import by.grits.dao.InMemoryUserDao;
import by.grits.dao.ItemDao;
import by.grits.dao.UserDao;

/** This lass implements factory pattern. */
public class DaoFactory {
  private static final DaoFactory instance = new DaoFactory();

  private final UserDao inMemoryUserDaoImpl;
  private final ItemDao inMemoryItemDaoImpl;

  public DaoFactory() {
    inMemoryUserDaoImpl = new InMemoryUserDao();
    inMemoryItemDaoImpl = new InMemoryItemDao();
  }

  public static DaoFactory getInstance() {
    return instance;
  }

  public UserDao getInMemoryUserDaoImpl() {
    return inMemoryUserDaoImpl;
  }

  public ItemDao getInMemoryItemDaoImpl() {
    return inMemoryItemDaoImpl;
  }
}
