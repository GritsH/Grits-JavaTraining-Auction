package by.grits.utils;

import by.grits.entities.people.User;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Session {
  private static User user;

  public static User getUser() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.dat"))) {
      user = (User) ois.readObject();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;
  }
}
