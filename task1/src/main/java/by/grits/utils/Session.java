package by.grits.utils;

import by.grits.entities.people.User;

public class Session {
  private static final Logger LOGGER = LogManager.getLogger(Session.class);
  private static User user;

  public static User getUser() {
    return user;
  }
}
