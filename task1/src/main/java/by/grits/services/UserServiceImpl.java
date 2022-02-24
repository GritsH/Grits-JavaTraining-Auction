package by.grits.services;

import by.grits.dao.DaoException;
import by.grits.dao.UserDao;
import by.grits.entities.people.User;
import by.grits.serialization.UserSerializer;

public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public User logIn(String email, String password) throws DaoException {
    User user = userDao.signIn(email, password);
    UserSerializer.saveUser(user);
    return user;
  }

  @Override
  public void addNewUser(User user) throws DaoException {
    userDao.add(user);
  }

  @Override
  public boolean userExists(String email) throws DaoException {
    return userDao.getByEmail(email) != null;
  }

  @Override
  public void logOut() throws DaoException {
    UserSerializer.removeUser();
  }
}
