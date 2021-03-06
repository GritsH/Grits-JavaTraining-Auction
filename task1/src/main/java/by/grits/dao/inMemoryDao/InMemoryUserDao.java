package by.grits.dao.inMemoryDao;

import by.grits.dao.UserDao;
import by.grits.dao.exception.DaoException;
import by.grits.entities.people.User;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/** This class is responsible for different operations with imitated table of database. */
public class InMemoryUserDao implements UserDao {
  private Map<String, User> usersByEmail = new HashMap<>();
  private Map<Integer, User> usersById = new HashMap<>();
  private AtomicInteger idCounter = new AtomicInteger(0);

  @Override
  public User get(int id) throws DaoException {
    try {
      return usersById.get(id);
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public User getByEmail(String email) throws DaoException {
    try {
      return usersByEmail.get(email);
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void add(User user) throws DaoException {
    try {
      int id = idCounter.incrementAndGet();
      user.setAddedAt(LocalDate.now());
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
  public List<User> getAll() throws DaoException {
    try {
      return new ArrayList<>(usersByEmail.values());
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public User signIn(String email, String password) throws DaoException {
    try {
      User foundUser = usersByEmail.get(email);
      if (foundUser != null && foundUser.getPassword().equals(password)) {
        return foundUser;
      }
      return null;
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }
}
