package by.grits.utils;

import by.grits.entities.people.User;

/**
 * This is util class which sets and gets current user. Methods were made static in order to make
 * return value global and accessible in other classes.
 */
public class Session {
  // ThreadLocal?
  private static User user;

  public static User getUser() {
    return user;
  }

  public static void setUser(User newUser) {
    user = newUser;
  }
}
