package by.grits.services;

import by.grits.dao.UserDao;
import by.grits.entities.people.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public User logIn(String login, String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    return userDao.getUserByLoginPassword(login, password);
  }

  @Override
  public void addNewUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
    userDao.add(user);
  }

  @Override
  public boolean userExists(String phoneNumber) {
    Map<String, User> userMap = userDao.getAll();
    if (userMap.containsKey(phoneNumber)) {
      return true;
    }
    return false;
  }
}
