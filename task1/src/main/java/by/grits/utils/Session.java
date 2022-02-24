package by.grits.utils;

import by.grits.entities.people.User;

public class Session {
  // ThreadLocal
  private static User user;

  public static User getUser() {
    return user;
  }

  public static void setUser(User newUser) {
    user = newUser;
  }
}
