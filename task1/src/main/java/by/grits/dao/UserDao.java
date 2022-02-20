package by.grits.dao;

import by.grits.entities.people.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public interface UserDao {
  User get(String id);

  void add(User user) throws NoSuchAlgorithmException, InvalidKeySpecException;

  void delete(int key);

  Map<String, User> getAll();

  User getUserByLoginPassword(String login, String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException;
}
