package by.grits.dao;

import by.grits.entities.enums.RoleType;
import by.grits.entities.people.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserDao implements UserDao {
  private Map<String, User> usersByEmail = new HashMap<>();
  private Map<Integer, User> usersById = new HashMap<>();
  private AtomicInteger idCounter = new AtomicInteger(1);

  public InMemoryUserDao() {
    User admin = new User("admin", "0", "admin@gmail.com", "admin", RoleType.ADMIN);
    usersById.put(0, admin);
    usersByEmail.put("admin@gmail.com", admin);
  }

  @Override
  public User get(int id) {
    return usersById.get(id);
  }

  @Override
  public User getByEmail(String email) {
    return usersByEmail.get(email);
  }

  @Override
  public void add(User user) throws DaoException {
    try {
      int id = idCounter.incrementAndGet();
      user.setId(id);
      usersById.put(id, user);
      usersByEmail.put(user.getEmailAddress(), user);
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void delete(int key) throws DaoException {
    try {
      usersByEmail.remove(usersById.get(key).getEmailAddress());
      usersById.remove(key);
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Collection<User> getAll() throws DaoException {
    try {
      return usersByEmail.values();
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public User signIn(String email, String password) {
    User foundUser = usersByEmail.get(email);
    if (foundUser != null && foundUser.getPassword().equals(password)) {
      return foundUser;
    }
    // later, add custom exception
    return null;
  }
}
