package by.grits.utils;

import by.grits.entities.people.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Session {
  private static final Logger LOGGER = LogManager.getLogger(Session.class);
  private static User user;

  public static User getUser() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.dat"))) {
      user = (User) ois.readObject();
    } catch (Exception e) {
      LOGGER.info("Some problems with deserialization");
    }
    return user;
  }
}
