package by.grits.dao;

import by.grits.entities.enums.RoleType;
import by.grits.entities.people.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDao implements UserDao {
  //change login to email, make email a key for map1
  private Map<String, User> usersByPhoneNumber = new HashMap<>();
  //second map usersbyid

  public InMemoryUserDao() {
    usersByPhoneNumber.put("0", new User("admin", "0", "admin@gmail.com", "admin", "admin", RoleType.ADMIN));
  }

  @Override
  public User get(String id) {
    return usersByPhoneNumber.get(id);
  }

  @Override
  public void add(User user) {
    usersByPhoneNumber.put(user.getPhoneNumber(), user);
    //add to second map, generate id
  }

  @Override
  public void delete(int key) {
    //delete in second map
    usersByPhoneNumber.remove(key);
  }

  @Override
  public Map<String, User> getAll() {
    return usersByPhoneNumber;
  }

  @Override
  public User getUserByLoginPassword(String email, String password) {
    for (User u : usersByPhoneNumber.values()) {
      if (u.getLogin().equals(email) && u.getPassword().equals(password)) {
        return u;
      }
    }
    //later, add custom exception
    return null;
  }
}
