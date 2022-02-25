package by.grits.serialization;

import by.grits.entities.people.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/** Util class which helps to serialize current user in .dat file */
public class UserSerializer {
  private static final Logger LOGGER = LogManager.getLogger(UserSerializer.class);

  public static User getUser() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.dat"))) {
      return (User) ois.readObject();
    } catch (FileNotFoundException e) {
      LOGGER.info("No current user");
    } catch (IOException | ClassNotFoundException e) {
      LOGGER.error("Some problems with deserialization", e);
    }
    return null;
  }

  public static User saveUser(User user) {
    if (user == null) {
      return null;
    }
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.dat"))) {
      oos.writeObject(user);
      oos.flush();
      return user;
    } catch (IOException e) {
      LOGGER.error("Smth wrong with file");
      return null;
    }
  }

  public static void removeUser() {
    File file = new File("user.dat");
    file.delete();
  }
}
