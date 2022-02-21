package by.grits.services;

import by.grits.dao.DaoException;
import by.grits.dao.UserDao;
import by.grits.entities.people.User;

public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public User logIn(String email, String password) {
    return userDao.signIn(email, password);
  }

  @Override
  public void addNewUser(User user) throws DaoException {
    try {
      userDao.add(user);
    } catch (DaoException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public boolean userExists(String email) {
    return userDao.getByEmail(email) != null;
  }
}
